pipeline {
    agent any

    stages {
        stage('GIT') {
            steps {
                git branch: 'RimaChemengui-5SAE5-G5',
                url: 'https://github.com/NourelAmalMbarek/5SAE5-G5-projet1/tree/RimaChemengui-5SAE5-G5'
            }
        }
        stage('MVN CLEAN AND COMPILE') {
            steps{
                sh 'mvn clean compile'
            }
        }

      
}
}
