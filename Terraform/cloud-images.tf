locals {
  shared_images_project_id = "sab-ssvcs-gold-images-c3d9"
}

data "google_compute_image" "image" {
  family  = "sabre-${var.image_vars["os_flavour"]}-${var.image_vars["os_version"]}-oslogin-${var.image_vars["image_release_type"]}"
  project = local.shared_images_project_id
}

locals {
  common_labels = {
    business_unit        = var.resource_labels["business_unit"]
    environment          = var.resource_labels["environment"]
    application_service  = var.resource_labels["application_service"]
    business_service     = var.resource_labels["business_service"]
    owner_email          = var.resource_labels["owner_email"]
    service_owner_email  = var.resource_labels["service_owner_email"]
    logical_environments = var.resource_labels["logical_environments"]
  }
}
