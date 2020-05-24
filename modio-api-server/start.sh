#!/bin/bash
java -jar -Drun.arguments="--modio.server.name=api" ./target/modio-api-server-1.0.0-SNAPSHOT.jar &
disown $!
touch ./logs/RUN.`date +%Y%m%d`.log
tail -f ./logs/RUN.`date +%Y%m%d`.log 
