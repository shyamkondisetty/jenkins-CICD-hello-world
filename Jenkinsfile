#!groovy
node {
  def dockerTool = tool name: 'Docker', type: 'org.jenkinsci.plugins.docker.commons.tools.DockerTool'
  withEnv(["DOCKER=${dockerTool}/bin"]) {
    stage('Checkout') {
      checkout scm
    }
    stage('Build') {
//      sh './gradlew clean build'
      docker.withRegistry('docker.pkg.github.com', 'githubcredentials') {
//        docker.build('helloworld').push('1.0')
      }
//      dockerCmd 'version'
    }
  }
}

def dockerCmd(args) {
  sh "${DOCKER}/docker ${args}"
}