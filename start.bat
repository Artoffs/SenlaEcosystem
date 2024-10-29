@echo off

cd src/
javac -cp ../out com/ecosystem/models/*.java
javac -cp ../out com/ecosystem/repo/*.java
javac -cp ../out com/ecosystem/services/*.java
javac -cp ../out com/ecosystem/simulation/*.java
javac -cp ../out com/ecosystem/utils/*.java
cd ..
javac -d out src/com/ecosystem/*.java
java -cp out com.ecosystem.Ecosystem

pause
