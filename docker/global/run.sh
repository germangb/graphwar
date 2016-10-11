#!/bin/sh

echo "Starting global server in $GLOBAL_IP"

java -jar /graphwar/global.jar $GLOBAL_IP
