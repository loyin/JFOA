for %%i in ("./WebContent/lib/*.*") do call "lcp.bat" ./WebContent/lib/%%i
set webclasses=WebContent/classes
java -Xms128m -Xmx640m -classpath "%LOCALCLASSPATH%;%webclasses%" Server 