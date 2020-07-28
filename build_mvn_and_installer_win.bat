@echo off
rem Dieses Skript erstellt mit Hilfe von Maven eine ausfuehrbare JAR-Datei aus dem Java-Projekt, kopiert diese in das
rem Verzeichnis "installer\source" und erzeugt dann mit einem weiteren Skript Plattformspezifische Installer fuer Windows.
rem Es werden zwei Installer in den gaengigen Formaten bereitgestellt (.exe und .msi)

rem ---- Benoetigte Informationen in Variablen speichern ----------------------------------------------------------------

rem Mit den folgeneden Variablen koennen die Grundlegenden Daten fuer das Projekt eingestellt werden:
set NAME=Bitchanger
set DESCRIPTION="Rechner fuer beliebige Zahlensysteme"
set VERSION=0.1.4
set VENDOR=Entwicklungsprojekt_EB2020

set INPUT=installer\source\Windows
set OUT=installer\%VERSION%\Windows


rem ---- Maven build -----------------------------------------------------------------------------------------------------
echo.
echo Maven Bulid durchfuehren
echo.
@echo on
call mvn clean install
@echo off


rem ---- JARs sichern ----------------------------------------------------------------------------------------------------
echo JAR-Dateien kopieren
if not exist %INPUT%\%VERSION% mkdir %INPUT%\%VERSION%
if exist %INPUT%\jpackage del /Q jpackage	rem Eingabeordner fuer jpackage leeren
@echo on
copy target\bitchanger-%VERSION%-jar-with-dependencies.jar %INPUT%\%VERSION%\bitchanger-%VERSION%-jar-with-dependencies.jar
copy target\bitchanger-%VERSION%.jar %INPUT%\%VERSION%\bitchanger-%VERSION%.jar
copy target\bitchanger-%VERSION%-jar-with-dependencies.jar %INPUT%\jpackage\bitchanger-%VERSION%.jar
@echo off


rem ---- Installer erzeugen ----------------------------------------------------------------------------------------------
echo.
echo Installer fuer Windows werden erzeugt.
@echo on
build_installer_win.bat VERSION=%VERSION% INPUT=%INPUT% OUT=%OUT%
@echo off
echo.

