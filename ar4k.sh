#!/bin/bash
ls -lh target/standalone-0.1.jar 
java -jar -Xms1024m -Xmx1024m -XX:NewSize=256m -XX:MaxNewSize=356m -XX:PermSize=256m -XX:MaxPermSize=356m target/standalone-0.1.jar
