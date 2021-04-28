resource "google_compute_instance" "Linux-instance" {
  machine_type = var.instance_vars["machine_type"]
  name         = var.instance_vars["name"]
  project      = var.global_vars["project"]
  zone         = var.global_vars["zone"]

  tags = [
    "default-${var.global_vars["network_tag"]}"
  ]

  labels = local.common_labels

  boot_disk {
    device_name = var.instance_vars["disk_name"]
    initialize_params {
      size  = var.instance_vars["boot_disk_size"]
      type  = var.instance_vars["boot_disk_type"]
      image = data.google_compute_image.image.self_link
    }
  }

  network_interface {
    subnetwork = "projects/${var.global_vars["subnetwork_project"]}/regions/${var.global_vars["region"]}/subnetworks/${var.global_vars["subnetwork"]}"
  }

  metadata = {
    enable-oslogin      = "true"
    #amt-idm-hostprofile = "csat-csaag1"
  }

  service_account {
    scopes = ["cloud-platform"]
  }
}
