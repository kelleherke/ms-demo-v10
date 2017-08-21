echo "Pushing service docker images to docker hub ...."
docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD
docker push kelleherke/tmx-addressservice:$BUILD_NAME
docker push kelleherke/tmx-pupilservice:$BUILD_NAME
docker push kelleherke/tmx-confsvr:$BUILD_NAME
docker push kelleherke/tmx-eurekasvr:$BUILD_NAME
docker push kelleherke/tmx-zuulsvr:$BUILD_NAME
