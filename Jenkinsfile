pipeline {
    agent any
    environment {
        // Pipeline-level: available in every stage
        NEW_VERSION = "1.0.${BUILD_NUMBER}"
        APP_NAME = 'java-maven-app'
        IMAGE_TAG = "${APP_NAME}:${NEW_VERSION}"
        // Credentials example (needs a credential with this ID in Jenkins):
        // DOCKER_CREDS = credentials('docker-hub-repo')  // also sets DOCKER_CREDS_USR and DOCKER_CREDS_PSW
    }
    parameters {
        string(name: 'BRANCH_NAME', defaultValue: 'main', description: 'Branch to build')
        choice(name: 'ENVIRONMENT', choices: ['dev', 'qa', 'prod'], description: 'Deployment environment')
        booleanParam(name: 'SKIP_TESTS', defaultValue: false, description: 'Skip tests during build')
    }
    stages {
        stage('Build') {
            steps {
                echo "Build branch $GIT_BRANCH with build number $BUILD_NUMBER"
                // Built-in Jenkins variables
                echo "Job: $JOB_NAME, workspace: $WORKSPACE"
                echo "Build URL: $BUILD_URL"
                // Variables defined in the environment block
                echo "Building image tag $IMAGE_TAG"
                // Same variable accessed via env. and params.
                echo "Env via env.: ${env.APP_NAME}, parameter via params.: ${params.ENVIRONMENT}"
                // Setting an env variable at runtime (visible to later stages too)
                script {
                    env.BUILD_TIME = new Date().format('yyyy-MM-dd HH:mm')
                }
            }
        }

        stage('Test') {
            when {
                expression { !params.SKIP_TESTS }
            }
            steps {
                echo "Tests completed for branch $GIT_BRANCH at commit $GIT_COMMIT"
            }
        }

        stage('Deploy') {
            // Stage-level: only available in this stage
            environment {
                DEPLOY_TARGET = "${params.ENVIRONMENT}-server"
            }
            steps {
                echo "Deploying $IMAGE_TAG to $DEPLOY_TARGET (built at $BUILD_TIME)"
                // Temporary override, only inside the block
                withEnv(['LOG_LEVEL=debug']) {
                    echo "Log level: $LOG_LEVEL"
                }
                echo "Deploying to environment ${params.ENVIRONMENT}"
                echo "Deployment completed for branch $GIT_BRANCH with new version $NEW_VERSION at commit $GIT_COMMIT"
            }
        }
    }
}