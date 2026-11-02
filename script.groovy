def functionExample() {
    echo "Directory listing****************************:"
    sh 'ls -la'
    sh 'whoami'
    sh 'mkdir -p build'
    sh 'ls -la build'
}
return this