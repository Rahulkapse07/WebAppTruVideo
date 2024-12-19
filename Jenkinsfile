pipeline {
    agent any

    tools {
        maven 'Maven' // Make sure 'Maven3' matches the Maven installation name in Jenkins
        
    }

    environment {
        MAVEN_OPTS = '-Xmx1024m' // Optional: Configure Maven memory options if needed
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
                sh 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                echo 'Running tests...'
                sh 'mvn test -Prc-smoke'
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
