pipeline {
    agent any

    stages {
        stage('GIT') {
            steps {
                git branch: 'master',
                url: 'https://github.com/NourelAmalMbarek/5SAE5-G5-projet1.git'
            }
        }
    stage('MVN CLEAN ') {
            steps {
                sh 'mvn clean '
            }
        }
        stage('MVN COMPILE ') {
            steps {
                sh 'mvn  compile'
            }
        }
     }
  }
        

