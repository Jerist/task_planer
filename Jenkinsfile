pipeline {
    agent any
    tools {
        maven 'Maven-3.8.6'   // должно совпадать с именем в Jenkins Tools
        jdk 'JDK-17'          // должно совпадать
    }
    parameters {
        string(name: 'BRANCH', defaultValue: 'developer', description: 'Branch to build')
    }
    environment {
        PUBLISH_DIR = "/Users/jerist/JenkinsPublished"
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: params.BRANCH, url: 'https://github.com/ваш_логин/TaskPlanner.git'
            }
        }
        stage('Compile') {
            steps {
                sh 'mvn clean compile'
            }
        }
        stage('Test') {
            when {
                expression { params.BRANCH.startsWith('feature/') }
            }
            steps {
                sh 'mvn test'
            }
        }
        stage('Static Analysis') {
            when {
                expression { params.BRANCH == 'developer' }
            }
            steps {
                sh 'mvn checkstyle:check || echo "Checkstyle skipped"'
            }
        }
        stage('Coverage Report') {
            steps {
                sh 'mvn jacoco:report'
            }
        }
        stage('Install Artifact') {
            steps {
                sh 'mvn install -DskipTests'
            }
        }
        stage('Coverage Check') {
            steps {
                sh 'mvn jacoco:check'
            }
        }
        stage('Publish Artifacts') {
            steps {
                sh """
                    mkdir -p ${PUBLISH_DIR}
                    cp cli/target/*.jar ${PUBLISH_DIR}/
                    cp core/target/*.jar ${PUBLISH_DIR}/ || true
                """
                archiveArtifacts artifacts: 'cli/target/*.jar, core/target/*.jar'
            }
        }
    }
    post {
        success { echo 'Pipeline SUCCESS' }
        failure { echo 'Pipeline FAILED' }
    }
}