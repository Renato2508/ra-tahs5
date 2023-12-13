@echo off
cls
xcopy "bin" "app/WEB-INF/classes" /s /e /y
cd app 
jar cvf ../stock.war .
cd ..
xcopy "stock.war" "D:\app\apache-tomcat-10.0.22\webapps" /s /e /y



