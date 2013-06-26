#!/bin/bash
LDBC_CORE="ldbc_driver"
LDBC_CORE_JAR="ldbc_driver/core/target/core-0.1-SNAPSHOT.jar"
LDBC_CORE_VER="0.1-SNAPSHOT"
IN_PROJECT_MVN_REPO="lib"

git submodule init
git submodule update
rm -rf $IN_PROJECT_MVN_REPO
cd $LDBC_CORE
./build.sh
cd ..
mvn install:install-file -DlocalRepositoryPath=$IN_PROJECT_MVN_REPO -DcreateChecksum=true -Dpackaging=jar -Dfile=$LDBC_CORE_JAR -DgroupId=com.ldbc.driver -DartifactId=core -Dversion=$LDBC_CORE_VER
mvn clean package
