pipeline {
   agent any
   
   environment {
      baseurl = 'https://rc.truvideo.com/login'  // Lowercase environment variable
      SUITE_FILE = 'TestRunnerSuites/RCSmokeSuite.xml'
   }
   
   parameters {
      string(name: 'baseUrl', defaultValue: "${baseurl}", description: 'Base URL for the application')  // Reference environment variable
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
            sh 'mvn clean install'  // Use the 'sh' step instead of 'start'
         }
      }
      
      stage('Run Tests') {
         steps {
            // Run the tests with Maven, passing the suite XML file and the base URL parameter
            sh "mvn clean test -DsuiteXmlFile=${SUITE_FILE} -DbaseUrl=${baseUrl}"  // Use the 'sh' step instead of 'start'
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
