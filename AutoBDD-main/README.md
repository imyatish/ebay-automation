# BDD TestNG Framework in Java

## Overview

This project is a sample BDD (Behavior-Driven Development) framework implemented in Java using TestNG. 
## Features

- BDD-style test scenarios using Cucumber.
- TestNG integration for test execution.
- Easily maintainable and scalable test structure.
- Sample test scenarios and steps provided as examples.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java Development Kit (JDK) installed on your system.
- An Integrated Development Environment (IDE) such as IntelliJ IDEA or Eclipse.
- Maven for project dependency management.
- Cucumber for Java, TestNG, and other required dependencies.

## Getting Started

Follow these steps to set up and run the project on your local machine:

1. Download zip file  and create new folder in C- drive and extract all files .

2. Open the project in your preferred IDE.

3. Install the required dependencies.

mvn install (this is very important to run test case)

4. Run the sample BDD tests. I have added run.bat file you can run that file or  you can run all the tests using Maven by right-clicking on the package explorer and selecting "Run As".->Select Maven test

5. Test report you can check in target folder path -target\HtmlReports\report.html


### Below is a step-by-step approach  follow:

Clearly understand the purpose and goals of your testing efforts.Identify the scenarios and behaviors 
Choose the appropriate tools and libraries for your BDD framework. Common choices include Cucumber, Gherkin, TestNG, and Maven.

Project Setup:

Create a new Java project in your preferred IDE (e.g., IntelliJ IDEA, Eclipse).
Set up a Maven project to manage dependencies.

Feature Files:
Create Gherkin feature files that describe the behavior of your application.
Feature files should be placed in the src/test/resources/features 

Step Definitions:
Implement step definitions in Java to define the actual code that runs for each step in the feature files.
Step definitions should be organized in packages (e.g., src/test/java/stepdefinitions).

TestNG Configuration:
Create TestNG XML configuration files to specify which tests to run and set parameters.
Configure test execution settings, parallel execution, and reporting.
Writing BDD Test Scenarios:

In feature files, use Gherkin syntax to write clear and concise test scenarios with Given-When-Then structure.
Each scenario should describe an expected behavior.
Running Tests:

Run the BDD tests through your IDE by right-clicking on the TestNG XML configuration file and selecting "Run."
You can also run tests in terminal using "run" command

Test Reporting:
Generate and view test reports that provide information about the test results, passed and failed scenarios, and execution statistics.







