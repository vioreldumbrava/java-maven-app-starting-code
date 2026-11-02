def functionExample() {
    echo "Directory listing****************************:"
    sh 'ls -la'
    sh 'whoami'
    sh 'mkdir -p build'
    sh 'ls -la build'
}

def functionBuild(){
    echo "Build branch $GIT_BRANCH with build number $BUILD_NUMBER"
    // Built-in Jenkins variables
    echo "Job: $JOB_NAME, workspace: $WORKSPACE"
    echo "Build URL: $BUILD_URL"
    // Variables defined in the environment block
    echo "Building image tag $IMAGE_TAG"
    // Same variable accessed via env. and params.
    echo "Env via env.: ${env.APP_NAME}, parameter via params.: ${params.ENVIRONMENT}"
    // Setting an env variable at runtime (visible to later stages too)
    env.BUILD_TIME = new Date().format('yyyy-MM-dd HH:mm')
    echo "Build time: $BUILD_TIME"
}
return this