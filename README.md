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
