#! /bin/bash

echo "#### $2maven install start....."
mvn clean install -D maven.test.skip=true -P release -f ../../pom.xml
echo "#### $2maven install finish"

echo "#### deploy start..."
exec ./publish.sh ROOT /usr/local/tomcat 180.76.196.4 ../../cove-api/target/ROOT.war
echo "#### deploy finish"