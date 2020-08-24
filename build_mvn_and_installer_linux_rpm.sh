#!/bin/sh
cd "$(dirname "$0")"	# zum Pfad dieses Skriptes wechseln

# Dieses Skript erstellt mit Hilfe von Maven eine ausfuehrbare JAR-Datei aus dem Java-Projekt, kopiert diese in das
# Verzeichnis "installer\source".

# Im Anschluss wird mit Hilfe des jpackage Tools (OpenJDK14) aus einer ausfuehrbaren JAR Datei einen nativer Installer
# fuer Linux erstellt. Alle von dem Java Programm benoetigten Komponenten (JRE, javaFX, ...) werden dabei mit in den 
# Installer eingebunden, sodass das Programm nach der Installation auf jedem Linux Betriebssystem unabhaengig von 
# anderen Programmen oder Installationen lauffaehig ist.

# Es werden zwei Installer fuer macOS in den gaengigen Formaten bereitgestellt (.deb und .rpm)


# ---- Benoetigte Informationen in Variablen speichern ----------------------------------------------------------------

# Mit den folgeneden Variablen koennen die Grundlegenden Daten fuer das Projekt eingestellt werden:
NAME=Bitchanger
DESCRIPTION="Rechner fuer beliebige Zahlensysteme"
VERSION=0.1.5
VENDOR="Entwicklungsprojekt_EB2020 by Tim Mühle and Moritz Wolter"
# set COPYRIGHT = ""
# set LICENSE_FILE = ""

# Einstellungen fuer jpackage:
MAIN_JAR="bitchanger-$VERSION.jar"
INPUT="installer/source/Linux"
OUT="installer/${VERSION}/Linux"
# set ICON =

# Weitere Befehle fuer jpackage:
# App Icon aendern: --icon <path/to/icon.ico>
# Linzenz Datei: --license-file <file>


# ---- Maven build -----------------------------------------------------------------------------------------------------
echo ""
echo "Maven build durchfuehren"
echo ""
mvn clean install


# ---- JARs sichern ----------------------------------------------------------------------------------------------------
echo ""
echo JAR-Dateien kopieren

mkdir $OUT
mkdir $INPUT/jpackage
mkdir $INPUT/$VERSION

cp target/bitchanger-$VERSION-jar-with-dependencies.jar $INPUT/$VERSION/bitchanger-$VERSION-jar-with-dependencies.jar
cp target/bitchanger-$VERSION.jar $INPUT/$VERSION/bitchanger-$VERSION.jar

rm -r -f $INPUT/jpackage/*	# Eingabeordner fuer jpackage leeren
cp target/bitchanger-$VERSION-jar-with-dependencies.jar $INPUT/jpackage/bitchanger-$VERSION.jar

echo JAR-Dateien kopiert

# ---- Installer erzeugen ----------------------------------------------------------------------------------------------
echo ""
echo "Installer fuer Linux werden erzeugt."
echo ""

for TYPE in "rpm"
do
	echo "${NAME-$VERSION}.${TYPE} wird erstellt"
	echo ""
	
	/library/java/jdk-14.0.1/bin/jpackage \
	--type $TYPE \
	--name "${NAME}" \
	--description "${DESCRIPTION}" \
	--vendor "${VENDOR}" \
	--app-version $VERSION \
	--input $INPUT/jpackage \
	--dest $OUT \
	--main-jar "${MAIN_JAR}" \
	--linux-shortcut

	echo ""
	echo ""
done

