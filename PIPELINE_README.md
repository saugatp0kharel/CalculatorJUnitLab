# Jenkins Pipeline Setup for CalculatorJUnitLab 🧮

This file documents how the Jenkins pipeline is configured to build, test, and generate JaCoCo code coverage reports for this project.

---

## 🔧 Pipeline Overview

**Tools Used**
- Jenkins (CI/CD)
- Maven (Build Automation)
- JaCoCo (Code Coverage)
- GitHub (Source Control)

**Stages in the Pipeline**
1. **Checkout Code:** Pulls the latest code from GitHub.
2. **Build & Test:** Runs `mvn clean verify` to build and execute JUnit tests.
3. **Generate Coverage Report:** Uses JaCoCo plugin to generate test coverage reports.
4. **Post Actions:** 
   - ✅ On success → Displays success message.
   - ❌ On failure → Displays failure message.

---

## 📊 Quality Gate Configuration

The Jenkins JaCoCo plugin is configured to **fail the build if line coverage < 95%**.

| Setting | Value |
|----------|--------|
| Threshold | 95 |
| Baseline | Overall project |
| Metric | Line Coverage |
| Action | Fail the build if below threshold |

---

## 📁 File Summary

| File | Purpose |
|------|----------|
| `pom.xml` | Maven configuration with JaCoCo plugin |
| `Jenkinsfile` | Defines pipeline stages |
| `PIPELINE_README.md` | Documentation of CI/CD setup |

---

### ✅ Expected Jenkins Output

