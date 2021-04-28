output "VM_private_ip" {
  value = google_compute_instance.Linux-instance.network_interface.0.network_ip
}
