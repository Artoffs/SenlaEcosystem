@echo off

REM Компилируем Java программу
dir /s /b src\*.java > sources.txt
javac -d out @sources.txt

REM Запускаем Java программу
java -cp out com.ecosystem.Ecosystem

pause
