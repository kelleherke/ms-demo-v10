echo "Pushing service docker images to docker hub ...."
docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD
docker push kelleherke/tmx-addressservice:v10
docker push kelleherke/tmx-pupilservice:v10
docker push kelleherke/tmx-confsvr:v10
docker push kelleherke/tmx-eurekasvr:v10
docker push kelleherke/tmx-zuulsvr:v10