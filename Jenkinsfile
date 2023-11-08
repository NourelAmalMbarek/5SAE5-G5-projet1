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
           stage('MVN JUNIT/MOCKITO') {
        steps {
                sh 'mvn test'
            }
        }

         stage('MVN SONARQUBE ') {
        steps {
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar'
            }
        }
        stage('NEXUS DEPLOY') {
                    steps {
                        sh 'mvn deploy -DaltDeploymentRepository=deploymentRepo::default::http://localhost:8081/repository/maven-releases/ -Dnexus.user=admin -Dnexus.password=nexus'
                    }
                }
            stage('BUILD DOCKER IMAGE') {
                                   steps {
                                       script {
                                           sh 'docker build -t nourelamalmbarek/piste .'
                                       }
                                   }
                               }

                               stage('DOCKER HUB') {
                                   steps {
                                       script {
                                           sh 'docker login -u nouramal -p dckr_pat_4HOLlehTtZlai3D1emjd3FzT3aw'
                                       }
                                   }
                               }
stage('PUSH DOCKER IMAGE') {
                            steps {
                                script {
                                    sh 'docker push nourelamalmbarek/piste:latest'
                                }
                            }
                        }

                        stage('DOCKER COMPOSE') {
                            steps {
                                script {
                                    sh 'docker compose up -d'
                                }
                            }
                        }

       
     }
  }
        

