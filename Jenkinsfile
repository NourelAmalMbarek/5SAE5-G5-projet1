pipeline {
    agent any

    stages {
        stage('GIT') {
            steps {
                git branch: 'ElairNaoures-5SAE5-G5',
                url: 'https://github.com/NourelAmalMbarek/5SAE5-G5-projet1.git'
            }
        }
        stage('MVN CLEAN AND COMPILE') {
            steps{
                sh 'mvn clean compile'
            }
        }


           stage('MVN SONARQUBE ') {
        steps {
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar'
            }
        }
//         stage('MVN JUNIT/MOCKITO') {
//         steps {
//                 sh 'mvn test'
//             }
//         }
              stage('NEXUS') {
                      steps {
                          sh 'mvn deploy'
                      }
                     }
//                     steps {
//                            sh 'mvn deploy -Dmaven.test.skip=true';
//                           }
//                          }

        
     }
  }
        

