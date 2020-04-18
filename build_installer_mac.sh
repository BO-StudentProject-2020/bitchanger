#!/bin/sh

# Dieses Script erstellt mit Hilfe des jpackage Tools (OpenJDK14) aus einer ausf�hrbaren JAR Datei
# einen nativen Installer f�r Windows. Alle von dem Java Programm ben�tigten Komponenten (JRE, javaFX, ...)
# werden dabei mit in den Installer eingebunden, sodass das Programm nach der Installation auf jedem Windows Betriebssystem
# unabh�ngig von anderen Programmen oder Installationen lauff�hig ist.

# Es werden zwei Installer in den g�ngigen Formaten bereitgestellt (.exe und .msi)

# ---- Ben�tigte Informationen in Variablen speichern ----------------------------------------------------------------

# Mit den folgeneden Variablen k�nnen die Grundlegenden Daten f�r das Projekt eingestellt werden:
NAME=Bitchanger
DESCRIPTION="Rechner f�r beliebige Zahlensysteme"
VERSION=0.0.1
VENDOR=Entwicklungsprojekt_EB2020
# set COPYRIGHT = ""
# set LICENSE_FILE = ""

# Einstellungen fuer jpackage:
MAIN_JAR=bitchanger-$VERSION-jar-with-dependencies.jar
INPUT=target
OUT=installer
# set ICON =

# Weitere Befehle f�r jpackage:
# Installationspfad bei der Installation ausw�hlbar: --win-dir-chooser
# Shortcut auf dem Desktop: --win-shortcut
# In das Startmen� aufnehmen: --win-menu
# Startmen�gruppe aufw�hlen: --win-menu-group <menu-group-name>
# Installation auf Benutzerbasis: --win-per-user-install
# App Icon �ndern: --icon <path/to/icon.ico>
# Linzenz Datei: --license-file <file>

# ---- Installer erzeugen ----------------------------------------------------------------------------------------------

echo 
echo Installer f�r macOS werden erzeugt.
echo 

for TYPE in "dmg" "pkg"
do
	echo ${NAME-$VERSION}.${TYPE} wird erstellt
	echo 
	
	jpackage ^
	--type dmg ^
	--name $NAME ^
	--description $DESCRIPTION ^
	--vendor $VENDOR ^
	--app-version $VERSION ^
	--input $INPUT ^
	--dest $OUT ^
	--main-jar $MAIN_JAR ^
	--mac-package-name $NAME ^
	--mac-sign

	echo
	echo
done

# ---- Auf Best�tigung von Benutzer warten -----------------------------------------------------------------------------
read -p "Zum Abschlie�en eine beliebige Taste dr�cken ..." var


