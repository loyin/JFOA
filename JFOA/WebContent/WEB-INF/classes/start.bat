for %%i in ("../lib/*.*") do call "lcp.bat" ../lib/%%i
java -classpath "%LOCALCLASSPATH%" Server