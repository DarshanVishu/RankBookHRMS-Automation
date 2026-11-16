pipeline {
    agent any

    parameters {
        choice(
            name: 'SUITE_XML',
            choices: [
                'src/test/resources/testng-web.xml',
                'src/test/resources/testng-ios.xml',
                'src/test/resources/testng-api.xml',
                'src/test/resources/testng-e2e.xml',
                'src/test/resources/testng-all.xml'
            ],
            description: 'Select the TestNG Suite XML to execute'
        )
        booleanParam(
            name: 'HEADLESS',
            defaultValue: true,
            description: 'Run browsers in headless mode'
        )
        string(
            name: 'BASE_URL',
            defaultValue: '',
            description: 'Optional override for baseUrl (leave blank to use FrameworkConstants default)'
        )
    }

    environment {
        PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD = '1'
        ALLURE_RESULTS_DIR = 'target/allure-results'
    }

    options {
        buildDiscarder(logRotator(numToKeepStr: '15'))
        timeout(time: 45, unit: 'MINUTES')
        timestamps()
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Verify Toolchain') {
            steps {
                script {
                    if (isUnix()) {
                        sh '''
                            java -version
                            mvn -version
                        '''
                    } else {
                        bat '''
                            java -version
                            mvn -version
                        '''
                    }
                }
            }
        }

        stage('Execute TestNG Suite') {
            steps {
                script {
                    def baseUrlArg = params.BASE_URL?.trim() ? "-DbaseUrl=${params.BASE_URL.trim()}" : ""
                    def mvnCmd = "mvn clean test -DsuiteXmlFile=${params.SUITE_XML} -Dheadless=${params.HEADLESS} -Dallure.results.directory=${ALLURE_RESULTS_DIR} ${baseUrlArg}"

                    if (isUnix()) {
                        sh "${mvnCmd}"
                    } else {
                        bat "${mvnCmd}"
                    }
                }
            }
        }
    }

    post {
        always {
            script {
                // Merge root allure-results if generated outside target/
                if (isUnix()) {
                    sh '''
                        if [ -d "allure-results" ] && [ "$(ls -A allure-results 2>/dev/null)" ]; then
                            mkdir -p target/allure-results
                            cp -r allure-results/* target/allure-results/
                        fi
                    '''
                } else {
                    bat '''
                        if exist allure-results (
                            if not exist target\\allure-results mkdir target\\allure-results
                            xcopy /E /I /Y allure-results target\\allure-results >nul
                        )
                    '''
                }
            }

            junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'

            allure includeProperties: false,
                   jdk: '',
                   results: [[path: 'target/allure-results']]

            archiveArtifacts artifacts: 'target/playwright-traces/**, target/surefire-reports/**',
                             allowEmptyArchive: true
        }
    }
}
