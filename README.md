# LanguageTrainer

Sovelluksen avulla suomenkielentaitoinen henkilö voi opetella espanjan sanastoa. Nykyisellä versiolla voi harjoitella preesensin verbejä suomesta espanjaksi tekstikäyttöliittymällä.

Sovellus tarvitsee toimiakseen tiedoston *espanjanverbilista.csv* sovelluksen juurihakemistoon.

## Dokumentaatio

[Vaatimusmäärittely](https://github.com/apla-hy/ot-harjoitustyo/blob/master/dokumentointi/maarittelydokumentti.md)

[Työaikakirjanpito](https://github.com/apla-hy/ot-harjoitustyo/blob/master/dokumentointi/tyoaikakirjanpito.md)

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
mvn compile exec:java -Dexec.mainClass=languagetrainer.uitext.Main
```
 



