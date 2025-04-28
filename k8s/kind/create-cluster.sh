gcloud beta container --project "scalerkart-453310" \
clusters create-auto "scalerkart-cluster" \
--region "us-central1" \
--release-channel "regular" \
--tier "standard" \
--enable-ip-access \
--no-enable-google-cloud-access \
--network "projects/scalerkart-453310/global/networks/default" \
--subnetwork "projects/scalerkart-453310/regions/us-central1/subnetworks/default" \
--cluster-ipv4-cidr "/17" \
--binauthz-evaluation-mode=DISABLED


gcloud container clusters delete webstore-cluster --region us-central1

gcloud compute disks create scalerkart-auth-persistent-disk --size=1GB --zone=us-central1-a
gcloud compute disks create scalerkart-cart-persistent-disk --size=1GB --zone=us-central1-b
gcloud compute disks create scalerkart-order-persistent-disk --size=1GB --zone=us-central1-b
gcloud compute disks create scalerkart-product-persistent-disk --size=5GB --zone=us-central1-a

gcloud compute disks describe scalerkart-auth-persistent-disk --zone=us-central1-a
gcloud compute disks describe scalerkart-cart-persistent-disk --zone=us-central1-b
gcloud compute disks describe scalerkart-order-persistent-disk --zone=us-central1-b

gcloud compute disks delete scalerkart-auth-persistent-disk --zone=us-central1-a
gcloud compute disks delete scalerkart-cart-persistent-disk --zone=us-central1-b
gcloud compute disks delete scalerkart-order-persistent-disk --zone=us-central1-b
gcloud compute disks delete scalerkart-product-persistent-disk --zone=us-central1-a


