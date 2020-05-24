#!/bin/sh

PID=`ps -ef | grep -v grep | grep java | grep modio | grep oauth | awk '{ print $2 }'`

echo $PID

if [ -n "$PID" ]; then
        kill -9 "$PID"
fi

ps -ef | grep java | grep modio | grep oauth 
