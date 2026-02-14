@echo off
setlocal
set JAVA_HOME=%USERPROFILE%\.java\jdk-11.0.21+9
set MAVEN_HOME=%USERPROFILE%\.maven\apache-maven-3.9.11
set PATH=%JAVA_HOME%\bin;%MAVEN_HOME%\bin;%PATH%
cd /d "%~dp0"
call mvn clean javafx:run -q
