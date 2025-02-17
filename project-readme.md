# UI Automation Regression Test Framework

## Overview
This project is a UI Automation Testing Framework designed to perform regression testing on web applications. It follows a structured approach using Page Object Model (POM) design pattern and implements various test flows for different website functionalities.

## Project Structure
```
RemotelyUIAutomationTest
├── src
│   └── main
│       └── java
│           └── RemotelyFlows
│               ├── CreateProjects
│               ├── GoToMembers
│               ├── GoToProjects
│               ├── GoToSites
│               ├── GoToTasks
│               ├── LoginAndGetNumberOfActionsInTask
│               ├── LoginToWebsite
│               ├── onBoarding
│               └── TestLoadActionAutomate
├── test
│   └── java
│       └── TestWebsiteFlows
│           ├── DashboardTest
│           ├── LoginTest
│           ├── MembersTest
│           ├── ProjectsTest
│           ├── SitesTest
│           └── TasksTest
├── Reports
│   └── emailable-report.html
├── resources
├── pom.xml
└── Regression.xml
```

## Features
- Automated test flows for:
  - User Login functionality
  - Dashboard navigation and verification
  - Project management operations
  - Site management
  - Task management
  - Member management
- TestNG XML configuration for test execution
- HTML report generation
- Reusable automation flows

## Prerequisites
- Java JDK 8 or higher
- Maven
- IDE (IntelliJ IDEA recommended)
- TestNG framework
- Web browser (Chrome/Firefox)

## Installation
1. Clone the repository:
```bash
git clone [repository-url]
```

2. Navigate to the project directory:
```bash
cd UI-Automation-Regression-Test
```

3. Install dependencies:
```bash
mvn clean install
```

## Test Execution
### Running via TestNG XML
1. Navigate to `Regression.xml`
2. Right-click and select 'Run' to execute all test cases
3. Alternatively, use Maven:
```bash
mvn test -DsuiteXmlFile=Regression.xml
```

### Individual Test Execution
You can run individual test classes from the `test/java/TestWebsiteFlows` directory:
- LoginTest
- DashboardTest
- ProjectsTest
- SitesTest
- TasksTest
- MembersTest

## Test Reports
After test execution, reports can be found in:
- `Reports/emailable-report.html`

## Project Components

### Main Flows (src/main/java/RemotelyFlows)
- `CreateProjects`: Handles project creation workflows
- `LoginToWebsite`: Manages login functionality
- `GoToMembers`, `GoToProjects`, `GoToSites`, `GoToTasks`: Navigation flows
- `TestLoadActionAutomate`: Automation for load testing actions
- `onBoarding`: User onboarding automation

### Test Classes (test/java/TestWebsiteFlows)
- `LoginTest`: Verifies login functionality
- `DashboardTest`: Tests dashboard features
- `ProjectsTest`: Validates project management features
- `SitesTest`: Tests site-related functionalities
- `TasksTest`: Verifies task management features
- `MembersTest`: Tests member management functionality

## Contributing
1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License
[Add your license information here]

## Contact
[Add your contact information here]
