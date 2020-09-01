pipeline{
  agent {
    docker { image 'nginx:latest' }
  }
  stages{
    stage("test"){
      steps{
        sh "./gradlew test"
        echo "testing the application"
      }
    }

    stage("build"){
//     when{
//         expression{
//             BRANCH_NAME = 'master'
//         }
//     }
      steps{
        sh "./gradlew build"
        echo "building the application"
      }
    }

    stage("deploy"){

        steps{
//             docker{
//                 image "nginx:latest"
//             }
            echo "deploying the project..."
        }
    }
  }
 }