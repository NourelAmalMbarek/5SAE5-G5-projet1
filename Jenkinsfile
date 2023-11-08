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
                               

                      
                        stage('Docker compose') {
                            steps {
                                script {
                                    sh 'docker compose up -d'
                                }
                            }
                        }


        stage('Prometheus') {
            steps {
                echo 'Setting up Prometheus..'
                // Configurez ici votre fichier prometheus.yml pour utiliser l'exposition Prometheus de votre application
                // Par exemple:
                  scrape_configs:
                     - job_name: 'skier'
                     static_configs:
                       - targets: ['192.168.132.10:8089']
            }
        }
        stage('Grafana') {
            steps {
                echo 'Setting up Grafana..'
                // Configurez ici votre dashboard Grafana pour visualiser les métriques de votre application
                // Par exemple:
                   grafanaDashboard('Jenkins', 'http://192.168.33.10:3000/d/haryan-jenkins/jenkins')
            }
        }


     }
  }
        

