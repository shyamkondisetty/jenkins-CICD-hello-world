podTemplate(
    label: 'mypod',
    inheritFrom: 'default',
    containers: [
        containerTemplate(
            name: 'docker',
            image: 'docker:18.02',
            ttyEnabled: true,
            command: 'cat'
        ),
        containerTemplate(
            name: 'helm',
            image: 'ibmcom/k8s-helm:v2.6.0',
            ttyEnabled: true,
            command: 'cat'
        )
    ],
    volumes: [
        hostPathVolume(
            hostPath: '/var/run/docker.sock',
            mountPath: '/var/run/docker.sock'
        )
    ]
) {
    node('mypod') {
        def commitId
        stage ('Extract') {
            checkout scm
//             commitId = sh(script: 'git rev-parse --short HEAD', returnStdout: true).trim()
        }
        stage ('Build') {
             sh './gradlew clean build'
        }
        def repository
        stage ('Docker') {
            container ('docker') {
//                 def registryIp = sh(script: 'getent hosts registry.kube-system | awk \'{ print $1 ; exit }\'', returnStdout: true).trim()
//                 repository = "${registryIp}:80/hello"
                sh "docker build -t image ."
                sh "docker tag image docker.pkg.github.com/shyamkondisetty/jenkins-cicd-hello-world/helloworld:1.0"
                sh "docker images"
                withDockerRegistry([credentialsId: "githubcredentials", url: "https://docker.pkg.github.com"]) {
                    sh "docker push docker.pkg.github.com/shyamkondisetty/jenkins-cicd-hello-world/helloworld:1.0"
                }
//                 sh "docker push ${repository}:${commitId}"
            }
        }
        stage ('Deploy') {
            container ('helm') {
                sh "/helm init --client-only --skip-refresh"
//                 sh "/helm upgrade --install --wait --set image.repository=${repository},image.tag=${commitId} hello hello"
            }
        }
    }
}