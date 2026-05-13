package sae.app;

import sae.model.*;

public class Main {

    public static void main(String[] args) {

        Monde monde = new Monde();

        Mine mine1 = new Mine(1, TypeMinerai.OR, 2, 2, 80);
        Entrepot entrepot1 = new Entrepot(1, TypeMinerai.OR, 7, 7);

        Robot robot1 = new Robot(1, TypeMinerai.OR, 0, 0, 5, 2);

        monde.ajouterMine(mine1);
        monde.ajouterEntrepot(entrepot1);
        monde.ajouterRobot(robot1);

        monde.afficherMonde();

        System.out.println();

        monde.deplacerRobot(robot1, Direction.EST);
        monde.deplacerRobot(robot1, Direction.SUD);

        monde.jouerTour();

        monde.afficherMonde();
    }
}