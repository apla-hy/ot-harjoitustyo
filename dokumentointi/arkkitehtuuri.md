# Arkkitehtuurikuvaus

## Rakenne

Sovelluksen rakenteessa käyttöliittymät ja sovelluslogiikka on erotettu toisistaan. Pakkausrakenne on seuraava:
* languagetrainer.main
* languagetrainer.ui
* languagetrainer.uitext
* languagetrainer.domain

Pakkaus *languagetrainer.ui* sisältää JavaFX:llä toteutetun käyttöliittymän ja *languagetrainer.domain* sovelluslogiikan. Pakkaus *languagetrainer.main* sisältää pelkästään sovelluksen käynnistämisessä käytettävän main-luokan. Pakkaus *languagetrainer.uitext* sisältää tekstikäyttöliitymän mutta tämä pakkaus ei ole käytössä nykyisessä sovellusversiossa.

## Käyttöliittymä

## Sovelluslogiikka

Sovelluksen loogisen datamallin muodostavat luokat *Task*, *TaskList* ja *Exercise*. Luokka *Task* kuvaa yksittäistä tehtävää. Luokka *TaskList* sisältää listan kaikista sovelluksessa olevista yksittäisistä tehtävistä. Luokka *Exercise* kuvaa yksittäistä harjoitusta, joka koostuu tehtävistä. Luokkien välisiä suhteita kuvaa alla oleva luokkakaavio.

![Luokkakaavio](luokkakaavio.png)

## Päätoiminnallisuudet

### Uuden harjoituksen aloittaminen

Sovellus avautuu graafisen käyttöliittymän näkymään, jossa käyttäjä voi määrittää haluamansa harjoituksen vaihtoehdot. Painamalla nappia "Aloita uusi harjoitus" (startButton), sovellus luo uuden harjoituksen ja vaihtaa käyttöliittymän näkymän harjoitusnäkymään, jossa harjoituksen kysymyksiin vastataan yksi kerrallaan. Alla oleva sekvenssikaavio kuvaa uuden harjoituksen aloitusta.

![Sekvenssikaavio](sekvenssikaavio.png)
