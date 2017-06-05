#!/bin/bash
WD=`dirname $0`
WS=$WD/../../..
cd $WS

PROJ_NAME=$1
PACKAGE_NAME=$2
PACKAGE=${PACKAGE_NAME/\./\/}

mkdir $PROJ_NAME
cp -R template/doc $PROJ_NAME
cp -R template/src $PROJ_NAME
cp template/pom.xml $PROJ_NAME

sed -i '' -e "s/template/$PROJ_NAME/g" $PROJ_NAME/pom.xml

PACKAGE_FOLDER=$PROJ_NAME/src/main/java/com/ns
mkdir -p $PACKAGE_FOLDER/$PACKAGE
cp -r $PACKAGE_FOLDER/template/* $PACKAGE_FOLDER/$PACKAGE/
rm -rf $PACKAGE_FOLDER/template

SQL_FOLDER=$PROJ_NAME/doc/sql
mv $SQL_FOLDER/template.sql $SQL_FOLDER/$PROJ_NAME.sql
mv $SQL_FOLDER/template.mwb $SQL_FOLDER/$PROJ_NAME.mwb
mv $SQL_FOLDER/template.mwb.bak $SQL_FOLDER/$PROJ_NAME.mwb.bak

sed -i '' -e "s/Schema template/Schema $PROJ_NAME/g" $SQL_FOLDER/$PROJ_NAME.sql
sed -i '' -e "s/template/$PROJ_NAME/g" $SQL_FOLDER/param.sql

BUILD_FOLDER=$PROJ_NAME/doc/build

rm -f $BUILD_FOLDER/new_project.sh
sed -i '' -e "s/template/$PROJ_NAME/g" $BUILD_FOLDER/deploy.sh

RESOURCE_FOLDER=$PROJ_NAME/src/main/resources

sed -i '' -e "s/template/$PROJ_NAME/g" $RESOURCE_FOLDER/application.properties

SRC_FOLDER=$PROJ_NAME/src/main/java

sed -i '' -e "s/template/$PACKAGE_NAME/g" $SRC_FOLDER/com/ns/common/aop/AopNSDao.java
sed -i '' -e "s/template/$PACKAGE_NAME/g" $SRC_FOLDER/com/ns/common/aop/AopNSJsonAction.java
sed -i '' -e "s/template/$PACKAGE_NAME/g" $SRC_FOLDER/com/ns/common/aop/AopNSRpcAction.java

echo "Create $PROJ_NAME Success"

