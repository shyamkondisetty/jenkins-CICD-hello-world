#!groovy

node {
  stage('Checkout') {
    checkout scm
  }
  stage('Build') {
    sh './gradlew clean build'
  }
}