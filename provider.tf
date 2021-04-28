provider "google" {
  version = "~> 3.48.0"
  project = var.global_vars["project"]
  region  = var.global_vars["region"]
  #credentials = file("/terraform/auth/tf-provisioner.json")
}

provider "google-beta" {
  project = var.global_vars["project"]
  region  = var.global_vars["region"]
  version = "~> 3.48.0"
}
