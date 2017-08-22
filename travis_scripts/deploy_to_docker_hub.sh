echo "Pushing citadel service docker images to docker hub ...."
docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD
docker push kelleherke/tmx-addressservice:v11
docker push kelleherke/tmx-pupilservice:v11
docker push kelleherke/tmx-confsvr:v11
docker push kelleherke/tmx-eurekasvr:v11
docker push kelleherke/tmx-zuulsvr:v11