output "instance_dns_list" {
  value = "${join(aws_instance.lab_node.*.public_dns)}"
}
