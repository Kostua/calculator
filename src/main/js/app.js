'use strict';

// tag::vars[]
const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');
// end::vars[]

// tag::app[]
class App extends React.Component {

	constructor(props) {
		super(props);
		this.state = {carriers: []};
	}

	componentDidMount() {
		client({method: 'GET', path: '/api/carriers'}).done(response => {
			this.setState({carriers: response.entity._embedded.carriers});
		});
	}

	render() {
		return (
			<CarrierList carriers = {this.state.carriers}/>
		)
	}
}
// end::app[]

// tag::carrier-list[]
class CarrierList extends React.Component{
	render() {
		const carriers = this.props.carriers.map(carrier =>
			<Carrier key={carrier._links.self.href} carrier={carrier}/>
		);
		return (
			<table>
				<tbody>
					<tr>
						<th>Name</th>
						<th>Air</th>
						<th>Sea</th>
					</tr>
					{carriers}
				</tbody>
			</table>
		)
	}
}
// end::carrier-list[]

// tag::carrier[]
class Carrier extends React.Component{
	render() {
		return (
			<tr>
				<td>{this.props.carrier.name}</td>
				<td>{this.props.carrier.air}</td>
				<td>{this.props.carrier.sea}</td>
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

