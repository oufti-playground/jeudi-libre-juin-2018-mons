pipeline {
  agent {
    node {
      label 'maven-jdk8'
    }
  }
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
        junit 'target/failsafe-reports/*.xml'
      }
    }
    stage('Deploy') {
      steps {
        sh './scripts/deploy.sh'
      }
    }
  }
}
