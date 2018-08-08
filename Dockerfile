FROM openjdk:8-jre-slim

# Add the project jar & copy dependencies
ADD  target/container-test.jar /usr/share/tag/container-test.jar
ADD  target/libs /usr/share/tag/libs

# Add the suite xmls
ADD order-module.xml /usr/share/tag/order-module.xml

# Command line to execute the test
# Expects below ennvironment variables
# BROWSER = chrome / firefox
# MODULE  = order-module / search-module
# GRIDHOST = selenium hub hostname / ipaddress

ENTRYPOINT /usr/bin/java -cp /usr/share/tag/container-test.jar:/usr/share/tag/libs/* -DseleniumHubHost=$SELENIUM_HUB -Dbrowser=$BROWSER org.testng.TestNG /usr/share/tag/$MODULE
