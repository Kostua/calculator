'use strict';

// tag::vars[]
const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');
const follow = require('./follow'); // function to hop multiple links by "rel"

const root = '/api';
// end::vars[]

// tag::app[]
class App extends React.Component {

	constructor(props) {
		super(props);
		this.state = {carriers: [], attributes: [], pageSize: 2, links: {}};
		this.updatePageSize = this.updatePageSize.bind(this);
		this.onCreate = this.onCreate.bind(this);
		this.onDelete = this.onDelete.bind(this);
		this.onNavigate = this.onNavigate.bind(this);
	}

	loadFromServer(pageSize){
	        follow(client, root, [
	                {rel: 'carriers', params: {size: pageSize}}]
	                ).then(carrierCollection => {
	                        return client({
	                                method: 'GET',
	                                path: carrierCollection.entity._links.profile.href,
	                                headers: {'Accept': 'application/schema+json'}
	                        }).then(schema => {
	                                this.schema = schema.entity;
	                                return carrierCollection;
	                        });
	                }).done(carrierCollection => {
	                        this.setState({
	                                carriers: carrierCollection.entity._embedded.carriers,
	                                attributes: Object.keys(this.schema.properties),
	                                pageSize: pageSize,
	                                links: carrierCollection.entity._links});
									});
	}
	onCreate(newCarrier) {
		follow(client, root, ['carriers']).then(carrierCollection => {
			return client({
				method: 'POST',
				path: carrierCollection.entity._links.self.href,
				entity: newCarrier,
				headers: {'Content-Type': 'application/json'}
			})
		}).then(response => {
			return follow(client, root, [
				{rel: 'carriers', params: {'size': this.state.pageSize}}]);
		}).done(response => {
			if (typeof response.entity._links.last !== "undefined") {
				this.onNavigate(response.entity._links.last.href);
			} else {
				this.onNavigate(response.entity._links.self.href);
			}
		});
	}

	onDelete(carrier) {
		client({method: 'DELETE', path: carrier._links.self.href}).done(response => {
			this.loadFromServer(this.state.pageSize);
		});
	}

	onNavigate(navUri) {
		client({method: 'GET', path: navUri}).done(carrierCollection => {
			this.setState({
				carriers: carrierCollection.entity._embedded.carriers,
				attributes: this.state.attributes,
				pageSize: this.state.pageSize,
				links: carrierCollection.entity._links
			});
		});
	}
		// tag::update-page-size[]
		updatePageSize(pageSize) {
			if (pageSize !== this.state.pageSize) {
				this.loadFromServer(pageSize);
			}
		}

		// tag::follow-1[]
	componentDidMount() {
		this.loadFromServer(this.state.pageSize);
	}
	// end::follow-1[]

	render() {
		return (
			<div>
				<CreateDialog attributes={this.state.attributes} onCreate={this.onCreate}/>
				<CarrierList carriers={this.state.carriers}
							  links={this.state.links}
							  pageSize={this.state.pageSize}
							  onNavigate={this.onNavigate}
							  onDelete={this.onDelete}
							  updatePageSize={this.updatePageSize}/>
			</div>
		)
	}

}


// end::app[]
class CreateDialog extends React.Component {

	constructor(props) {
		super(props);
		this.handleSubmit = this.handleSubmit.bind(this);
	}

	handleSubmit(e) {
		e.preventDefault();
		const newCarrier = {};
		this.props.attributes.forEach(attribute => {
			newCarrier[attribute] = ReactDOM.findDOMNode(this.refs[attribute]).value.trim();
		});
		this.props.onCreate(newCarrier);

		// clear out the dialog's inputs
		this.props.attributes.forEach(attribute => {
			ReactDOM.findDOMNode(this.refs[attribute]).value = '';
		});

		// Navigate away from the dialog to hide it.
		window.location = "#";
	}

	render() {
		const inputs = this.props.attributes.map(attribute =>
			<p key={attribute}>
				<input type="text" placeholder={attribute} ref={attribute} className="field"/>
			</p>
		);

		return (
			<div>
				<a href="#createCarrier">Create</a>

				<div id="createCarrier" className="modalDialog">
					<div>
						<a href="#" title="Close" className="close">X</a>

						<h2>Create new carrier</h2>

						<form>
							{inputs}
							<button onClick={this.handleSubmit}>Create</button>
						</form>
					</div>
				</div>
			</div>
		)
	}

}
// tag::carrier-list[]
class CarrierList extends React.Component{
	constructor(props) {
		super(props);
		this.handleNavFirst = this.handleNavFirst.bind(this);
		this.handleNavPrev = this.handleNavPrev.bind(this);
		this.handleNavNext = this.handleNavNext.bind(this);
		this.handleNavLast = this.handleNavLast.bind(this);
		this.handleInput = this.handleInput.bind(this);
	}

		// tag::handle-page-size-updates[]
		handleInput(e) {
			e.preventDefault();
			const pageSize = ReactDOM.findDOMNode(this.refs.pageSize).value;
			if (/^[0-9]+$/.test(pageSize)) {
				this.props.updatePageSize(pageSize);
			} else {
				ReactDOM.findDOMNode(this.refs.pageSize).value =
					pageSize.substring(0, pageSize.length - 1);
			}
		}
		// end::handle-page-size-updates[]
	
		// tag::handle-nav[]
		handleNavFirst(e){
			e.preventDefault();
			this.props.onNavigate(this.props.links.first.href);
		}
	
		handleNavPrev(e) {
			e.preventDefault();
			this.props.onNavigate(this.props.links.prev.href);
		}
	
		handleNavNext(e) {
			e.preventDefault();
			this.props.onNavigate(this.props.links.next.href);
		}
	
		handleNavLast(e) {
			e.preventDefault();
			this.props.onNavigate(this.props.links.last.href);
		}
		// end::handle-nav[]
	
		// tag::carrier-list-render[]
	render() {
		const carriers = this.props.carriers.map(carrier =>
			<Carrier key={carrier._links.self.href} carrier={carrier} onDelete={this.props.onDelete}/>
		);
		const navLinks = [];
		if ("first" in this.props.links) {
			navLinks.push(<button key="first" onClick={this.handleNavFirst}>&lt;&lt;</button>);
		}
		if ("prev" in this.props.links) {
			navLinks.push(<button key="prev" onClick={this.handleNavPrev}>&lt;</button>);
		}
		if ("next" in this.props.links) {
			navLinks.push(<button key="next" onClick={this.handleNavNext}>&gt;</button>);
		}
		if ("last" in this.props.links) {
			navLinks.push(<button key="last" onClick={this.handleNavLast}>&gt;&gt;</button>);
		}
	

		return (
			<div>
				<input ref="pageSize" defaultValue={this.props.pageSize} onInput={this.handleInput}/>
				<table>
					<tbody>
						<tr>
							<th>Name</th>
							<th>Air</th>
							<th>Sea</th>
							<th></th>
						</tr>
						{carriers}
					</tbody>
				</table>
				<div>
					{navLinks}
				</div>
			</div>
		)
	}
}
// end::carrier-list[]

// tag::carrier[]
class Carrier extends React.Component{
	constructor(props) {
		super(props);
		this.handleDelete = this.handleDelete.bind(this);
	}

	handleDelete() {
		this.props.onDelete(this.props.carrier);
	}


	render() {
		return (
			<tr>
				<td>{this.props.carrier.name}</td>
				<td>{this.props.carrier.air}</td>
				<td>{this.props.carrier.sea}</td>
				<td>
					<button onClick={this.handleDelete}>Delete</button>
				</td>
			</tr>
		)
	}
}
// end::carrier[]

// tag::render[]
ReactDOM.render(
	<App />,
	document.getElementById('react')
)
// end::render[]

