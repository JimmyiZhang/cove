#! /bin/bash

echo "[DEV] install start....."
mvn clean install -D maven.test.skip=true -P develop -f ../../pom.xml
echo "[DEV] install finish"