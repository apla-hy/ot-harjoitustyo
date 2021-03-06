# LanguageTrainer

Sovelluksen avulla suomenkielentaitoinen henkilö voi opetella espanjan sanastoa. Nykyisellä versiolla voi harjoitella preesensin verbejä suomesta espanjaksi graafisella käyttöliitymällä.

Sovellus tarvitsee toimiakseen tiedostot *config.properties* ja *vocabulary.csv* sovelluksen juurihakemistoon.

Osa testeistä tarvitsee toimiakseen tiedoston *vocabularyForTesting.csv* sovelluksen juurihakemistoon.

## Dokumentaatio

[Käyttöohje](https://github.com/apla-hy/ot-harjoitustyo/blob/master/dokumentointi/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/apla-hy/ot-harjoitustyo/blob/master/dokumentointi/maarittelydokumentti.md)

[Arkkitehtuurikuvaus](https://github.com/apla-hy/ot-harjoitustyo/blob/master/dokumentointi/arkkitehtuuri.md)

[Testausdokumentti](https://github.com/apla-hy/ot-harjoitustyo/blob/master/dokumentointi/testaus.md)

[Työaikakirjanpito](https://github.com/apla-hy/ot-harjoitustyo/blob/master/dokumentointi/tyoaikakirjanpito.md)

## Releaset

[Viikko 5](https://github.com/apla-hy/ot-harjoitustyo/releases/tag/viikko5)

[Viikko 6](https://github.com/apla-hy/ot-harjoitustyo/releases/tag/viikko6)

[Loppupalautus](https://github.com/apla-hy/ot-harjoitustyo/releases/tag/loppupalautus)

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
generoi hakemistoon target suoritettavan jar-tiedoston. Jar-tiedoston kanssa samassa hakemistossa pitää olla tiedostot *config.properties* ja *vocabulary.csv*.

### Checkstyle

Tiedoston *checkstyle.xml* määrittelemät tarkistukset suoritetaan komennolla
```
mvn jxr:jxr checkstyle:checkstyle
```
Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto *target/site/checkstyle.html*.

### JavaDoc

JavaDocista saa luotua HTML-version suorittamalla komennon
```
mvn javadoc:javadoc
```
Generoitu JavaDoc löytyy hakemistosta *target/site/apidocs/*.
