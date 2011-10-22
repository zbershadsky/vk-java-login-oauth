#!/bin/sh
# Build and run the vk-java-login-oauth tomcat.

mvn clean install -Passembly
#mvn clean install -Passembly -Dtomcat.distrib=/PATH/TO/TOMCAT/DISTRIBUTIVE

rm -rf ./target/war-unzipped

unzip ./target/lib-vk-standalone-tomcat-resources.dir/vk-java-login-oauth-server-war-1.1-SNAPSHOT.war -d ./target/war-unzipped

cp ./target/war-unzipped/WEB-INF/lib/vk-java-login-oauth-valve-1.1-SNAPSHOT.jar ./target/vk-tomcat/lib/
cp ./target/war-unzipped/WEB-INF/lib/vk-java-login-oauth-servlet-1.1-SNAPSHOT.jar ./target/vk-tomcat/lib/
cp ./target/war-unzipped/WEB-INF/lib/slf4j-api-1.5.8.jar ./target/vk-tomcat/lib/
cp ./target/war-unzipped/WEB-INF/lib/slf4j-log4j12-1.5.8.jar ./target/vk-tomcat/lib/
cp ./target/war-unzipped/WEB-INF/lib/log4j-1.2.14.jar ./target/vk-tomcat/lib/


cd ./target/vk-tomcat/bin

chmod +x *.sh

