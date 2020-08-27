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
        def version = "1.0"
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
//                 sh "docker build -t image ."
//                 sh "docker tag image ${repository}:${version}"
//                 sh "docker images"
//                 withDockerRegistry([credentialsId: "githubcredentials", url: "https://docker.pkg.github.com"]) {
//                     sh "docker push ${repository}:${version}"
//                 }
                echo "${repository}:${version}"
            }
        }
        stage ('Deploy') {
            container ('helm') {
                git branch: 'master',
                    credentialsId: 'githubcredentials',
                    url: 'https://github.com/shyamkondisetty/helloworldhelmchart.git'
                sh "ls -a"
                sh "helm init --client-only --skip-refresh"
//                 sh "/helm upgrade --install --wait --set image.repository=${repository},image.tag=${version} helloworldhelm helloworldhelm"
            }
        }
    }
}