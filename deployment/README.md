Ansible Role
==================
This role for Junior DevOps Hiring Day

- create security group
- generate EC2 keypair
- create EC2 instance
- create S3 bucket and upload index.html
- add EC2 instance to group web
- install NGINX with role nginxinc.nginx
- delete default nginx index.html
- download and install new index.html from S3 with get_url ansible module

Requirements
------------

To successfully make an API call to AWS need install boto (http://docs.pythonboto.org)
and export two environment variables:

export AWS_ACCESS_KEY_ID='AK123'

export AWS_SECRET_ACCESS_KEY='abc123'

Running command
---------------

$ ansible-playbook site.yml
