pipeline {
    agent any
    parameters {
        string(name: 'BRANCH_NAME', defaultValue: 'main', description: 'Branch to build')
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: params.BRANCH_NAME, url: 'https://github.com/Jerist/task_planer.git'
            }
        }
        stage('Compile') {
            steps {
                sh 'mvn clean compile'
            }
        }
        stage('Test') {
            when { expression { params.BRANCH_NAME.startsWith('feature/') } }
            steps {
                sh 'mvn test'
            }
        }
        stage('Static Analysis') {
            when { expression { params.BRANCH_NAME == 'developer' } }
            steps {
                sh 'mvn checkstyle:check || true'
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
                sh 'mkdir -p ./published && cp cli/target/*.jar ./published/'
                archiveArtifacts artifacts: 'published/*.jar'
            }
        }
    }
    post {
        success { echo 'Pipeline SUCCESS' }
        failure { echo 'Pipeline FAILED' }
    }
}