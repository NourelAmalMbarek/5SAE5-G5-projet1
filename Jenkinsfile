pipeline {
  agent any
  stages {
    stage('Git Clone') {
      steps {
        checkout([
          $class: 'GitSCM', 
          branches: [[name: '*/AhmedBenAmmar-5SAE5-G5']], 
          userRemoteConfigs: [[url: 'https://github.com/NourelAmalMbarek/5SAE5-G5-projet1.git']]
        ])
      }
    }
    stage('Nettoyage du projet') {
      steps {
        sh 'mvn clean'
      }
    }
    stage('Création du livrable') {
      steps {
        sh 'mvn package -DskipTests=true'
      }
    }
    stage('Docker Login') {
      steps {
        script {
          withCredentials([usernamePassword(credentialsId: 'DOCKER', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
            sh "docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD"
          }
        }
      }
    }
    stage('Docker build') {
      steps {
        script {
           sh 'docker version'
           sh 'docker build -t ahmedbnammar/ahmedbenammar-5sae-g5 .'
           sh 'docker image list'
           sh 'docker tag ahmedbnammar/ahmedbenammar-5sae-g5 ahmedbnammar/ahmedbenammar-5sae-g5:latest'
        }
      }
    }
    stage("Push Image to Docker Hub") {
      steps {
        sh 'docker push ahmedbnammar/ahmedbenammar-5sae-g5:latest'
      }
    }
 
  stage("Publish to Nexus") {
            steps {
                sh 'mvn deploy'
            }
        }
stage('SONARQUBE') {
            steps {
                sh 'mvn sonar:sonar -Dsonar.login=4fccdf1b23f3d096f4c8ee06efee8bdf50294639'
            }
        }
        
}
}
