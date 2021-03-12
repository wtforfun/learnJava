#!/bin/bash
   PID=$(ps -ef | grep channel-config-server-1.0.0.jar | grep -v grep | awk '{ print $2 }')
if [ -z "$PID" ]
then
    echo Application is already stopped
else
    echo kill $PID
    kill $PID
fi
