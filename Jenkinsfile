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
                stage('Build Docker Image') {
                                   steps {
                                       script {
                                           sh 'docker build -t rimachemengui/skier .'
                                       }
                                   }
                               }

                               stage('Docker Hub') {
                                   steps {
                                       script {
                                           sh 'docker login -u rimachemengui -p dckr_pat_V8mHArp9TO5h7FCk6I13lk9btfI'
                                       }
                                   }
                               }
                               
                        stage('Push image '){
            steps{
                script{
                   
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
        

