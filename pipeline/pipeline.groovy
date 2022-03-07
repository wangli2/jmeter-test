#!/usr/bin/env groovy
node {

    stage('Initialise') {
        /* Checkout the scripts */
        checkout scm: [
                $class: 'GitSCM',
                userRemoteConfigs: [
                        [
                                url: "https://github.com/wangli2/jmeter-test.git",
                                credentialsId: "wangli2"
                        ]
                ],
                branches: [[name: "master"]]
        ], poll: false
    }

    stage('Complete any setup steps') {
        echo "Complete set-up steps"
        echo "${octoperf_test_value}"
    }

    stage('Execute Performance Tests') {
        dir("${WORKSPACE}/tc") {
            bat "c:/apache-jmeter-5.4.3/bin/jmeter.bat -n -t xxtest.jmx -l xxtest.jtl -Joptestvalue=${octoperf_test_value}"
        }
    }

    stage('Analyse Results') {
        echo "Analyse results"
    }
}
