def functionBuildJar() {
    echo 'Building the application...'
    sh 'mvn clean package'
}

def functionBuildDocker() {
    echo 'Building the Docker image...'
    sh 'docker build -t "$DOCKER_IMAGE" .'
}

def functionPushDocker() {
    echo 'Pushing the Docker image to Docker Hub...'
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
        sh 'printf "%s" "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin'
        sh 'docker push "$DOCKER_IMAGE"'
    }
}
def functionDeploy() {
    echo 'Deploying the application container on the Jenkins host...'
    sh '''
        if docker container inspect java-maven-app >/dev/null 2>&1; then
            docker rm -f java-maven-app
        fi
        docker run -d \
            --name java-maven-app \
            --restart unless-stopped \
            -p 8081:8080 \
            "$DOCKER_IMAGE"
    '''
}

return this