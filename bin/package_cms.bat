@echo off
echo [INFO] Package the war in target dir.

cd %~dp0
cd ..
call mvn clean package -Dmaven.test.skip=true -P proc-cms -pl iotek-online-edu-web-cms -am
cd bin
pause