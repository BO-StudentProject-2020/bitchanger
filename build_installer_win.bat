@echo off
rem Dieses Script erstellt mit Hilfe des jpackage Tools (OpenJDK14) aus einer ausf�hrbaren JAR Datei
rem einen nativen Installer f�r Windows. Alle von dem Java Programm ben�tigten Komponenten (JRE, javaFX, ...)
rem werden dabei mit in den Installer eingebunden, sodass das Programm nach der Installation auf jedem Windows Betriebssystem
rem unabh�ngig von anderen Programmen oder Installationen lauff�hig ist.

rem Es werden zwei Installer in den g�ngigen Formaten bereitgestellt (.exe und .msi)

rem ---- Ben�tigte Informationen in Variablen speichern ----------------------------------------------------------------

rem Mit den folgeneden Variablen k�nnen die Grundlegenden Daten f�r das Projekt eingestellt werden:
set NAME=Bitchanger
set DESCRIPTION="Rechner f�r beliebige Zahlensysteme"
set VERSION=0.1.0
set VENDOR=Entwicklungsprojekt_EB2020
rem set COPYRIGHT = ""
rem set LICENSE_FILE = ""

rem Einstellungen fuer jpackage:
set MAIN_JAR=bitchanger-%VERSION%-jar-with-dependencies.jar
set INPUT=target
set OUT=installer
rem set ICON =

rem Weitere Befehle f�r jpackage:
rem Installationspfad bei der Installation ausw�hlbar: --win-dir-chooser
rem Shortcut auf dem Desktop: --win-shortcut
rem In das Startmen� aufnehmen: --win-menu
rem Startmen�gruppe aufw�hlen: --win-menu-group <menu-group-name>
rem Installation auf Benutzerbasis: --win-per-user-install
rem App Icon �ndern: --icon <path/to/icon.ico>
rem Linzenz Datei: --license-file <file>

rem ---- Installer erzeugen ----------------------------------------------------------------------------------------------

echo.
echo Installer f�r Windows werden erzeugt.
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

rem ---- Auf Best�tigung von Benutzer warten -----------------------------------------------------------------------------
echo "Zum Abschlie�en eine beliebige Taste dr�cken"
pause

