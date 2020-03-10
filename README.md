# slavin-tech-test

## Development environment:
Windows 10 x64  
IntelliJ IDEA Community Edition  
Maven 3.6.0 
JDK 11.0.1  
TestNG (IDEA plugin)  

## Tech debt / future improvements
* I've only run the tests in Google Chrome on Windows 10.
  * Need to ensure tests run on supported OS/browser combinations.
* Investigate running tests in parallel.
  * Initial look suggests that it can be achieved in config. Not sure all tests would support parallel execution.
* Convert class/element specific methods for interacting with the contacts table into generic methods in the base page object class, and then use those in the 'proper' page object classes.
* Improve the robustness of the interactions with the contacts table.
  * I've added some retry logic in the event of _problems_ with a particular element.
  * i haven't had any issues since implementing, but needs more investigation (or confirmation that this is the right approach).
* Consider Adding security tests.
  * There's a useful resource - a 'naughty strings' repo on GitHub. 
  * I'd programmatically test a given control with each string in the list, rather than a test for each attack string.
  * Need to assess what security tests exist and at what level (unit/integration?).
* Investigate other reporting options.
  * More detailed output.
  * Some way to link screenshots to failing tests.
  * Also need to add a timestamp to the report output directory, so reports are not overridden.
* Investigate a better way to take screenshots on failure - maybe look at reportng listeners.
* Programmatically create test data (possibly via API) to cut down test execution time:
  * For test cases such as delete contact, for example.
* Look into the implications of making the test data driven.
  * Test method names should be descriptive enough to mitigate masking test inputs and _obscuring_ the purpose of the test.