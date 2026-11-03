def gvscr
pipeline {
    agent any
    tools {
        maven 'maven-3.10'
    }
    environment {
        DOCKER_IMAGE = "vioreldv2/learn-devops:jma-3.${env.BUILD_NUMBER}"
    }
    stages {
        stage('Initialize') {
            steps {
                script {
                   gvscr = load 'script_new.groovy'
                }
            }
        }
        stage('Build Jar') {
            steps {
                script {
                    gvscr.functionBuildJar()
                }
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    gvscr.functionBuildDocker()
                }
            }
        }
        stage('Push Docker Image') {
            steps {
                script {
                    gvscr.functionPushDocker()
                }
            }
        }
        stage('Deploy to Jenkins Host') {
            steps {
                script {
                   gvscr.functionDeploy()
                }
            }
        }
    }
}
