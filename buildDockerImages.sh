cd nagarro-shop-api-gateway
./buildDockerImage.sh
cd ..

cd nagarro-shop-config-server
./buildDockerImage.sh
cd ..

cd nagarro-shop-inventory-service
./buildDockerImage.sh
cd ..

cd nagarro-shop-order-service
./buildDockerImage.sh
cd ..

cd nagarro-shop-tracking-service
./buildDockerImage.sh
cd ..

cd nagarro-shop-user-service
./buildDockerImage.sh
cd ..