#! /bin/bash

echo "[DEV] install start....."
mvn clean install -D maven.test.skip=true -P develop -f ../../pom.xml

echo "[DEV] copy start....."
scp ../../jazzy-api/target/ROOT.war root@101.200.53.244:/usr/local/tomcat-9000/temp

echo "[DEV] deploy start....."
ssh -t -t root@101.200.53.244 << EOF
    cd /usr/local/tomcat-9000

    echo "[DEV] shutdown tomcat..."
    bin/shutdown.sh

    echo "[DEV] remove ROOT file"
    rm -rf webapps/ROOT
    rm -rf webapps/ROOT.war

    echo "[DEV] move ROOT.war file"
    mv -f temp/ROOT.war webapps/
    rm -rf temp/ROOT.war

    echo "[DEV] startup tomcat..."
    bin/startup.sh &
    echo "[DEV] startup tomcat end"

    exit
EOF

echo "[DEV] deploy finish"