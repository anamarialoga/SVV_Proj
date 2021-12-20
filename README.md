#SVV PROJECT

Implementing a web server and some unit tests;;

* Java Version: Java 1.8 (Java Version 1.8.0_251)

* Dependencies (using Maven):
    * Mockito-core - Version 4.0.0
    * JUnit - Version 4.1.3
    * Spotbugs - Version 4.5.0
    * Spotbugs Maven
    
* Plugin :  
    * Lombok 
    * Spotbugs - Version 1.2.5


#IMPLEMENTATION

*  Web server ✓
*  Unit Tests ✓
*  GUI  ✓

#COVERAGE

![ScreenShot](screenshots/coverage1.PNG)
![ScreenShot](screenshots/coverage2.PNG)

#SPOTBUGS INCLUDES

![ScreenShot](screenshots/spotBugsIncludes.PNG)

#STATIC ANALYSIS - SPOTBUGS

![ScreenShot](screenshots/bugs/BadPractice/run()invokesSystemExit.PNG)
![ScreenShot](screenshots/bugs/MaliciousCodeVulnerability/SecurityIssues.PNG)
![ScreenShot](screenshots/bugs/MultithreadCorrectness/WebServerInvokesStart.PNG)
![ScreenShot](screenshots/bugs/Correctness/ErrorController_UnwrittenFIels.PNG)
![ScreenShot](screenshots/bugs/Correctness/PathController_UnwrittenField.PNG)
![ScreenShot](screenshots/bugs/MultithreadCorrectness/testRun()invokesRunOnThread.PNG)
![ScreenShot](screenshots/bugs/Performance/ObjectFileTest_UnreadField.PNG)

#DYNAMIC ANALYSIS - YOURKIT

![ScreenShot](screenshots/dynamicAnalysis/Screenshot(36).png)
![ScreenShot](screenshots/dynamicAnalysis/Screenshot(37).png)
![ScreenShot](screenshots/dynamicAnalysis/Screenshot(39).png)
![ScreenShot](screenshots/dynamicAnalysis/Screenshot(40).png)
![ScreenShot](screenshots/dynamicAnalysis/Screenshot(41).png)

#3DView

* base - maintenence state
* ![ScreenShot](screenshots/base-maintenence.PNG)
* base - run state
* ![ScreenShot](screenshots/base-run.PNG)
* index - run state
* ![ScreenShot](screenshots/index-run.PNG)

#MARATHON

* error
* ![Screenshot](screenshots/marathon-error.PNG)
* folder
* ![Screenshot](screenshots/marathon-folder.PNG)
