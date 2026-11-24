pipeline {
    agent any

    tools {
        jdk 'JDK21'
        maven 'M3'
    }

    triggers {
        pollSCM('* * * * *')
    }

    stages {
        stage('Checkout Backend') {
            steps {
                git branch: 'main', url: 'https://github.com/RachedJouini/devops-app-gestion-projets.git'
            }
        }

        stage('Tests Unitaires') {
                    steps {
                        sh '''
                            TEST_FLAGS="-Dtest=*Test -Dsurefire.failIfNoSpecifiedTests=false"
                            if [ -f pom.xml ]; then
                                mvn clean test $TEST_FLAGS
                            elif [ -f backend/pom.xml ]; then
                                cd backend && mvn clean test $TEST_FLAGS
                            elif [ -f backend/backend/pom.xml ]; then
                                cd backend/backend && mvn clean test $TEST_FLAGS
                            else
                                echo "pom.xml introuvable !" && exit 1
                            fi
                        '''
                    }
                    post {
                        always {
                            junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
                        }
                    }
                }

        stage('Package (Livrable target/)') {
            steps {
                sh '''
                    if [ -f pom.xml ]; then
                        mvn package -DskipTests
                    elif [ -f backend/pom.xml ]; then
                        cd backend && mvn package -DskipTests
                    elif [ -f backend/backend/pom.xml ]; then
                        cd backend/backend && mvn package -DskipTests
                    fi
                '''
            }
        }
    }

    post {
        success {
            archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            echo 'Livrable généré dans target/'
        }
        failure {
            echo 'Échec du build : vérifier Console Output'
        }
    }
}