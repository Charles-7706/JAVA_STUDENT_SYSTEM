@echo off
echo Cleaning...
del /s /q project\*.class 2>nul
echo Compiling...
javac -cp "project\DAO\mysql-connector-j-9.6.0.jar;." project\model\*.java project\DAO\*.java project\controllers\*.java project\view\*.java
echo Done_Compiling.
java -cp "project\DAO\mysql-connector-j-9.6.0.jar;." project.view.UniversitySystem
