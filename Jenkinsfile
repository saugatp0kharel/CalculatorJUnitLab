pipeline {
  agent any
  tools { 
    jdk 'JDK17'          // must match the name in Manage Jenkins → Global Tool Configuration
    maven 'Maven_3_9'    // must match your Maven installation name
  }

  stages {
    stage('Checkout') {
      steps {
        // If your job is "Pipeline script from SCM", use checkout scm
        checkout scm
        // If you run a plain Pipeline job and need to hardcode the repo, use this line instead:
        // git branch: 'main', url: 'https://github.com/saugatp0kharel/CalculatorJUnitLab.git'
      }
    }

    stage('Test & Generate Coverage XML') {
      steps {
        // This creates target/site/jacoco/jacoco.xml and index.html
        sh 'mvn -B clean test jacoco:report'
      }
    }

    stage('Publish Reports') {
      steps {
        // Publish JUnit
        junit 'target/surefire-reports/*.xml'

        // ✅ Use Coverage Plugin (NOT the old JaCoCo plugin)
        recordCoverage(tools: [jacoco(pattern: 'target/site/jacoco/jacoco.xml')])

        // Keep HTML report as artifact (optional but nice for screenshots)
        archiveArtifacts artifacts: 'target/site/jacoco/**', fingerprint: true
      }
    }
  }

  post {
    success { echo '✅ Build and coverage completed successfully!' }
    failure { echo '❌ Build or tests failed. Check logs!' }
  }
}
