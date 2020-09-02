podTemplate(
    label: 'mypod',
    inheritFrom: 'default',
    serviceAccount: 'jenkins',
    containers: [
        containerTemplate(
            name: 'docker',
            image: 'docker:18.02',
            ttyEnabled: true,
            command: 'cat'
        ),
        containerTemplate(
            name: 'helm',
            image: 'lachlanevenson/k8s-helm:v3.0.3',
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

        def version = "3.0"
        stage ('Extract') {
            checkout scm
//             commitId = sh(script: 'git rev-parse --short HEAD', returnStdout: true).trim()
        }
        stage ('Build') {
             sh './gradlew clean build'
        }
        def repository = "docker.pkg.github.com/shyamkondisetty/jenkins-cicd-hello-world/helloworld"
        stage ('Docker') {
            container ('docker') {
                sh "docker build -t image ."
                sh "docker tag image ${repository}:${version}"
                sh "docker images"
                   ([credentialsId: "githubcredentials", url: "https://docker.pkg.github.com"]) {
                    sh "docker push ${repository}:${version}"
                }
                echo "${repository}:${version}"
            }
        }
        stage ('Deploy') {
            container ('helm') {
                dir("helloworldhelmchart") {
                    git branch: 'master',
                        credentialsId: 'githubcredentials',
                        url: 'https://github.com/shyamkondisetty/helloworldhelmchart.git'

                }
                sh "ls -a"
                sh "helm version"
                sh "helm repo add stable https://kubernetes-charts.storage.googleapis.com/"
                sh "helm list --all"
                sh "helm -n jenkins upgrade --install --wait --set image.repository=${repository},image.tag=${version} helloworldhelmchart helloworldhelmchart"
            }
        }
    }
}