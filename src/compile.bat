@echo off
if not exist ..\classes mkdir ..\classes
del /F /Q ..\classes\*
javac -d ..\classes -classpath ..\..\Updater\output\* -target 1.6 org\blazr\BuyVMServerInterface\main\java\* org\eclipse\wb\swing\*
cd ..\classes
jar cfm BuyVMServerInterface.jar ..\src\MANIFEST.MF org\*
if not exist ..\current_version mkdir ..\current_version
move BuyVMServerInterface.jar ..\current_version
if not exist ..\current_version\lib mkdir ..\current_version\lib
copy ..\..\Updater\output\Updater.jar ..\current_version\lib
pause