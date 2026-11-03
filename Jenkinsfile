pipeline {
    agent any
    tools {
        maven 'maven-3.10'
    }
    stages {
        stage('Build Jar') {
            steps {
                script {
                    echo 'Building the application...'
                    sh 'mvn clean package'
                }
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    echo 'Building the Docker image...'
                    sh 'docker build -t vioreldv2/learn-devops:jma-2.5 .'
                }
            }
        }
        stage('Push Docker Image') {
            steps {
                script {
                    echo 'Pushing the Docker image to Docker Hub...'
                    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                        sh 'printf "%s" "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin'
                        sh 'docker push vioreldv2/learn-devops:jma-2.5'
                    }
                }
            }
        }
        stage('Deploy to Jenkins Host') {
            steps {
                script {
                    echo 'Deploying the application container on the Jenkins host...'
                    sh '''
                        if docker container inspect java-maven-app >/dev/null 2>&1; then
                            docker rm -f java-maven-app
                        fi
                        docker run -d \
                            --name java-maven-app \
                            --restart unless-stopped \
                            -p 8081:8080 \
                            vioreldv2/learn-devops:jma-2.5
                    '''
                }
            }
        }
    }
}
