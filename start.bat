@echo off
dir /s /b src\*.java > sources.txt
javac -d out @sources.txt
java -cp out src/com/ecosystem/Ecosystem.java
pause