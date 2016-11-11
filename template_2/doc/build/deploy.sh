#!/bin/bash
WD=`dirname $0`
PROJ=$WD/../..
cd $PROJ

ENV=$1

svn up

mvn clean package

JAVA_OPTS="-Xms512m -Xmx2048m -XX:PermSize=128M -XX:MaxPermSize=512m"

echo "java opt: $JAVA_OPTS"

ps -ef | grep template.jar | awk '{print $2}' | xargs kill -9

nohup java -jar target/template.jar $JAVA_OPTS 2>&1 | /usr/bin/cronolog "$PROJ/logs/catalina.%Y-%m-%d.out" &