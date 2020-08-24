#!/bin/sh
cd "$(dirname "$0")"	# zum Pfad dieses Skriptes wechseln

# Dieses Skript erstellt mit Hilfe von Maven eine ausfuehrbare JAR-Datei aus dem Java-Projekt, kopiert diese in das
# Verzeichnis "installer\source".

# Im Anschluss wird mit Hilfe des jpackage Tools (OpenJDK14) aus einer ausfuehrbaren JAR Datei einen nativer Installer
# fuer macOS erstellt. Alle von dem Java Programm benoetigten Komponenten (JRE, javaFX, ...) werden dabei mit in den 
# Installer eingebunden, sodass das Programm nach der Installation auf jedem Windows Betriebssystem unabhaengig von 
# anderen Programmen oder Installationen lauffaehig ist.

# Es werden zwei Installer fuer macOS in den gaengigen Formaten bereitgestellt (.dmg und .pkg)


# ---- Benoetigte Informationen in Variablen speichern ----------------------------------------------------------------

# Mit den folgeneden Variablen koennen die Grundlegenden Daten fuer das Projekt eingestellt werden:
NAME="Bitchanger"
DESCRIPTION="Rechner fuer beliebige Zahlensysteme"
VERSION="0.1.5"
VENDOR="Entwicklungsprojekt_EB2020 by Tim Mühle and Moritz Wolter"
# set COPYRIGHT = ""
# set LICENSE_FILE = ""

# Einstellungen fuer jpackage:
MAIN_JAR="bitchanger-$VERSION.jar"
INPUT="installer/source/Mac"
OUT="installer/${VERSION}/macOS"
# set ICON =

# Weitere Befehle fuer jpackage:
# Installationspfad bei der Installation auswaehlbar: --win-dir-chooser
# Name, der in der Menueleiste angezeigt wird: --mac-package-name <name>
# Request that the bundle be signed: --mac-sign
# App Icon aendern: --icon <path/to/icon.ico>
# Linzenz Datei: --license-file <file>


# ---- Maven build -----------------------------------------------------------------------------------------------------
echo ""
echo "Maven build durchfuehren"
echo ""
mvn clean install


# ---- JARs sichern ----------------------------------------------------------------------------------------------------
echo JAR-Dateien kopieren
mkdir $OUT
mkdir $INPUT/jpackage
mkdir $INPUT/$VERSION

cp target/bitchanger-$VERSION-jar-with-dependencies.jar $INPUT/$VERSION/bitchanger-$VERSION-jar-with-dependencies.jar
cp target/bitchanger-$VERSION.jar $INPUT/$VERSION/bitchanger-$VERSION.jar

rm -r -f $INPUT/jpackage/*	# Eingabeordner fuer jpackage leeren
cp target/bitchanger-$VERSION-jar-with-dependencies.jar $INPUT/jpackage/bitchanger-$VERSION.jar


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
	--input $INPUT/jpackage \
	--dest $OUT \
	--main-jar "${MAIN_JAR}" \
	--mac-package-name "${NAME}"

	echo ""
	echo ""
done

