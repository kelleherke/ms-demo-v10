echo "Pushing service docker images to docker hub ...."
docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD
docker push citadel/tmx-addressservice:v10
docker push citadel/tmx-pupilservice:v10
docker push citadel/tmx-confsvr:v10
docker push citadel/tmx-eurekasvr:v10
docker push citadel/tmx-zuulsvr:v10