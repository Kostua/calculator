pipeline {
    agent any
    stages {
	stage("Compile") {
		steps {
			sh "./mvn build"
		}
	}
	stage("Unit test") {
	     steps {
			sh "./mvn test"
		}
	   }
	}
}
