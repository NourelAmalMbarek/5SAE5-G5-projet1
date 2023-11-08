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
                                           sh 'docker login -u nourelamal -p dckr_pat_OToyCN4_vafNmBcXmwjxRZXzyiM'
                                       }
                                   }
                               }
        stage('Push to Nexus') {
    steps {
        script {
            // Replace the placeholders with your Nexus Repository details
            def nexusUrl = 'http://192.168.33.10:8088/repository/docker-nexus/'  // Replace with your Nexus URL
            def nexusRepo = 'docker-nexus'      // Replace with your Nexus Docker repository name
            
            sh "docker tag nourelamalmbarek/piste:latest $nexusRepo/nourelamalmbarek/piste:latest"
            sh "docker login -u admin -p nexus http://192.168.33.10:8088/repository/docker-nexus/"
            sh "docker push docker push $nexusRepo/nourelamalmbarek/piste:latest"
            sh "docker logout docker logout $nexusUrl"
        }
    }
}
     }
  }
        

