package sae.model;



import java.util.List;
import java.util.Map;

public class PlanificateurPrioriteFixe {

    private final Monde monde;
    private final DijkstraPathFinder dijkstra;

    public PlanificateurPrioriteFixe(Monde monde) {
        this.monde = monde;
        this.dijkstra = new DijkstraPathFinder(monde);
    }

    public void jouerUnTour(Map<Robot, Position> objectifs) {
        for (Robot robot : monde.getRobots()) {
            Position objectif = objectifs.get(robot);

            if (objectif != null) {
                avancerRobotVers(robot, objectif);
            }
        }

        monde.jouerTour();
    }

    private void avancerRobotVers(Robot robot, Position objectif) {
        Position depart = new Position(robot.getLigne(), robot.getColonne());

        List<Position> chemin = dijkstra.trouverChemin(depart, objectif);

        if (chemin.size() < 2) {
            System.out.println("Robot " + robot.getNumero() + " attend.");
            return;
        }

        Position prochainePosition = chemin.get(1);
        Direction direction = calculerDirection(depart, prochainePosition);

        boolean deplacementReussi = monde.deplacerRobot(robot, direction);

        if (deplacementReussi) {
            System.out.println("Robot " + robot.getNumero() + " avance vers " + prochainePosition);
        } else {
            System.out.println("Robot " + robot.getNumero() + " attend car la case est bloquée.");
        }
    }

    private Direction calculerDirection(Position depart, Position arrivee) {
        if (arrivee.getLigne() == depart.getLigne() - 1) {
            return Direction.NORD;
        }

        if (arrivee.getLigne() == depart.getLigne() + 1) {
            return Direction.SUD;
        }

        if (arrivee.getColonne() == depart.getColonne() + 1) {
            return Direction.EST;
        }

        return Direction.OUEST;
    }
}