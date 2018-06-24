pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh './scripts/build.sh'
        junit 'target/surefire-reports/*.xml'
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
