#!/usr/bin/env bash
echo "*********************************************************************************************"
echo "********************************* Transactions Statistics **************************************"
echo "*********************************************************************************************"
echo "/////////////////////////////////////////////////////////////////////////////////////////////"

## Making an executable jar file

echo "*********************************************************************************************"
echo "**************************    STARTING THE APPLICATION     *********************************"
echo "*********************************************************************************************"
echo "mvn spring-boot:run"
mvn spring-boot:run

STATUS=$?
if [ $STATUS -eq 0 ]; then
echo "*********************************************************************************************"
echo "*******************************   Build Successful     **************************************"
echo "*********************************************************************************************"
else
echo "*********************************************************************************************"
echo "*********************************    Build Fail     *****************************************"
echo "*********************************************************************************************"
fi