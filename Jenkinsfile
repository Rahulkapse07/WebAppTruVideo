pipeline {
    agent any

    tools {
        maven 'Maven' // Ensure 'Maven' matches the Maven installation name in Jenkins
        
    }

    environment {
        MAVEN_OPTS = '-Xmx1024m' // Optional: Configure Maven memory options if needed
         DISPLAY = ':99'
    }

    stages {
        stage('Checkout Code') {
            steps {
                echo 'Checking out source code...'
                checkout scm
            }
        }
        stage('Build') {
            steps {
                echo 'Building the project...'
                sh 'mvn test -Prc-smoke'
            }
        }
        stage('Test') {
            steps {
                echo 'Running tests with xvfb...'
               sh 'xvfb-run -a mvn test -Prc-smoke'
            }
        }
        stage('Archive') {
            steps {
                echo 'Archiving artifacts and test results...'
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
                junit '**/target/surefire-reports/*.xml'
            }
        }
    }

    post {
        always {
            echo 'Cleaning up workspace...'
            cleanWs()
        }
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed. Check logs for details.'
        }
    }
}
