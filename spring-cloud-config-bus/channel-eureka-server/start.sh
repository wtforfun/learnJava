#!/bin/bash
export JAVA_HOME=/opt/java/jdk1.8.0_111
export PATH=$JAVA_HOME/bin:$PATH
export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
nohup java -Xms128m -Xmx128m -jar channel-config-server-1.0.0.jar >> ./logs/localhost.out 2>&1 &
