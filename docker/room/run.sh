#!/bin/sh

echo "Starting room server \($(hostname)\) in $GLOBAL_IP"

java -jar /graphwar/room.jar $GLOBAL_IP $(hostname)
