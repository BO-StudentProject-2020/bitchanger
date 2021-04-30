ENGLISH:

Bitchanger is a program for converting various number systems. The main feature is the conversion of
different number systems with decimal places. Additionally it is possible to do basic arithmetic
operations and bitwise operations in different number systems.  Also the conversion from decimal system
to IEEE standard (IEEE-754-2008) (32-bit or 16-bit) is possible.

-------------------------------------------------------------------------------------------------------

All offered operations are executed via the graphical user interface, which is implemented with javaFX.

-------------------------------------------------------------------------------------------------------

Delivery:

Delivery of the software:
The program is packaged into a JAR file including all dependencies using the build tool Maven.
Afterwards the command line tool jpackage (openJDK 14) is used to create an installer for Windows, macOS
and Linux, which also contains the Java-Runtime that is required for execution.
Due to licensing terms, only Amazon Corretto (currently version 11) and the openJDK
(openJavaFX 11 and openJDK 14 for jpackage) are used.

-------------------------------------------------------------------------------------------------------

Origin:

The Bitchanger was developed by Tim Mühle and Moritz Wolter for the AID laboratory (Bochum University of
Applied Science, Germany) on behalf of Professor Brabender and Mr. Koch.



-------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------

GERMAN:

Bitchanger ist ein Programm zum Umwandeln von diversen Zahlensystemen. Das Hauptmerkmal liegt bei der
Umwandlung von verschiedenen Zahlensystemen mit Nachkommastellen. Zusätzlich ist es möglich in unter-
schiedlichen Zahlensystemen die Grundrechenarten und Bitoperationen durchzuführen. Auch die Umwandlung
von Dezimalsystem zu IEEE-Norm (IEEE-754-2008) (32-Bit oder 16-Bit) ist möglich.

-------------------------------------------------------------------------------------------------------

Alle angebotenen Operationen werden über die graphische Benutzeroberfläche ausgeführt, die mit javaFX
implementiert ist.

-------------------------------------------------------------------------------------------------------

Auslieferung der Software:

Das Programm wird mit dem Buildtool Maven in eine JAR-Datei inklusive aller Abhängigkeiten gepackt.
Im Anschluss wird mit dem Kommandozeilentool jpackage (openJDK 14) ein Installer für Windows, macOS
und Linux zur Auslieferung bereitgestellt, der auch die zur Ausführung benötigte Java-Runtime enthält.
Wegen Lizenzbestimmungen wird ausschließlich Amazon Corretto (aktuell Version 11) und das openJDK
(openJavaFX 11 und openJDK 14 für jpackage) verwendet.

-------------------------------------------------------------------------------------------------------

Entstehung:

Der Bitchanger wurde im Auftrag von Professorin Brabender und Herrn Koch von Tim Mühle und Moritz Wolter
als Entwicklungsprojekt für das AID-Labor im Sommersemester 2020 an der Hochschule Bochum entwickelt.

-------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------

Screenshots
<div>
    <h3>Zahlensystem-Umrechnung</h3>
    <img src="img/Screenshot_Conversions.png"></img>
    <p>In dieser Ansicht können Zahlen zwischen Zahlensystemen mit der Basis 2 bis 36 umgerechnet werden. 
        Es werden die häufig gebrauchten Zahlensysteme und ein frei wählbares Zahlensystem als Übersicht angezeigt.</p>
</div>
<div>
    <h3>Rechenweg der Umrechnung</h3>
    <img src="img/Screenshot_Steps.png" width="80%"></img>
    <p>Dieser Ansicht zeigt den Rechenweg als Schritt für Schritt Anleitung an, um zwischen zwei Zahlensystemen umzurechnen.</p>
</div>
<div>
    <h3>IEEE</h3>
    <img src="img/Screenshot_IEEE.png"></img>
    <p>Zu den Zahlensystemen beherrscht der Bitchanger auch die Umrechnung zwischem dem Dezimalsystem (Zehnersystem) und der 16-Bit bzw. 32-Bit IEEE-Norm (IEEE-754-2008-B16 / IEEE-754-2008-B32).</p>
</div>
<div>
    <h3>Berechnungen</h3>
    <img src="img/Screenshot_Calc.png"></img>
    <p>Auch einfache mathematische Berechnungen können in jedem beliebigen Zahlensystem durchgeführt werden.</p>
</div>
<div>
    <h3>Bitoperationen</h3>
    <img src="img/Screenshot_Bitoperations.png"></img>
    <p>Bitoperationen können ebenfalls mit allen Zahlensystemen durchgeführt werden.</p>
</div>
<div>
    <h3>Stile</h3>
    <img src="img/Screenshot_dark.png" width="25%"></img>
    <img src="img/Screenshot_color.png" width="25%"></img>
    <img src="img/Screenshot_light.png" width="25%"></img>
    <p>Es gibt drei verschiedene Stile, die nach persönlichem Geschmack eigestellt werden können.</p>
    <p>Bei negativen binärzahlen wird das Zweierkomplement gebildet und mit führender Eins angezeigt. Daher beginnen positive Binärzahlen immer mit einer Null.</p>
</div>