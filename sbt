#!/bin/sh
java -XX:MaxPermSize=256M -Xmx1024m -jar sbt-launch.jar "$@"
