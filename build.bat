@echo off
cls
xcopy "bin" "app/WEB-INF/classes" /s /e /y
cd app 
jar cvf ../solaire.war .
cd ..
xcopy "solaire.war" "D:\app\apache-tomcat-10.0.22\webapps" /s /e /y



