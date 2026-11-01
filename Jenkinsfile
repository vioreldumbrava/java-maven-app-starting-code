pipeline {
    agent any
    environment {
        BRANCH_NAME = "${env.BRANCH_NAME}"
        BUILD_NUMBER = "${env.BUILD_NUMBER}"
        GIT_COMMIT = "${env.GIT_COMMIT}"
        NEW_VERSION = "1.0.${BUILD_NUMBER}"
    }
    parameters {
        string(name: 'BRANCH_NAME', defaultValue: 'main', description: 'Branch to build')
        choice(name: 'ENVIRONMENT', choices: ['dev', 'qa', 'prod'], description: 'Deployment environment')
        booleanParam(name: 'SKIP_TESTS', defaultValue: false, description: 'Skip tests during build')
    }
    stages {
        stage('Build') {
            steps {
                echo "Build branch $BRANCH_NAME with build number $BUILD_NUMBER"
            }
        }

        stage('Test') {
            steps {
                echo "Tests completed for branch $BRANCH_NAME at commit $GIT_COMMIT"
            }
        }

        stage('Deploy') {
            steps {
                when {
                expression {
                    echo "Deploying to environment $ENVIRONMENT"
                    if (params.SKIP_TESTS == false) {
                        echo "Running tests as per parameter"
                    }
                }
                }
                echo "Deployment completed for branch $BRANCH_NAME with new version $NEW_VERSION at commit $GIT_COMMIT"
            }
        }
    }
}