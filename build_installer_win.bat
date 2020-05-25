@echo off
rem Dieses Script erstellt mit Hilfe des jpackage Tools (OpenJDK14) aus einer ausfÅhrbaren JAR Datei
rem einen nativen Installer fÅr Windows. Alle von dem Java Programm benîtigten Komponenten (JRE, javaFX, ...)
rem werden dabei mit in den Installer eingebunden, sodass das Programm nach der Installation auf jedem Windows Betriebssystem
rem unabhÑngig von anderen Programmen oder Installationen lauffÑhig ist.

rem Es werden zwei Installer in den gÑngigen Formaten bereitgestellt (.exe und .msi)

rem ---- Benîtigte Informationen in Variablen speichern ----------------------------------------------------------------

rem Mit den folgeneden Variablen kînnen die Grundlegenden Daten fÅr das Projekt eingestellt werden:
set NAME=Bitchanger
set DESCRIPTION="Rechner fÅr beliebige Zahlensysteme"
set VERSION=0.1.0
set VENDOR=Entwicklungsprojekt_EB2020
rem set COPYRIGHT = ""
rem set LICENSE_FILE = ""

rem Einstellungen fuer jpackage:
set MAIN_JAR=bitchanger-%VERSION%-jar-with-dependencies.jar
set INPUT=target
set OUT=installer
rem set ICON =

rem Weitere Befehle fÅr jpackage:
rem Installationspfad bei der Installation auswÑhlbar: --win-dir-chooser
rem Shortcut auf dem Desktop: --win-shortcut
rem In das StartmenÅ aufnehmen: --win-menu
rem StartmenÅgruppe aufwÑhlen: --win-menu-group <menu-group-name>
rem Installation auf Benutzerbasis: --win-per-user-install
rem App Icon Ñndern: --icon <path/to/icon.ico>
rem Linzenz Datei: --license-file <file>

rem ---- Installer erzeugen ----------------------------------------------------------------------------------------------

echo.
echo Installer fÅr Windows werden erzeugt.
echo.

rem ---- exe ----
echo %NAME%-%VERSION%.exe wird erstellt
echo.
@echo on
jpackage ^
--type exe ^
--name %NAME% ^
--description %DESCRIPTION% ^
--vendor %VENDOR% ^
--app-version %VERSION% ^
--input %INPUT% ^
--dest %OUT% ^
--main-jar %MAIN_JAR% ^
--win-dir-chooser ^
--win-shortcut ^
--win-menu ^
--win-menu-group  

@echo off
echo.
echo.

rem ---- msi ----
echo %NAME%-%VERSION%.msi wird erstellt
echo.
@echo on

jpackage ^
--type msi ^
--name %NAME% ^
--description %DESCRIPTION% ^
--vendor %VENDOR% ^
--app-version %VERSION% ^
--input %INPUT% ^
--dest %OUT% ^
--main-jar %MAIN_JAR% ^
--win-dir-chooser ^
--win-shortcut ^
--win-menu ^
--win-menu-group  

@echo off
echo.
echo.

rem ---- Auf BestÑtigung von Benutzer warten -----------------------------------------------------------------------------
echo "Zum Abschlie·en eine beliebige Taste drÅcken"
pause

