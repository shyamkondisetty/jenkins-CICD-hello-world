#!groovy
node {
  def dockerTool = tool name: 'Docker', type: 'org.jenkinsci.plugins.docker.commons.tools.DockerTool'
  withEnv(["DOCKER=${dockerTool}/bin"]) {
    stage('Checkout') {
      checkout scm
    }
    stage('Build') {
      sh './gradlew clean build'
      dockerCmd 'images'
    }
  }
}

def dockerCmd(args) {
  sh "${DOCKER}/docker ${args}"
}