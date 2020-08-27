#!groovy
node {
  def dockerTool = tool name: 'Docker', type: 'org.jenkinsci.plugins.docker.commons.tools.DockerTool'
  withEnv(["DOCKER=${dockerTool}/bin"]) {
    stage('Checkout') {
      checkout scm
    }
    stage('Build') {
      sh './gradlew clean build'
      docker.withRegistry('https://docker.mycorp.com/') {
        // or docker.build, etc.
        sh "docker images"
        // runs: docker pull --all-tags docker.mycorp.com/myImg
      }
//      dockerCmd 'version'
    }
  }
}

def dockerCmd(args) {
  sh "${DOCKER}/docker ${args}"
}