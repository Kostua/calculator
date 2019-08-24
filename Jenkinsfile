pipeline {
	agent any
	tools {
		maven 'maven 3.5.4'
		jdk 'java_11'
		}
    triggers {
        pollSCM('H/15 * * * *')
    }


	stages {
	    stage ('Initialize') {
		steps {
			sh '''
			   echo "PATH = ${PATH}"
			   echo "M2_HOME = ${M2_HOME}"
			'''
			}
		}
	    stage("Checkout") {
		steps {
			git url: 'https://github.com/Kostua/calculator.git'
		}
	   
	  }
	    stage('SonarQube analysis') {
		   enviroment {
			scannerHome = tool 'sonar_qube_scanner'
				}
		   steps { 
			withSonarQubeEnv('sonar')
			sh '${scannerHome}/bin/sonar-scanner'
		}
	}

	    stage("Build") {
		steps {
		  sh 'mvn -B -Dmaven.test.failure.ignore=true install' 
     		 }
		post {
		   success {
			junit 'target/surefire-reports/**/*.xml'
			}
		}
	   }



}

}
