# RankBook HRMS Automation Framework

[![Regression Pipeline](https://github.com/DarshanVishu/RankBookHRMS-Automation/actions/workflows/regression.yml/badge.svg)](https://github.com/DarshanVishu/RankBookHRMS-Automation/actions/workflows/regression.yml)
[![Live Allure Report](https://img.shields.io/badge/Allure%20Report-Live%20Dashboard-blue?logo=allure)](https://darshanvishu.github.io/RankBookHRMS-Automation/)
[![Java](https://img.shields.io/badge/Java-17-orange?logo=openjdk)](https://www.oracle.com/java/)
[![Selenium](https://img.shields.io/badge/Selenium-4.19.1-green?logo=selenium)](https://www.selenium.dev/)
[![REST Assured](https://img.shields.io/badge/REST%20Assured-5.4.0-red)](https://rest-assured.io/)
[![Appium](https://img.shields.io/badge/Appium-9.2.2-purple?logo=appium)](https://appium.io/)
[![TestNG](https://img.shields.io/badge/TestNG-7.9.0-yellow)](https://testng.org/)

An enterprise-grade hybrid test automation framework designed to validate Web UI, RESTful APIs, and Mobile endpoints for the RankBook HRMS application. Built with modularity, headless CI/CD execution, and automated Allure dashboard publishing.

---

## 📊 Live Interactive Dashboard
Executions on the `main` branch automatically deploy comprehensive Allure analytics:
👉 **[View Live Allure Execution Report](https://darshanvishu.github.io/RankBookHRMS-Automation/)**

---

## 🏛️ Framework Architecture

```text
RankBookHRMS-Automation/
├── .github/workflows/
│   └── regression.yml          # GitHub Actions CI/CD Pipeline & Pages deployment
├── src/
│   ├── main/java/com/rankbook/hrms/
│   │   ├── api/                # REST Assured client, request specs, and payload handlers
│   │   ├── config/             # Environment, properties, and configuration managers
│   │   ├── drivers/            # Driver factory (Selenium WebDriver, Playwright, Appium)
│   │   ├── pages/              # Page Object Model (POM) page classes & locators
│   │   └── utils/              # Data readers (Apache POI), listeners, and screenshot utilities
│   └── test/
│       ├── java/com/rankbook/hrms/automation/tests/
│       │   ├── api/            # API endpoint and contract validation tests
│       │   ├── base/           # BaseTest classes for session setup/teardown
│       │   ├── e2e/            # Full-flow end-to-end integration scenarios
│       │   ├── mobile/         # Appium mobile tests
│       │   └── web/            # Selenium Web UI functional test suites
│       └── resources/
│           ├── config.properties
│           ├── testng-all.xml  # Master suite runner (Web + API + E2E)
│           ├── testng-api.xml  # API-specific regression suite
│           ├── testng-e2e.xml  # E2E integration scenarios
│           ├── testng-mobile.xml # Mobile application test runner
│           └── testng-web.xml  # Web UI regression suite
├── pom.xml                     # Maven project descriptor & dependency management
└── README.md
🚀 Key Technical Highlights
Multi-Layer Coverage: Validates frontend UI (Selenium / Playwright), backend REST APIs (REST Assured), and native apps (Appium).

Headless CI-Ready Execution: Fully containerized Chrome runs in headless mode inside Ubuntu GitHub Actions runners.

AspectJ Weaved Allure Reporting: In-flight capture of screenshots, request/response headers, parameters, and step-by-step logs.

Process Isolation: Surefire configured with isolated forks (forkCount=1, reuseForks=false) to eliminate thread contamination.

Live GitHub Pages Publishing: Artifacts auto-deploy to GitHub Pages on every push, preserving failure reports without stalling the pipeline.

🛠️ Prerequisites & Local Setup
Requirements
JDK: Version 17 or higher

Build Tool: Apache Maven 3.8+

Browser: Google Chrome (latest stable)

Clone & Install
Bash
git clone [https://github.com/DarshanVishu/RankBookHRMS-Automation.git](https://github.com/DarshanVishu/RankBookHRMS-Automation.git)
cd RankBookHRMS-Automation
mvn clean install -DskipTests
⚡ Running Test Suites Locally
Run targeted suites using Maven system properties:

1. Web UI Tests (Headless or Browser Mode)
Bash
# Headless mode
mvn clean test -DsuiteXmlFile="src/test/resources/testng-web.xml" -Dheadless=true

# Headed mode (opens local browser)
mvn clean test -DsuiteXmlFile="src/test/resources/testng-web.xml" -Dheadless=false
2. API Contract & Functional Tests
Bash
mvn clean test -DsuiteXmlFile="src/test/resources/testng-api.xml"
3. End-to-End (E2E) Flow Tests
Bash
mvn clean test -DsuiteXmlFile="src/test/resources/testng-e2e.xml" -Dheadless=true
4. Full Master Suite
Bash
mvn clean test -DsuiteXmlFile="src/test/resources/testng-all.xml" -Dheadless=true
📈 Generating Allure Reports Locally
To spin up a local lightweight server displaying execution graphs and logs:

Bash
# Generate report files from raw execution data
mvn allure:report

# Serve the report directly in your default browser
mvn allure:serve
🔄 CI/CD Automation Pipeline
The repository uses GitHub Actions (.github/workflows/regression.yml) to orchestrate runs:

Trigger: Automated on push/pull requests to main, and manual execution via workflow_dispatch.

Selective Execution: Supports on-demand suite selection directly from the GitHub UI dropdown (testng-web.xml, testng-api.xml, testng-e2e.xml, or testng-all.xml).

Report Hosting: Packages the generated HTML report and publishes it to the github-pages environment.


---

### How to Update

To update your repository's `README.md`:

```powershell
# In your local project directory:
Set-Content -Path "README.md" -Value @'
<PASTE ENTIRE MARKDOWN ABOVE HERE>
'@ -Encoding UTF8

git add README.md
git commit -m "docs: enrich readme with architecture diagram, prerequisites, and execution guides"
git push origin main
