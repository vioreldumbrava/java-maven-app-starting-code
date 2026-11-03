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
                    env.PIPELINE_BRANCH = env.BRANCH_NAME ?: env.GIT_BRANCH?.replaceFirst('^origin/', '') ?: 'unknown'
                    gvscr = load 'script_new.groovy'
                }
            }
        }
        stage('Test') {
            steps {
                echo 'Running tests...'
                echo "Executing tests for branch ${env.PIPELINE_BRANCH}"
            }
        }
        stage('Build Jar') {
            when {
                expression {
                    env.PIPELINE_BRANCH == 'master'
                }
            }
            steps {
                script {
                    gvscr.functionBuildJar()
                }
            }
        }
        stage('Build Docker Image') {
            when {
                expression {
                    env.PIPELINE_BRANCH == 'master'
                }
            }
            steps {
                script {
                    gvscr.functionBuildDocker()
                }
            }
        }
        stage('Push Docker Image') {
            when {
                expression {
                    env.PIPELINE_BRANCH == 'master'
                }
            }
            steps {
                script {
                    gvscr.functionPushDocker()
                }
            }
        }
        stage('Deploy to Jenkins Host') {
            when {
                expression {
                    env.PIPELINE_BRANCH == 'master'
                }
            }
            steps {
                script {
                    gvscr.functionDeploy()
                }
            }
        }
    }
}
