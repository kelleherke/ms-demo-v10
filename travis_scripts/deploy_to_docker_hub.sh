echo "Pushing kelleherke service docker images to docker hub ...."
docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD
echo "See Images ....start"
docker images
echo "See Images ....end"
docker push kelleherke/tmx-addressservice:v11
docker push kelleherke/tmx-pupilservice:v11
docker push kelleherke/tmx-confsvr:v11
docker push kelleherke/tmx-eurekasvr:v11
docker push kelleherke/tmx-zuulsvr:v11