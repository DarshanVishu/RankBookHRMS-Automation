# RankBook HRMS Automation Framework

[![Regression Pipeline](https://github.com/DarshanVishu/RankBookHRMS-Automation/actions/workflows/regression.yml/badge.svg)](https://github.com/DarshanVishu/RankBookHRMS-Automation/actions/workflows/regression.yml)
[![Live Allure Report](https://img.shields.io/badge/Allure%20Report-Live%20Dashboard-blue?logo=allure)](https://darshanvishu.github.io/RankBookHRMS-Automation/)
[![Java](https://img.shields.io/badge/Java-17-orange?logo=openjdk)](https://www.oracle.com/java/)
[![Playwright](https://img.shields.io/badge/Playwright-1.50.0-2EAD33?logo=playwright)](https://playwright.dev/java/)
[![Selenium](https://img.shields.io/badge/Selenium-4.19.1-green?logo=selenium)](https://www.selenium.dev/)
[![REST Assured](https://img.shields.io/badge/REST%20Assured-5.4.0-red)](https://rest-assured.io/)
[![Appium](https://img.shields.io/badge/Appium-9.2.2-purple?logo=appium)](https://appium.io/)
[![TestNG](https://img.shields.io/badge/TestNG-7.9.0-yellow)](https://testng.org/)

An enterprise-grade hybrid test automation framework designed to validate Desktop Web UI, Mobile Safari (iOS WebKit), RESTful APIs, and Native Mobile endpoints for the **RankBook HRMS** application. Built with modularity, headless CI/CD execution, and automated Allure dashboard publishing.

---

## 📊 Live Interactive Dashboard

Executions on the `main` branch automatically deploy merged Desktop + iOS Safari Allure analytics:  
👉 **[View Live Allure Execution Report](https://darshanvishu.github.io/RankBookHRMS-Automation/)**

---

## 🏛️ Framework Architecture

```text
RankBookHRMS-Automation/
├── .github/workflows/
│   └── regression.yml                 # Multi-job CI/CD Pipeline & GitHub Pages deployment
├── src/
│   ├── main/java/com/rankbook/hrms/
│   │   ├── api/                       # REST Assured client, request specs, and payload handlers
│   │   ├── config/                    # Environment, properties, and configuration managers
│   │   ├── drivers/                   # Driver factory (Selenium WebDriver, Playwright WebKit, Appium)
│   │   ├── pages/                     # Page Object Model (POM) page classes & locators
│   │   └── utils/                     # Data readers (Apache POI), listeners, and screenshot utilities
│   └── test/
│       ├── java/com/rankbook/hrms/automation/tests/
│       │   ├── api/                   # API endpoint and contract validation tests
│       │   ├── base/                  # BaseTest classes for session setup/teardown
│       │   ├── e2e/                   # Full-flow end-to-end integration scenarios
│       │   ├── mobile/                # iOS Safari (WebKit) & Appium mobile test suites
│       │   └── web/                   # Desktop Web UI functional test suites
│       └── resources/
│           ├── config.properties
│           ├── testng-all.xml         # Master suite runner (Web + API + E2E)
│           ├── testng-api.xml         # API-specific regression suite
│           ├── testng-e2e.xml         # E2E integration scenarios
│           ├── testng-ios.xml         # iOS Safari (WebKit) mobile web test runner
│           ├── testng-mobile.xml      # Native mobile application test runner
│           └── testng-web.xml         # Web UI regression suite
├── pom.xml                            # Maven project descriptor & dependency management
└── README.md

```

---

## 🚀 Key Technical Highlights

* **Multi-Layer Coverage:** Validates frontend Desktop Web (Selenium / Playwright), Mobile Safari WebKit (`393x852` touch viewport & overflow checks), backend REST APIs (REST Assured), and native mobile apps (Appium).
* **Headless CI-Ready Execution:** Runs Chrome and native Playwright WebKit in headless mode across Ubuntu GitHub Actions runners (`ubuntu-latest` and `ubuntu-24.04`).
* **AspectJ Weaved Allure Reporting:** In-flight capture of screenshots, Playwright `.zip` execution traces, request/response headers, parameters, and step-by-step logs.
* **Process Isolation:** Surefire configured with isolated forks (`forkCount=1`, `reuseForks=false`) to eliminate thread contamination.
* **Live GitHub Pages Publishing:** Merges desktop and iOS Safari execution artifacts and auto-deploys to GitHub Pages on every push to `main`.

---

## 🛠️ Prerequisites & Local Setup

### Requirements

* **JDK:** Version 17 or higher
* **Build Tool:** Apache Maven 3.8+
* **Browser:** Google Chrome (latest stable) + Playwright WebKit binary

### Clone & Install

```bash
git clone [https://github.com/DarshanVishu/RankBookHRMS-Automation.git](https://github.com/DarshanVishu/RankBookHRMS-Automation.git)
cd RankBookHRMS-Automation
mvn clean install -DskipTests

```

### Install Playwright WebKit Binary (For iOS Safari Suite)

```bash
mvn test-compile exec:java -e \
  -Dexec.classpathScope=test \
  -Dexec.mainClass=com.microsoft.playwright.CLI \
  -Dexec.args="install webkit"

```

---

## ⚡ Running Test Suites Locally

Run targeted suites using Maven system properties:

### 1. Web UI Tests (Headless or Browser Mode)

```bash
# Headless mode
mvn clean test -DsuiteXmlFile="src/test/resources/testng-web.xml" -Dheadless=true

# Headed mode (opens local browser)
mvn clean test -DsuiteXmlFile="src/test/resources/testng-web.xml" -Dheadless=false

```

### 2. iOS Safari (WebKit) Mobile Web Tests

```bash
mvn test -DsuiteXmlFile="src/test/resources/testng-ios.xml" -Dheadless=true

```

### 3. API Contract & Functional Tests

```bash
mvn clean test -DsuiteXmlFile="src/test/resources/testng-api.xml"

```

### 4. End-to-End (E2E) Flow Tests

```bash
mvn clean test -DsuiteXmlFile="src/test/resources/testng-e2e.xml" -Dheadless=true

```

### 5. Full Master Suite

```bash
mvn clean test -DsuiteXmlFile="src/test/resources/testng-all.xml" -Dheadless=true

```

---

## 📈 Generating Allure Reports Locally

To spin up a local lightweight server displaying execution graphs and logs:

```bash
# Generate report files from raw execution data
mvn allure:report

# Serve the report directly in your default browser
mvn allure:serve

```

---

## 🔄 CI/CD Automation Pipeline

The repository uses GitHub Actions (`.github/workflows/regression.yml`) to orchestrate runs:

* **Trigger:** Automated on `push` / `pull_request` to `main`, and manual execution via `workflow_dispatch`.
* **Parallel Jobs:** Executes Desktop Web regression (`ubuntu-latest`) in parallel with iOS Safari WebKit mobile tests (`ubuntu-24.04`).
* **Selective Execution:** Supports on-demand suite selection directly from the GitHub UI dropdown (`testng-web.xml`, `testng-ios.xml`, `testng-api.xml`, `testng-e2e.xml`, or `testng-all.xml`).
* **Report Hosting:** Merges desktop and mobile raw results into a unified HTML Allure report and publishes it to the `github-pages` environment.

---

## 👨‍💻 Author

**Darshan Vishu** — QA Automation | Software Development Engineer in Test (SDET)

* GitHub: [@DarshanVishu](https://github.com/DarshanVishu)

```

---

### Update & Push in PowerShell

```powershell
git add README.md
git commit -m "docs: fix markdown formatting and add Playwright/iOS Safari details to README"
git push origin main

```
