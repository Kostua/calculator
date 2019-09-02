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
		   steps { 
			withSonarQubeEnv('sonar'){
		        sh 'mvn clean package sonar:sonar'	
			}
		}
	}

	agent {
		docker {
			image 'maven:3-alpine'
			arg '-v /root/.m2:/root/.m2'
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
