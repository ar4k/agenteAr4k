#!/bin/bash
ls -lh target/standalone-0.1.jar 
java -jar -Xms2048m -Xmx2048m -XX:NewSize=256m -XX:MaxNewSize=356m -XX:PermSize=256m -XX:MaxPermSize=356m -Xss2048k target/standalone-0.1.jar
