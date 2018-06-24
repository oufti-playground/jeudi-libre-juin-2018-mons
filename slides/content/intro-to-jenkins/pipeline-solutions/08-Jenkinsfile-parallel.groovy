pipeline {
  agent {
    node {
      label 'maven3-jdk8'
    }
  }
  stages {
    stage('Build') {
      steps {
        sh './scripts/build.sh'
        junit 'target/surefire-reports/*.xml'
        stash(name: 'generated-artifacts', includes: 'target/**/*')
      }
    }
    stage('Test') {
      parallel {
        stage('IT Java 8') {
          agent {
            node {
              label 'jdk8'
            }
          }
          steps {
            unstash 'generated-artifacts'
            sh './scripts/integration-tests.sh'
            junit 'target/failsafe-reports/*.xml'
          }
        }
        stage('IT Java 9') {
          agent {
            node {
              label 'jdk9'
            }
          }
          steps {
            unstash 'generated-artifacts'
            sh './scripts/integration-tests.sh'
            junit 'target/failsafe-reports/*.xml'
          }
        }
      }
    }
    stage('Deploy') {
      agent {
        node {
          label 'production'
        }
      }
      steps {
        unstash 'generated-artifacts'
        sh './scripts/deploy.sh'
      }
    }
  }
}
