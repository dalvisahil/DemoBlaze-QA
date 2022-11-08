Installation (pre-requisites)
JDK 1.8+ (make sure Java class path is set)
Maven (make sure .m2 class path is set)
IntelliJ / Eclipse
Browser driver (make sure you have your desired browser driver and class path is set)
Run Tests
Open terminal (MAC OSX) or command prompt / power shell (for windows OS) and navigate to the project directory type mvn clean test command to run features. With this command it will invoke the default Chrome  browser and will execute the tests.
For reports please check "reports/cucumber-html-reports/html-report.html"

Please check "Excel-Files" folder for test cases and Bugs/Improvements