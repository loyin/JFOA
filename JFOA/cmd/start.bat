for %%i in ("../lib/*.*") do call "lcp.bat" ../lib/%%i
java -Xms128m -Xmx640m -classpath "%LOCALCLASSPATH%" Server