# Slutprojekt programering

Milton Långström 2023-06-02

## Inledning

Syftet med detta arbete var att skapa att "hockeyspel" som skulle innehålla ett visst antal funktioner med syftet att efterlikna sporten ishockey. 

## Bakgrund

De funktioner som jag skapade för att efterlikna puckens rörelse var en "klocka" som saktade ner puckens funktion för hastighet (puckvx och puck vy) varje 60:e frame. Skapade även en funktion som gjorde så att om puckens y position varje större eller mindre än planens max och min värde av y så får pucken motsatt hastighet i y (-puckvy). Även så att då pucken går i mål så startar båda spelarna och pucken i mitt posistion som om det vore en vanlig hockeymatch.

Skapade även en funktion med syftet att efterlikna ett "hockeyskott" så när spelare1 trycker på space eller spelare2 trycker på rightshift och har pucken i sin ägo så skjuts den iväg med samma y hastighet som spelarenoch en lite snabbare x hastighet än spelaren.

Skapade också en funktion som registrerar om spelaren nuddar pucken vilken gör så att spelaren "plockar upp" pucken och kan röra sig med den och skjuta, gjorde även så att om den andra spelaren nuddar pucken då den första spelaren har den i sin ägo så tar den andra spelaren pucken ifrån den första.


```
![GitHub Logo](/images/logo.png)
Format: ![Alt Text](url)
```

![NTI Gymnasiet Umeå Logo](https://raw.githubusercontent.com/jensnti/Webbprojekt/master/mallar/nti_logo_white_umea.svg)

## Positiva erfarenheter

Lärt mig hur man använder swing och dess egenskaper på ett mycket effektivt och bra sätt.
## Negativa erfarenheter

Hade lite problem med att göra en bra mål funktion vilket ledde till att jag var tvungen att börja om helt eftersom jag hade tänkt att det endast skulle gå att göra mål i det "riktiga" målet.
## Sammanfattning

 Tycker att detta projekt blev ganska bra eftersom att det blev nästan precis som jag ville förutom några små detaljer som målet och tekningar. Om jag hade gjort om detta projekt så hade jag lagt ned mycket mer tid på att skapa en mål funktion som faktiskt efterliknar ett mål.