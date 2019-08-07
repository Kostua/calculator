pipeline {
	agent any
	stages {
	    stage("Checkout") {
		steps {
			git url: 'https://github.com/Kostua/calculator.git'
		}
	   
	  }
	    stage("Build") {
		steps {
			sh "sh ./mvn clean install -DskipTests"
      }
}

}

}
