#! /bin/bash

echo "install start....."
mvn clean install -D maven.test.skip=true -P develop -f ../../pom.xml
echo "install finish"

echo "docker build"
docker build -t cove/jazzy:latest .
echo "docker finish"
