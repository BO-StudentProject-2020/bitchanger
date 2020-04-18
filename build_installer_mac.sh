#!/bin/sh

# Dieses Script erstellt mit Hilfe des jpackage Tools (OpenJDK14) aus einer ausführbaren JAR Datei
# einen nativen Installer für Windows. Alle von dem Java Programm benötigten Komponenten (JRE, javaFX, ...)
# werden dabei mit in den Installer eingebunden, sodass das Programm nach der Installation auf jedem Windows Betriebssystem
# unabhängig von anderen Programmen oder Installationen lauffähig ist.

# Es werden zwei Installer in den gängigen Formaten bereitgestellt (.exe und .msi)

# ---- Benötigte Informationen in Variablen speichern ----------------------------------------------------------------

# Mit den folgeneden Variablen können die Grundlegenden Daten für das Projekt eingestellt werden:
NAME=Bitchanger
DESCRIPTION="Rechner für beliebige Zahlensysteme"
VERSION=0.0.1
VENDOR=Entwicklungsprojekt_EB2020
# set COPYRIGHT = ""
# set LICENSE_FILE = ""

# Einstellungen fuer jpackage:
MAIN_JAR=bitchanger-$VERSION-jar-with-dependencies.jar
INPUT=target
OUT=installer
# set ICON =

# Weitere Befehle für jpackage:
# Installationspfad bei der Installation auswählbar: --win-dir-chooser
# Shortcut auf dem Desktop: --win-shortcut
# In das Startmenü aufnehmen: --win-menu
# Startmenügruppe aufwählen: --win-menu-group <menu-group-name>
# Installation auf Benutzerbasis: --win-per-user-install
# App Icon ändern: --icon <path/to/icon.ico>
# Linzenz Datei: --license-file <file>

# ---- Installer erzeugen ----------------------------------------------------------------------------------------------

echo 
echo Installer für macOS werden erzeugt.
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

# ---- Auf Bestätigung von Benutzer warten -----------------------------------------------------------------------------
read -p "Zum Abschließen eine beliebige Taste drücken ..." var


