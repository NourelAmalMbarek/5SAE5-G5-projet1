pipeline {
    agent any

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

                    stage('Test Unit') {
                          steps {
                                sh 'mvn test'
                                }
                                   }

stage('NEXUS') {
   steps {
                sh 'mvn deploy -Dmaven.test.skip=true';
            }
        }

}
}