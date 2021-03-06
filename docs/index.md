<html>
    <head>
        <meta charset="UTF-8" />
    </head>
    <body>
        <div>
            <p>
                <h1>Bitchanger Homepage</h1>
                <img src="img/Bitchanger.png" style="width: 15%; height: 15%" id="background" />
            </p>
            <p>
                <ul id="menu">
                    <li><a href="#documentation">Documentation</a></li>
                    <li><a href="#screenshots">Screenshots</a></li>
                    <li><a href="#about">About</a></li>
                </ul>
            </p>
        </div>
        <hr />
        <div id="documentation">
            <h2>Documentation</h2>
            <p>Die Dokumentation ist wegen Zeitmangel leider unvollständig.</p>
            <ul id="docmenu">
                <li><a href="1.0.2/en_public/index.html">Public Doc Bitchanger 1.0.2 (EN)</a></li>
                <li><a href="1.0.2/en_private/index.html">Private Doc Bitchanger 1.0.2 (EN)</a></li>
                <li><a href="1.0.3/de_public/index.html">Public Doc Bitchanger 1.0.3 (DE)</a></li>
                <li><a href="1.0.3/de_private/index.html">Private Doc Bitchanger 1.0.3 (DE)</a></li>
            </ul>
        </div>
        <hr />
        <div id="screenshots">
            <h2>Screenshots</h2>
            <div>
                <h3>Zahlensystem-Umrechnung</h3>
                <img src="img/Screenshot_Conversions.png" />
                <p>In dieser Ansicht können Zahlen zwischen Zahlensystemen mit der Basis 2 bis 36 umgerechnet werden. 
                   Es werden die häufig gebrauchten Zahlensysteme und ein frei wählbares Zahlensystem als Übersicht angezeigt.</p>
            </div>
            <div>
                <h3>Rechenweg der Umrechnung</h3>
                <img src="img/Screenshot_Steps.png" width="80%" />
                <p>Dieser Ansicht zeigt den Rechenweg als Schritt für Schritt Anleitung an, um zwischen zwei Zahlensystemen umzurechnen.</p>
            </div>
            <div>
                <h3>IEEE</h3>
                <img src="img/Screenshot_IEEE.png" />
                <p>Zu den Zahlensystemen beherrscht der Bitchanger auch die Umrechnung zwischem dem Dezimalsystem (Zehnersystem) und der 16-Bit bzw. 32-Bit IEEE-Norm (IEEE-754-2008-B16 / IEEE-754-2008-B32).</p>
            </div>
            <div>
                <h3>Berechnungen</h3>
                <img src="img/Screenshot_Calc.png" />
                <p>Auch einfache mathematische Berechnungen können in jedem beliebigen Zahlensystem durchgeführt werden.</p>
            </div>
            <div>
                <h3>Bitoperationen</h3>
                <img src="img/Screenshot_Bitoperations.png" />
                <p>Bitoperationen können ebenfalls mit allen Zahlensystemen durchgeführt werden.</p>
            </div>
            <div>
                <h3>Stile</h3>
                <img src="img/Screenshot_dark.png" width="30%" />
                <img src="img/Screenshot_color.png" width="30%" />
                <img src="img/Screenshot_light.png" width="30%" />
                <p>Es gibt drei verschiedene Stile, die nach persönlichem Geschmack eigestellt werden können.</p>
                <p>Bei negativen binärzahlen wird das Zweierkomplement gebildet und mit führender Eins angezeigt. Daher beginnen positive Binärzahlen immer mit einer Null.</p>
            </div>
        </div>
        <hr />
        <div id="about">
            <h2>About</h2>
            <p>
                <h3>English:</h3>
                Bitchanger is a program for converting various number systems. The main feature is the conversion of
                different number systems with decimal places. Additionally it is possible to do basic arithmetic
                operations and bitwise operations in different number systems.  Also the conversion from decimal system
                to IEEE standard (IEEE-754-2008) (32-bit or 16-bit) is possible.
                <br /><br />
                All offered operations are executed via the graphical user interface, which is implemented with javaFX.
                <br /><br />
                Delivery:
                <br /><br />
                Delivery of the software:
                The program is packaged into a JAR file including all dependencies using the build tool Maven.
                Afterwards the command line tool jpackage (openJDK 14) is used to create an installer for Windows, macOS
                and Linux, which also contains the Java-Runtime that is required for execution.
                Due to licensing terms, only Amazon Corretto (currently version 11) and the openJDK
                (openJavaFX 11 and openJDK 14 for jpackage) are used.
                <br /><br />
                Origin:
                <br /><br />
                The Bitchanger was developed by Tim Mühle and Moritz Wolter for the AID laboratory (Bochum University of
                Applied Science, Germany) on behalf of Professor Brabender and Mr. Koch.
            </p>
            <p>
                <h3>German:</h3>
                Bitchanger ist ein Programm zum Umwandeln von diversen Zahlensystemen. Das Hauptmerkmal liegt bei der
                Umwandlung von verschiedenen Zahlensystemen mit Nachkommastellen. Zusätzlich ist es möglich in unter-
                schiedlichen Zahlensystemen die Grundrechenarten und Bitoperationen durchzuführen. Auch die Umwandlung
                von Dezimalsystem zu IEEE-Norm (IEEE-754-2008) (32-Bit oder 16-Bit) ist möglich.
                <br /><br />
                Alle angebotenen Operationen werden über die graphische Benutzeroberfläche ausgeführt, die mit javaFX
                implementiert ist.
                <br /><br />
                Auslieferung der Software:
                <br /><br />
                Das Programm wird mit dem Buildtool Maven in eine JAR-Datei inklusive aller Abhängigkeiten gepackt.
                Im Anschluss wird mit dem Kommandozeilentool jpackage (openJDK 14) ein Installer für Windows, macOS
                und Linux zur Auslieferung bereitgestellt, der auch die zur Ausführung benötigte Java-Runtime enthält.
                Wegen Lizenzbestimmungen wird ausschließlich Amazon Corretto (aktuell Version 11) und das openJDK
                (openJavaFX 11 und openJDK 14 für jpackage) verwendet.
                <br /><br />
                Entstehung:
                <br /><br />
                Der Bitchanger wurde im Auftrag von Professorin Brabender und Herrn Koch von Tim Mühle und Moritz Wolter
                als Entwicklungsprojekt für das AID-Labor im Sommersemester 2020 an der Hochschule Bochum entwickelt.
            </p>
        </div>
    </body>
</html>
