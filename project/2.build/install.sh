#! /bin/bash

mvn clean install -D maven.test.skip=true -P release -f ../../cove-api
echo "#### $2maven install finish....."