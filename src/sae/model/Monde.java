package sae.model;

import java.util.ArrayList;

public class Monde {

    private Secteur[][] secteurs;
    private ArrayList<Robot> robots;
    private ArrayList<Mine> mines;
    private ArrayList<Entrepot> entrepots;
    private int tourActuel;

    public Monde() {
        secteurs = new Secteur[10][10];
        robots = new ArrayList<>();
        mines = new ArrayList<>();
        entrepots = new ArrayList<>();
        tourActuel = 0;

        creerSecteurs();
    }

    private void creerSecteurs() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                secteurs[i][j] = new Secteur(i, j, false);
            }
        }
    }

    public void ajouterRobot(Robot robot) {
        robots.add(robot);

        int ligne = robot.getLigne();
        int colonne = robot.getColonne();

        secteurs[ligne][colonne].placerRobot(robot);
    }

    public void ajouterMine(Mine mine) {
        mines.add(mine);

        int ligne = mine.getLigne();
        int colonne = mine.getColonne();

        secteurs[ligne][colonne].setMine(mine);
    }

    public void ajouterEntrepot(Entrepot entrepot) {
        entrepots.add(entrepot);

        int ligne = entrepot.getLigne();
        int colonne = entrepot.getColonne();

        secteurs[ligne][colonne].setEntrepot(entrepot);
    }

    public boolean deplacerRobot(Robot robot, Direction direction) {
        int ancienneLigne = robot.getLigne();
        int ancienneColonne = robot.getColonne();

        int nouvelleLigne = ancienneLigne;
        int nouvelleColonne = ancienneColonne;

        if (direction == Direction.NORD) {
            nouvelleLigne = nouvelleLigne - 1;
        }

        if (direction == Direction.SUD) {
            nouvelleLigne = nouvelleLigne + 1;
        }

        if (direction == Direction.EST) {
            nouvelleColonne = nouvelleColonne + 1;
        }

        if (direction == Direction.OUEST) {
            nouvelleColonne = nouvelleColonne - 1;
        }

        if (nouvelleLigne < 0 || nouvelleLigne >= 10) {
            return false;
        }

        if (nouvelleColonne < 0 || nouvelleColonne >= 10) {
            return false;
        }

        if (!secteurs[nouvelleLigne][nouvelleColonne].estAccessible()) {
            return false;
        }

        secteurs[ancienneLigne][ancienneColonne].retirerRobot();
        robot.setPosition(nouvelleLigne, nouvelleColonne);
        secteurs[nouvelleLigne][nouvelleColonne].placerRobot(robot);

        return true;
    }

    public void afficherMonde() {
        System.out.println("Tour : " + tourActuel);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                Secteur secteur = secteurs[i][j];

                if (secteur.getRobot() != null) {
                    System.out.print("R" + secteur.getRobot().getNumero() + " ");
                } else if (secteur.getMine() != null) {
                    System.out.print("M" + secteur.getMine().getNumero() + " ");
                } else if (secteur.getEntrepot() != null) {
                    System.out.print("E" + secteur.getEntrepot().getNumero() + " ");
                } else {
                    System.out.print("-- ");
                }
            }

            System.out.println();
        }
    }

    public void jouerTour() {
        tourActuel = tourActuel + 1;
    }

    public ArrayList<Robot> getRobots() {
        return robots;
    }
}