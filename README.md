# LAYP (Looks Like a You Problem)

Welcome to the **LAYP** repository! This project is all about quality assurance and testing, making sure your software doesn’t just work, but works well. Let’s get into it!

## 📁 Project Structure

This repository contains:

1. **Java Maven Project for UI Testing**
   - A comprehensive setup for automated UI testing using Java and Maven. 

2. **Java Project for Unit Testing**
   - Focused on ensuring your backend logic is rock solid with unit tests written in Java.

3. **API Testing**
   - Utilizing:
     - **Jest**: For testing JavaScript code, perfect for frontend and backend.
     - **K6**: Load testing tool to ensure your APIs can handle the heat.
     - **Frisby**: A REST API testing framework built on Jest for seamless integration.

## 🚀 Getting Started

### Prerequisites

Make sure you have the following installed:

- Java (JDK 8 or higher)
- Maven
- Node.js (for Jest and Frisby)
- K6

### Installation

1. **Clone the repo:**
   ```bash
   git clone https://github.com/yourusername/layp.git
   cd layp
   ```

2. **Setup Java Maven Project for UI Testing:**
   - Navigate to the UI testing directory and run:
   ```bash
   mvn clean install
   ```

3. **Setup Java Project for Unit Testing:**
   - Navigate to the unit testing directory and run:
   ```bash
   mvn test
   ```

4. **Setup API Testing:**
   - Navigate to the API testing folder and run the following for Jest:
   ```bash
   npm install
   npm test
   ```
   - For K6, run:
   ```bash
   k6 run your_test_file.js
   ```

## 🧪 Running Tests

- **UI Tests**: 
  ```bash
  mvn test -Dtest=YourUITestClass
  ```

- **Unit Tests**: 
  ```bash
  mvn test -Dtest=YourUnitTestClass
  ```

- **API Tests**: 
  ```bash
  npm test
  ```

## 📜 Contributing

Got a suggestion or a bug report? Make a PR, but remember: if it’s broken, it’s probably your problem now. 😈

## 📫 Contact

For any questions, hit us up at 
[f223714@cfd.nu.edu.pk](mailto:f223714@cfd.nu.edu.pk)
[f223722@cfd.nu.edu.pk](mailto:f223722@cfd.nu.edu.pk)



---

Let’s make your software shine bright like a diamond! 💎✨ Happy testing!
