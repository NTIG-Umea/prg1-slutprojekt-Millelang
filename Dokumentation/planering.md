## Planering


### Planen
Ska skapa ett hockeyspel där en spelare kan ta upp pucken genom att nudda den med sin karaktär och 
skjuta iväg den genom att trycka på en knapp(space)

#### Vad behövs?
* Två karaktärer som går att styra - Java swing ena spelaren styrs med wasd den andra pil tangenterna
* en puck 
* två mål och funktioner som registrerar mål - En if sats som kolla om pucken är inom två värden på X och Y
* Funktion som tillåter spelarna att plocka upp pucken - En if sats som tar spelarna kordinater och kollar sedan om dem matchar puckens
* Funktion som gör det möjligt att skjuta iväg pucken efter man hhar den i sin ägo. - En funktion som ger pucken en acceleratoin om Space eller R-ctrl är tryckt
* En funktion som gör så att pucken saktar ner över tid - En funktion i update som minskar farten på pucken varje frame
* Funktion som gör så att pucken studsar då den nuddar sargen - En if sats som kolla om pucken är högre eller lägre än en vissa x och/eller Y kordinat
* Funktion som gör så att det går att stjäla pucken - EN funktion somkollar om en spelare har pucken och om den andra spelaren försöker ta den.
* Funktion som gör så att spelarna hamnar i tekning - En funktion som uppstår efter det blivit mål som startar ett "tekninksläge"
* tekning funktion - En funktion som sätter båda spelarna lika långt bort från mittzon och lägger pucken i mittzonen.

###### Om jag hinner
* En funktion som gör det möjligt att sikta
* målvakt som är automatiserad
* Ljudeffekter då man skjuter
* Animation då man skjuter iväg pucken
* Fina karaktärer

28-04
Skapade variabler och bilder och hitboxes för bilderna