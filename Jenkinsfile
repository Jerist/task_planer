pipeline {
    agent any
    tools {
        maven 'Maven-3.8.6'
        jdk 'JDK-17'
    }
    parameters {
        string(name: 'BRANCH_NAME', defaultValue: 'main', description: 'Branch to build')
    }
    environment {
        PUBLISH_DIR = "/Users/jerist/JenkinsPublished"
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
            steps {
                sh 'mvn test'
            }
        }
        stage('Coverage Report') {
            steps {
                sh 'mvn jacoco:report'
            }
        }
        stage('Install') {
            steps {
                sh 'mvn install -Dmaven.test.skip=true'  // тесты уже запущены, можно пропустить
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