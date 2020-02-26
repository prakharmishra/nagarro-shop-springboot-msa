kubectl delete -f .k8s/pods
kubectl delete -f .k8s/volumes

sleep 2

kubectl apply -f .k8s/volumes
kubectl apply -f .k8s/pods