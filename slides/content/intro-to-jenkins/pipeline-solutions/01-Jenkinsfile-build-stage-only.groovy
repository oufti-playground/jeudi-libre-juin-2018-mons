pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        echo 'Building...'
        sh './scripts/build.sh'
      }
    }
  }
}
