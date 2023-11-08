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
        stage('Nexus Deploy') {
                    steps {
                    
                      sh 'mvn deploy -DaltDeploymentRepository=deploymentRepo::default::http://localhost:8081/repository/maven-releases/ -Dnexus.user=admin -Dnexus.password=nexus'
                    
                    }
                }
                stage("Docker build") {
            steps {
               
                sh 'docker build -t rimachemengui/skier .'
               
            }
        }
        stage('Push image to Hub'){
            steps{
                script{
                   withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhubpwd')]) {
                   sh 'docker login -u rimachemengui -p ${dockerhubpwd}'

}
                   sh 'docker push rimachemengui/skier:latest'
                }
            }
        }
        
        stage('Docker compose') {
             steps {
                script {
                      sh 'docker compose up -d'
                 }
             }
        }


     }
  }
        

