global_vars = {
  project            = "sab-dev-eng-project-1-6809"
  subnetwork         = "sn-dev-uscentral1-01"
  subnetwork_project = "sab-ssvcs-network-vpcs-5041"
  region             = "us-central1"
  zone               = "us-central1-b"
  network_tag        = "uscentral1"
}

instance_vars = {
  machine_type   = "n1-standard-1"
  disk_name      = "csa-siva-test-disk"
  boot_disk_type = "pd-ssd"
  boot_disk_size = "30"
  name           = "csa-siva-jenkins-pipeline-demo"
}

image_vars = {
  os_version         = "7"
  os_flavour         = "rhel"
  image_release_type = "rc"
}

resource_labels = {
  service_label        = "csa-test-fqdn"
  business_unit        = "teo"
  owner_email          = "sivaveeradurgaprakash_balusu__sabre_com"
  application_service  = "csat-gcp"
  business_service     = "csat"
  service_owner_email  = "rakesh_athukuri_sabre_com"
  logical_environments = "testing_builds"
  environment          = "dev"
  owner                = "sg0307159"
}
