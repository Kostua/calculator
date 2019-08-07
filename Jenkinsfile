pipeline {
	agent any
	stages {
	    stage("Checkout") {
		steps {
			git url: 'https://github.com/Kostua/calculator.git'
		}
	   
	  }
	    stage("Test") {
		steps {
			sh "mvn test"
      }
}

}

}
