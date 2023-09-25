def getGitBranchName() { 
                return scm.branches[0].name
            }
def branchName
def targetBranch 

pipeline {
  agent any
	environment {
     DOCKERHUB_USERNAME = "hassenzayani"
     PROD_TAG = "${DOCKERHUB_USERNAME}/test:v1.0.0-prod"
    }
	parameters {
	string(name: 'BRANCH_NAME', defaultValue: "${scm.branches[0].name}", description: 'Git branch name')
        string(name: 'CHANGE_ID', defaultValue: '', description: 'Git change ID for merge requests')
	string(name: 'CHANGE_TARGET', defaultValue: '', description: 'Git change ID for the target merge requests')
    }

  stages {
    stage('Github') {
      steps {
	script {
	branchName = params.BRANCH_NAME
        targetBranch = branchName

          git branch: branchName,
          url: 'https://github.com/ZayaniHassen/Test5Arctic3Back.git',
          credentialsId: 'cb4b0ceb-32e2-41ca-817a-2b9701522051'
      }
	  echo "Current branch name: ${branchName}"
	  echo "Current branch name: ${targetBranch}"
      }
    }

     stage ('MNV BUILD') {
	when {
         expression {
           (params.CHANGE_ID != null)  && (targetBranch == 'main') 
            }
     }
	steps {
        sh 'mvn clean install'
        echo 'Build stage done'
      }
    }

 //    stage ('MVN COMPILE') {
	// when {
 //         expression {
 //          (params.CHANGE_ID != null)  && (targetBranch == 'main')
 //            }
	//   }
 //      steps {
 //        sh 'mvn compile'
 //        echo 'compile stage done'
 //      }
 //    }
	   // stage ('STATIC TEST WITH SONAR') {
    //    when {
    //      expression {
    //        (params.CHANGE_ID != null)  && (targetBranch == 'main')
    //      }
    //    }
    //    steps {
    //      withSonarQubeEnv('sonarqube') {
    //             sh 'mvn sonar:sonar'
    //      }
    //    }
    // }

 //     stage ('JUNIT TEST') {
	// when {
 //         expression {
 //           (params.CHANGE_ID != null)  && (targetBranch == 'main')
 //            }
	//    }
 //      steps {
 //        sh 'mvn test'
 //        echo 'test stage done'
 //      }
 //    }
	 

    stage ('NEXUS DEPLOY') {
	when {
         expression {
          (params.CHANGE_ID != null)  && (targetBranch == 'main')
	}
	   }
       steps {
       sh 'mvn deploy -DskipTests'
      }
    }
	  

    stage('Build Docker') {
    when {
        expression {
          (params.CHANGE_ID != null)  && ((targetBranch == 'main')) 
        }
    }
    steps {
        script {
            if (targetBranch == 'main') {
                sh "docker build -t ${PROD_TAG} ."
            } 
        }
    }
}



     stage('Docker Login'){
	     when {
        expression {
          (params.CHANGE_ID != null)  && (targetBranch == 'main')
        }
    }
            steps{
                withCredentials([usernamePassword(credentialsId: '0fbe5ef9-b1ba-43bf-915e-a2ca5652441d', usernameVariable: 'DOCKERHUB_USERNAME', passwordVariable: 'DOCKERHUB_PASSWORD')]) {
                sh "docker login -u ${DOCKERHUB_USERNAME} -p ${DOCKERHUB_PASSWORD}"
    }
  }

        }

	stage('Docker Push'){
		when {
        expression {
          (params.CHANGE_ID != null)  && (targetBranch == 'main')
        }
    }
            steps{
                sh 'docker push $DOCKERHUB_USERNAME/test --all-tags '
            }
        }
// 	stage('Remove Containers') {
// 		when {
//         expression {
//           (params.CHANGE_ID != null)  && ((targetBranch == 'main') || (targetBranch == 'staging') || (targetBranch == 'develop'))
//         }
//     }
//     steps {
//         sh '''
//         container_ids=$(docker ps -q --filter "publish=8089/tcp")
//         if [ -n "$container_ids" ]; then
//             echo "Stopping and removing containers..."
//             docker stop $container_ids
//             docker rm $container_ids
//         else
//             echo "No containers found using port 8089."
//         fi
//         '''
//     }
// }
        stage('Deploy to Prod') {
            when {
                expression { 
			(params.CHANGE_ID != null)  && (targetBranch == 'main')
		}
            }
           steps {
   		sh "sudo ansible-playbook ansible/k8s.yml -i ansible/inventory/host.yml"   
	   } 
	}
  }
}
