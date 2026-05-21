# Simulation de robots mineurs

Ce projet a été réalisé dans le cadre de la SAE 2.01. Il propose une simulation d'un monde composé de robots, de mines et d'entrepôts, avec une version console et une version graphique.

## Organisation du projet

Tous les fichiers sources du projet se trouvent dans la branche `main`.

## Fichiers JAR fournis

- `SAE robot.jar` : version console.
- `SAE robot graphique.jar` : version graphique.

## Lancer l'application

### Version graphique

Pour lancer la version graphique, il faut exécuter la classe `ecranAccueil` située dans le package `ihm`. Le fichier JAR correspondant est `SAE_robot_graphique.jar`.

### Version console

Pour lancer la version console, il faut exécuter le main dans le package `app`. Le fichier JAR correspondant est `SAE_robot.jar`.

## Fonctionnement général

L'application simule un monde en grille dans lequel des robots se déplacent, récoltent des minerais dans des mines, puis les déposent dans les entrepôts correspondants. Deux modes d'utilisation sont disponibles : un mode console et un mode graphique.

## Tests JUnit

Les tests du projet se trouvent dans le package `model`. Ils permettent de vérifier le bon fonctionnement d'une partie de la logique métier du programme.

## Remarques

Le projet contient donc :

- une version console lancée depuis le package `app` et disponible dans `SAE robot.jar` ;
- une version graphique lancée depuis `ecranAccueil` dans le package `ihm` et disponible dans `SAE robot graphique.jar` ;
- les sources dans `main` ;
- les tests dans le package `model`.
