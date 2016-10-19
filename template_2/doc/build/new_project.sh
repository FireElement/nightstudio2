#!/bin/bash
WD=`dirname $0`
WS=$WD/../../..
cd $WS

PROJ_NAME=$1

mkdir $PROJ_NAME
cp -R template/doc $PROJ_NAME
cp -R template/src $PROJ_NAME
cp template/pom.xml $PROJ_NAME

sed -i '' -e "s/template/$PROJ_NAME/g" $PROJ_NAME/pom.xml

PACKAGE_FOLDER=$PROJ_NAME/src/main/java/com/ns
mv $PACKAGE_FOLDER/template $PACKAGE_FOLDER/$PROJ_NAME

SQL_FOLDER=$PROJ_NAME/doc/sql
mv $SQL_FOLDER/template.sql $SQL_FOLDER/$PROJ_NAME.sql
mv $SQL_FOLDER/template.mwb $SQL_FOLDER/$PROJ_NAME.mwb
mv $SQL_FOLDER/template.mwb.bak $SQL_FOLDER/$PROJ_NAME.mwb.bak

sed -i '' -e "s/Schema template/Schema $PROJ_NAME/g" $SQL_FOLDER/$PROJ_NAME.sql
sed -i '' -e "s/template/$PROJ_NAME/g" $SQL_FOLDER/param.sql

RESOURCE_FOLDER=$PROJ_NAME/src/main/resources

sed -i '' -e "s/template/$PROJ_NAME/g" $RESOURCE_FOLDER/application.properties

SRC_FOLDER=$PROJ_NAME/src/main/java

sed -i '' -e "s/template/$PROJ_NAME/g" $SRC_FOLDER/com/ns/common/aop/AopNSDao.java
sed -i '' -e "s/template/$PROJ_NAME/g" $SRC_FOLDER/com/ns/common/aop/AopNSJsonAction.java
sed -i '' -e "s/template/$PROJ_NAME/g" $SRC_FOLDER/com/ns/common/aop/AopNSRpcAction.java

echo "Create $PROJ_NAME Success"

