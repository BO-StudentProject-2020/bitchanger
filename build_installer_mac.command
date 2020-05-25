#!/bin/sh
cd "$(dirname "$0")"	# zum Pfad dieses Skriptes wechseln

# Dieses Script erstellt mit Hilfe des jpackage Tools (OpenJDK14) aus einer ausfuehrbaren JAR Datei
# einen nativen Installer fuer macOS. Alle von dem Java Programm benoetigten Komponenten (JRE, javaFX, ...)
# werden dabei mit in den Installer eingebunden, sodass das Programm nach der Installation auf jedem Windows Betriebssystem
# unabhaengig von anderen Programmen oder Installationen lauffaehig ist.

# Es werden zwei Installer fuer macOS in den gaengigen Formaten bereitgestellt (.dmg und .pkg)

# ---- Benoetigte Informationen in Variablen speichern ----------------------------------------------------------------

# Mit den folgeneden Variablen koennen die Grundlegenden Daten fuer das Projekt eingestellt werden:
NAME="Bitchanger"
DESCRIPTION="Rechner fuer beliebige Zahlensysteme"
VERSION="0.1.1"
VENDOR="Entwicklungsprojekt_EB2020"
# set COPYRIGHT = ""
# set LICENSE_FILE = ""

# Einstellungen fuer jpackage:
MAIN_JAR="bitchanger-$VERSION-jar-with-dependencies.jar"
INPUT="installer/source"
OUT="installer/${VERSION}/macOS"
# set ICON =

# Weitere Befehle fuer jpackage:
# Installationspfad bei der Installation auswaehlbar: --win-dir-chooser
# Name, der in der Menueleiste angezeigt wird: --mac-package-name <name>
# Request that the bundle be signed: --mac-sign
# App Icon aendern: --icon <path/to/icon.ico>
# Linzenz Datei: --license-file <file>


# ---- Installer erzeugen ----------------------------------------------------------------------------------------------

echo ""
echo "Installer fuer macOS werden erzeugt."
echo ""

for TYPE in "dmg" "pkg"
do
	echo "${NAME-$VERSION}.${TYPE} wird erstellt"
	echo ""
	
	/Library/Java/jdk-14.jdk/Contents/Home/bin/jpackage \
	--type $TYPE \
	--name "${NAME}" \
	--description "${DESCRIPTION}" \
	--vendor "${VENDOR}" \
	--app-version $VERSION \
	--input $INPUT \
	--dest $OUT \
	--main-jar "${MAIN_JAR}" \
	--mac-package-name "${NAME}"

	echo ""
	echo ""
done


