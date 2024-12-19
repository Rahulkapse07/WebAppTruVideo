pipeline:
agent:
any

environment:
DISPLAY: ":99"

stages:
- stage:  Checkout Code
steps:
- checkout scm

- stage: Setup Environment
steps:
- sh 'sudo apt-get update'
- sh 'sudo apt-get install -y xvfb'
- sh 'Xvfb :99 -screen 0 1024x768x16 &'

- stage: Install Dependencies
steps:
- sh 'mvn clean install'

- stage: Run Tests
steps:
- sh 'mvn test -Prc-smoke'

- stage: Archive Results
steps:
- archiveArtifacts artifacts: '**/target/surefire-reports/*.xml', fingerprint: true
- junit '**/target/surefire-reports/*.xml'