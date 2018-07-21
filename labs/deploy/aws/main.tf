provider "aws" {
  region = "${var.lab_region}"
  alias  = "default"
}

resource "aws_instance" "lab_node" {
  ami           = "${lookup(var.lab_amis,var.lab_region)}"
  instance_type = "t2.medium"
  count         = "${var.lab_instances_count}"

  security_groups = [
    "${aws_security_group.lab_sg.name}",
  ]

  associate_public_ip_address = true
}

resource "aws_security_group" "lab_sg" {
  name = "lab_sg"

  ## Allow Internet Access for everyone
  egress {
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 443
    to_port     = 443
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ## Allow SSH to the outside for cloning repos
  egress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ## Allow ingress on HTTP(S) and SSH
  ingress {
    from_port   = 8080
    to_port     = 8080
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }
}
