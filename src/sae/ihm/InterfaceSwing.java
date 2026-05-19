package sae.ihm;
import sae.model.*;

import javax.swing.*;
import java.awt.*;

public class InterfaceSwing extends JFrame {

    private Monde monde;
    private Robot robot1, robot2, robotChoisi;

    private JLabel[][] cases = new JLabel[20][20];
    private JTextArea infos = new JTextArea();

    private boolean robot1AJoue = false;
    private boolean robot2AJoue = false;

    public InterfaceSwing() {
        monde = new Monde();
        creerMonde();

        setTitle("SAE Robots Mineurs");
        setSize(1100, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel principal = new JPanel(new BorderLayout());

        JLabel titre = new JLabel("SAE Robots Mineurs", SwingConstants.CENTER);
        titre.setFont(new Font("Arial", Font.BOLD, 24));

        principal.add(titre, BorderLayout.NORTH);
        principal.add(creerGrille(), BorderLayout.CENTER);
        principal.add(creerCommandes(), BorderLayout.EAST);

        infos.setEditable(false);
        infos.setFont(new Font("Arial", Font.PLAIN, 14));
        principal.add(infos, BorderLayout.SOUTH);

        add(principal);

        afficherGrille();
        afficherInfos();

        setVisible(true);
    }
    private JPanel creerGrille() {
        JPanel grille = new JPanel(new GridLayout(20, 20));

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {

                JLabel caseGraphique = new JLabel("", SwingConstants.CENTER);
                caseGraphique.setOpaque(true);
                caseGraphique.setFont(new Font("Arial", Font.BOLD, 13));

                int haut = (i % 2 == 0) ? 2 : 1;
                int gauche = (j % 2 == 0) ? 2 : 1;
                int bas = (i % 2 == 1) ? 2 : 1;
                int droite = (j % 2 == 1) ? 2 : 1;

                caseGraphique.setBorder(BorderFactory.createMatteBorder(
                        haut, gauche, bas, droite, Color.BLACK
                ));

                cases[i][j] = caseGraphique;
                grille.add(caseGraphique);
            }
        }

        return grille;
    }

    private JPanel creerCommandes() {
        JPanel commandes = new JPanel();
        commandes.setLayout(new BoxLayout(commandes, BoxLayout.Y_AXIS));
        commandes.setPreferredSize(new Dimension(230, 600));

        JButton boutonR1 = new JButton("Robot 1");
        JButton boutonR2 = new JButton("Robot 2");

        boutonR1.addActionListener(e -> {
            robotChoisi = robot1;
            labelRobot.setText("Robot choisi : R1");
        });

        boutonR2.addActionListener(e -> {
            robotChoisi = robot2;
            labelRobot.setText("Robot choisi : R2");
        });

        JPanel fleches = new JPanel(new GridLayout(3, 3));
        fleches.setMaximumSize(new Dimension(150, 150));

        JButton haut = new JButton("↑");
        JButton bas = new JButton("↓");
        JButton gauche = new JButton("←");
        JButton droite = new JButton("→");

        fleches.add(new JLabel(""));
        fleches.add(haut);
        fleches.add(new JLabel(""));
        fleches.add(gauche);
        fleches.add(new JLabel(""));
        fleches.add(droite);
        fleches.add(new JLabel(""));
        fleches.add(bas);
        fleches.add(new JLabel(""));

        JButton recolter = new JButton("Récolter");
        JButton deposer = new JButton("Déposer");

        haut.addActionListener(e -> action("NORD"));
        bas.addActionListener(e -> action("SUD"));
        gauche.addActionListener(e -> action("OUEST"));
        droite.addActionListener(e -> action("EST"));
        recolter.addActionListener(e -> action("RECOLTER"));
        deposer.addActionListener(e -> action("DEPOSER"));

        commandes.add(new JLabel("Robot choisi"));
        commandes.add(boutonR1);
        commandes.add(boutonR2);
        commandes.add(labelRobot);
        commandes.add(labelTour);
        commandes.add(Box.createVerticalStrut(20));
        commandes.add(new JLabel("Déplacement"));
        commandes.add(fleches);
        commandes.add(Box.createVerticalStrut(20));
        commandes.add(recolter);
        commandes.add(deposer);

        return commandes;
    }

    private void action(String action) {

        if (robotChoisi == null) {
            JOptionPane.showMessageDialog(this, "Choisis un robot.");
            return;
        }

        if (robotChoisi == robot1 && robot1AJoue) {
            JOptionPane.showMessageDialog(this, "Robot 1 a déjà joué ce tour.");
            return;
        }

        if (robotChoisi == robot2 && robot2AJoue) {
            JOptionPane.showMessageDialog(this, "Robot 2 a déjà joué ce tour.");
            return;
        }

        boolean ok = false;

        if (action.equals("NORD")) {
            ok = monde.deplacerRobot(robotChoisi, Direction.NORD);
        } else if (action.equals("SUD")) {
            ok = monde.deplacerRobot(robotChoisi, Direction.SUD);
        } else if (action.equals("EST")) {
            ok = monde.deplacerRobot(robotChoisi, Direction.EST);
        } else if (action.equals("OUEST")) {
            ok = monde.deplacerRobot(robotChoisi, Direction.OUEST);
        } else if (action.equals("RECOLTER")) {
            ok = robotChoisi.recolter(monde.getMineSurCase(robotChoisi));
        } else if (action.equals("DEPOSER")) {
            ok = robotChoisi.deposer(monde.getEntrepotSurCase(robotChoisi));
        }

        if (!ok) {
            JOptionPane.showMessageDialog(this, "Action impossible.");
            return;
        }

        if (robotChoisi == robot1) {
            robot1AJoue = true;
        } else if (robotChoisi == robot2) {
            robot2AJoue = true;
        }

        robotChoisi = null;
        labelRobot.setText("Robot choisi : aucun");

        if (robot1AJoue && robot2AJoue) {
            monde.jouerTour();
            robot1AJoue = false;
            robot2AJoue = false;
            JOptionPane.showMessageDialog(this, "Fin du tour. Nouveau tour.");
        }

        afficherGrille();
        afficherInfos();
    }
