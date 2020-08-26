#!groovy

node {
  def dockerTool = tool name: 'Docker', type: 'org.jenkinsci.plugins.docker.commons.tools.DockerTool'
  withEnv(["DOCKER=${dockerTool}/bin"]) {
    //stages
    //now we can simply call: dockerCmd 'run mycontainer'
  }
  stage('Checkout') {
    checkout scm
  }
  stage('Build') {
    sh './gradlew clean build'
    dockerCmd 'images'
  }
}

def dockerCmd(args) {
  sh "sudo ${DOCKER}/docker ${args}"
}