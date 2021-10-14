#! /bin/bash

echo "install start....."
mvn clean install -D maven.test.skip=true -P product -f ../../pom.xml
echo "install finish"

echo "copy jar file....."
cp ../../jazzy-api/target/jazzy.jar ./jazzy.jar

version=`grep -oP "<project.jazzy.version>\K[0-9\.]+" ../../pom.xml`
echo "docker build"
docker build -t cove/jazzy:${version} -f Dockerfile .
echo "docker finish"

rm ./jazzy.jar
echo "remove jar file....."
