@echo off

java -cp ../WebContent/WEB-INF/lib/h2*.jar org.h2.tools.Server -baseDir . -tcp -tcpAllowOthers
