# LanguageTrainer

Sovelluksen avulla suomenkielentaitoinen henkilö voi opetella espanjan sanastoa. Nykyisellä versiolla voi harjoitella preesensin verbejä suomesta espanjaksi tekstikäyttöliittymällä.

Sovellus tarvitsee toimiakseen tiedoston *espanjanverbilista.csv* sovelluksen juurihakemistoon.

Osa testeistä tarvitsee toimiakseen tiedoston *taskListForTestingOptions.csv* sovelluksen juurihakemistoon.

## Dokumentaatio

[Vaatimusmäärittely](https://github.com/apla-hy/ot-harjoitustyo/blob/master/dokumentointi/maarittelydokumentti.md)

[Arkkitehtuurikuvaus](https://github.com/apla-hy/ot-harjoitustyo/blob/master/dokumentointi/arkkitehtuuri.md)

[Työaikakirjanpito](https://github.com/apla-hy/ot-harjoitustyo/blob/master/dokumentointi/tyoaikakirjanpito.md)

## Releaset

[Viikko 5](https://github.com/apla-hy/ot-harjoitustyo/releases)

## Komentorivitoiminnot

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto *target/site/jacoco/index.html*.

### Suorittaminen

Projektin koodin pystyy suorittamaan NetBeansin vihreällä napilla tai komennolla

```
mvn compile exec:java -Dexec.mainClass=languagetrainer.main.Main
```
### Suoritettavan jarin generointi

Komento

```
mvn package
```
generoi hakemistoon target suoritettavan jar-tiedoston. Jar-tiedoston kanssa samassa hakemistossa pitää olla tiedosto *espanjanverbilista.csv*.

### Checkstyle

Tiedoston *checkstyle.xml* määrittelemät tarkistukset suoritetaan komennolla
```
mvn jxr:jxr checkstyle:checkstyle
```
Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto *target/site/checkstyle.html*.



