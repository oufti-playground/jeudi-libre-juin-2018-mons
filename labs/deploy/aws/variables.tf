variable "lab_amis" {
  description = "Mapping regions to the lab AMI IDs"
  type        = "map"

  default = {
    "us-east-1"    = "ami-a4e4bddb"
    "eu-central-1" = "ami-12a294f9"
  }
}

variable "lab_instances_count" {
  description = "How many instances to start for lab"
  default     = 30
}

variable "lab_region" {
  description = "AWS Region where to launch instances"
  type        = "string"
  default     = "us-east-1"
}
