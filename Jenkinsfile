pipeline {
    agent any

    stages {
        stage('GIT') {
            steps {
                git branch: 'master',
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
         stage('MVN NEXUS') {
                steps {
                       sh 'mvn nexus:nexus -Dnexus.login=admin -Dnexus.password=nexus'
                    }
                }

        
     }
  }
        

