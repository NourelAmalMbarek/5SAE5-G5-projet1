pipeline {
    agent any

    stages {
        stage('GIT') {
            steps {
                git branch: 'RimaChemengui-5SAE5-G5',
                url: 'https://github.com/NourelAmalMbarek/5SAE5-G5-projet1.git'
            }
        }
        stage('MVN CLEAN AND COMPILE') {
            steps{
                sh 'mvn clean compile'
            }
        }
        stage('Tests Junit Mockito') {
                    steps {
                        sh 'mvn test'
                    }
                }
           stage('MVN SONARQUBE ') {
        steps {
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar'
            }
        }

     }
  }
        

