# UI Automation Testing Framework

## Description
This is a modular and scalable UI Automation Testing Framework built using **Selenium**, **TestNG**, and **Java**. The framework is designed to automate end-to-end testing for web applications, ensuring efficiency, accuracy, and maintainability.

The framework includes:
- Test cases for core functionalities like login, dashboard, projects, sites, tasks, and members.
- Modular structure for reusable flows.
- Test execution and reporting powered by TestNG.


## Features
- **Automation Tools**: Selenium and TestNG.
- **Programming Language**: Java.
- **Framework Highlights**:
  - Modular structure with reusable components.
  - TestNG configuration for managing test suites.
  - Easily extendable for new test cases.
- **Supported Scenarios**:
  - Functional UI testing.
  - Validation of multiple web application features (e.g., login, dashboard workflows).
- **Test Execution**:
  - Configurable through TestNG XML files.
  - Parallel and sequential execution supported.


## Project Structure

src
├── main
│   ├── java
│   │   ├── RemotelyFlows
│   │   │   ├── GoToMembers.java
│   │   │   ├── GoToProjects.java
│   │   │   ├── GoToSites.java
│   │   │   ├── LoginToWebsite.java
│   │   │   └── ...
│   │   └── utils
│   │       ├── ConfigManager.java
│   │       ├── WebDriverManager.java
│   │       └── LoggerUtil.java
├── test
│   ├── java
│   │   ├── TestWebsiteFlows
│   │   │   ├── LoginTest.java
│   │   │   ├── DashboardTest.java
│   │   │   ├── ProjectsTest.java
│   │   │   ├── SitesTest.java
│   │   │   └── TasksTest.java
│   └── resources
│       ├── TestNG.xml
│       ├── config.properties
│       └── ...


## Getting Started
Follow the steps below to set up and run the framework.

### Prerequisites
- Java (version 11 or higher)
- Maven
- TestNG plugin for your IDE (e.g., IntelliJ IDEA, Eclipse)
- WebDriver executables for supported browsers (e.g., ChromeDriver, GeckoDriver)

### Installation
1. Clone this repository:

2. Import the project into your preferred IDE as a Maven project.

3. Configure `config.properties`:
base.url=https://app.remotely.store
username=your_email@example.com
password=your_password
browser=chrome


## Running Tests

### Using TestNG XML
1. Open the `TestNG.xml` file in the `test/resources` folder.
2. Run the test suite by right-clicking the XML file and selecting `Run`.

### Using Maven
You can execute tests via the command line:
mvn clean test



---

#### **Tools and Technologies**

Selenium: For browser automation.
TestNG: For test execution and reporting.
Java: Core programming language.
Maven: For dependency management and build automation


