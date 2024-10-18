
# LAYP

## Overview

LAYP is a comprehensive Test Automation Framework designed for Web UI Interface Automation. It supports Behavior-Driven Development (BDD) using Cucumber, allows data integration with MariaDB, and generates HTML reports for test execution outcomes.

## Project Setup

### Prerequisites

- Java 1.8 or higher
- Maven 3.6 or higher
- MariaDB
- Cucumber dependencies

### Installation Steps

1. **Clone the repository:**

   ```bash
   git clone <https://github.com/whis-19/LAYP.git>
   cd <LAYP>
   ```

2. **Configure MariaDB:**

   - Ensure MariaDB is running.
   - Create a database for your test data.
   - Update the `DataBaseConnection.java` file with your MariaDB connection details.

3. **Build the project:**

   ```bash
   mvn clean install
   ```

4. **Run the tests:**

   To execute the test cases, use the following command:

   ```bash
   mvn test
   ```

## Framework Features

- **Modular and Scalable**: The framework is designed to accommodate a growing number of test cases and functionalities.
- **BDD Support**: Write test cases using Gherkin language and integrate with Cucumber for BDD.
- **Data-Driven Testing**: Supports Scenario Outline and allows multiple sets of input data.
- **Reporting**: Generates detailed HTML reports using Cucumber's built-in reporting features.
- **Integration with Data Sources**: Fetches test data from MariaDB.

## Usage

To write and execute test cases:

1. Define your scenarios in Gherkin format within the `src/test/resources/features` directory.
2. Implement the step definitions in the `src/test/java/step` directory.
3. Use Page Object Model (POM) to organize your test code in a modular fashion.

## Test Cases

Example test cases include:
- User Login
- Product Search
- Form Submission

## Documentation

For more detailed information on setting up and using the framework, refer to the `docs` folder, which contains:

- Instructions for setting up the environment
- Examples of writing and executing test cases
- Design decisions and architecture overview
- Integration guidelines for data sources and reporting

## Contribution
- Faizan Tariq
- Muhammad Abdullah 
