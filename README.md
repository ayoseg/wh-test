
Clone this test project and navigate to the root directory of wh-test



- To clean, setup and install dependencies using the command listed
    - $ mvn clean install

- To run the tests
    - $ mvn -Dbrowser=chrome test

    For browser options, use chrome or chrome_emulated


- Note: this test was developed using java version 1.7
- Chrome driver installation path = /usr/local/bin
- To View report - go to directory -  wh-test/target/cucumber-report/whtest/index.html

Follow the steps below to run the test suite

## Installation steps
``` bash
  $ git clone https://github.com/ayoseg/wh-test.git
  $ cd wh-test
  $ mvn clean install
  $ mvn -Dbrowser=chrome test
```