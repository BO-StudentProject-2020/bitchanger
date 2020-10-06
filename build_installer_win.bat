@echo off
rem Dieses Script erstellt mit Hilfe des jpackage Tools (OpenJDK14) aus einer ausfuehrbaren JAR Datei
rem einen nativen Installer fuer Windows. Alle von dem Java Programm benoetigten Komponenten (JRE, javaFX, ...)
rem werden dabei mit in den Installer eingebunden, sodass das Programm nach der Installation auf jedem Windows Betriebssystem
rem unabhaengig von anderen Programmen oder Installationen lauffaehig ist.

rem Es werden zwei Installer in den gaengigen Formaten bereitgestellt (.exe und .msi)

rem ---- Benoetigte Informationen in Variablen speichern ----------------------------------------------------------------

rem Mit den folgeneden Variablen koennen die Grundlegenden Daten fuer das Projekt eingestellt werden:
set NAME=Bitchanger
set DESCRIPTION="Rechner fuer beliebige Zahlensysteme"
if not defined VERSION set VERSION=0.1.8
set VENDOR="Entwicklungsprojekt_EB2020 by Tim Mühle and Moritz Wolter"
rem set COPYRIGHT = ""
rem set LICENSE_FILE = ""

rem Einstellungen fuer jpackage:
set MAIN_JAR=bitchanger-%VERSION%.jar
if not defined INPUT set INPUT=installer\source\Windows
if not defined OUT set OUT=installer\%VERSION%\Windows
rem set ICON =

rem Weitere Befehle fuer jpackage:
rem Installationspfad bei der Installation auswaehlbar: --win-dir-chooser
rem Shortcut auf dem Desktop: --win-shortcut
rem In das Startmenue aufnehmen: --win-menu
rem Startmenuegruppe aufwaehlen: --win-menu-group <menu-group-name>
rem Installation auf Benutzerbasis: --win-per-user-install
rem App Icon aendern: --icon <path/to/icon.ico>
rem Linzenz Datei: --license-file <file>


rem ---- Installer erzeugen ----------------------------------------------------------------------------------------------

echo.
echo Installer fuer Windows werden erzeugt.
echo.

rem ---- exe ----
echo %NAME%-%VERSION%-win-install.exe wird erstellt
echo.
@echo on
jpackage ^
--type exe ^
--name %NAME% ^
--description %DESCRIPTION% ^
--vendor %VENDOR% ^
--app-version %VERSION% ^
--input %INPUT%\jpackage ^
--dest %OUT% ^
--main-jar %MAIN_JAR% ^
--icon .\Logo\%NAME%.ico ^
--win-dir-chooser ^
--win-shortcut ^
--win-menu ^
--win-menu-group  

@echo off

ren "%OUT%\%NAME%-%VERSION%.exe" "%NAME%-%VERSION%-win-install.exe"
 
echo.
echo.

rem ---- msi ----
echo %NAME%-%VERSION%-win-install.msi wird erstellt
echo.
@echo on

jpackage ^
--type msi ^
--name %NAME% ^
--description %DESCRIPTION% ^
--vendor %VENDOR% ^
--app-version %VERSION% ^
--input %INPUT%\jpackage ^
--dest %OUT% ^
--main-jar %MAIN_JAR% ^
--icon .\Logo\%NAME%.ico ^
--win-dir-chooser ^
--win-shortcut ^
--win-menu ^
--win-menu-group  

@echo off

ren "%OUT%\%NAME%-%VERSION%.msi" "%NAME%-%VERSION%-win-install.msi"

echo.
echo.

rem ---- Auf Bestaetigung von Benutzer warten -----------------------------------------------------------------------------
echo "Zum Abschliessen eine beliebige Taste druecken"
pause

