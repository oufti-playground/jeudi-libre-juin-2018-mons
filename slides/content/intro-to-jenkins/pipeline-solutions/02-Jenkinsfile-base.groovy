pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        echo 'Building...'
        sh './scripts/build.sh'
      }
    }
    stage('Test') {
      steps {
        sh './scripts/integration-tests.sh'
      }
    }
    stage('Deploy') {
      steps {
        sh './scripts/deploy.sh'
      }
    }
  }
}
