module.exports = {
    reporters: [
      "default", // Keep the default console output
      [
        "jest-html-reporter",
        {
          outputPath: "./Reports/report.html", // Path for the HTML report
          pageTitle: "Frisby Test Report",
          includeFailureMsg: true,
          includeConsoleLog: true, // Includes logs from Frisby
        },
        "jest-junit",
        {
            outputDirectory: "./test-results",
            outputName: "report.xml",
        },
      ],
    ],
  };