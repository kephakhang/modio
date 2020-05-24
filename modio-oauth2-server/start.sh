#!/bin/sh
nohup mvn spring-boot:run -Drun.arguments="--modio.server.name=oauth"  > /dev/null &
touch ./logs/RUN.`date +%Y%m%d`.log
tail -f ./logs/RUN.`date +%Y%m%d`.log 
