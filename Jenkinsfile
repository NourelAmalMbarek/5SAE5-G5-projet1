pipeline {
    agent any

environment {

            DOCKER_IMAGE_NAME = 'elairnaoures/gestion-station-ski-1.0'

        }
    stages {
        stage('GIT') {
            steps {
                git branch: 'ElairNaoures-5SAE5-G5',
                url: 'https://github.com/NourelAmalMbarek/5SAE5-G5-projet1.git'
            }
        }
        stage('MVN CLEAN AND COMPILE') {
            steps{
             sh 'mvn clean'
                sh 'mvn compile'
            }
        }

         stage('MVN SONARQUBE ') {
        steps {
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar'
            }
        }
//   stage('Junit/ Mockito') {
//             steps {
//                 echo 'Étape 2 : Nettoyage et compilation avec Maven en cours...'
//                 sh 'mvn test'  // Nettoie le projet
//
//                 echo 'Étape 2 : Nettoyage et compilation avec Maven terminée.'
//             }
//         }

stage('NEXUS') {
   steps {
                sh 'mvn deploy -Dmaven.test.skip=true';
            }
        }


      stage('Building image') {
                steps {
            // Assurez-vous que Docker est installé sur l'agent Jenkins
                    sh 'docker --version'

            // Utilisez la commande 'docker build' pour construire l'image
                     sh 'docker build -t elairnaoures/gestion-station-ski-1.0 .'

            // Exécutez 'docker images' pour afficher la liste des images Docker
                     //sh 'docker images'
          }
         }




  stage('Deploy image') {
            steps {
        // Assurez-vous que Docker est installé sur l'agent Jenkins


        // Utilisez la commande 'docker build' pour construire l'image
                sh 'docker login -u elairnaoures -p elairnaoures'
                sh 'docker push elairnaoures/gestion-station-ski-1.0 '


      }
     }
    stage('DOCKER COMPOSE') {
            steps {

        // Utilisez la commande 'docker build' pour construire l'image

                sh 'docker compose up -d'


      }
     }

    }
}
