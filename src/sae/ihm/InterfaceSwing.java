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
