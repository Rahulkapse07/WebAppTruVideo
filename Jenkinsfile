pipeline {
   agent any
   
   environment {
      BASE_URL = 'https://rc.truvideo.com/login'
      SUITE_FILE = 'TestRunnerSuites/RCSmokeSuite.xml'
   }
   
   parameters {
      string(name: 'baseUrl', defaultValue: BASE_URL, description: 'Base URL for the application')
   }
   
   stages {
      stage('Checkout') {
         steps {
            // Checkout the code from the repository
            git branch: 'main', url: 'https://github.com/Rahulkapse07/WebAppTruVideo.git'
         }
      }
      
      stage('Install Dependencies') {
         steps {
            // Install dependencies (e.g., Maven dependencies)
            sh 'mvn clean install'
         }
      }
      
      stage('Run Tests') {
         steps {
            // Run the tests with Maven, passing the suite XML file and the base URL parameter
            sh "mvn clean test -DsuiteXmlFile=${SUITE_FILE} -DbaseUrl=${params.baseUrl}"
         }
      }
      
      stage('Archive Test Results') {
         steps {
            // Publish JUnit test results to Jenkins
            junit '**/target/test-*.xml'
         }
      }
   }
   
   post {
      always {
         echo 'Pipeline complete.'
      }
      success {
         echo 'Build and tests passed successfully.'
      }
      failure {
         echo 'Build or tests failed.'
      }
   }
}