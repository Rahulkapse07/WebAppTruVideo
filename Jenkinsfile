pipeline {
    agent any

    environment {
        SUITE_FILE = 'TestRunnerSuites/RCSmokeSuite.xml'  // Path to your XML file
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout the code from the repository
                git branch: 'main', url: 'https://github.com/Rahulkapse07/WebAppTruVideo.git'
            }
        }
        
        stage('Run Tests') {
            steps {
                // Run the tests with Maven using the XML suite file
                sh "mvn clean test -DsuiteXmlFile=${SUITE_FILE}"  // Modify the path if needed
            }
        }
    }

    post {
        always {
            echo 'Pipeline complete.'
        }
        success {
            echo 'Tests passed successfully.'
        }
        failure {
            echo 'Tests failed.'
        }
    }
}
