package luxnet.browser;

import javax.swing.*;
import java.awt.*;

public class LuxBrowserUI extends JFrame {
    private JTextField addressBar;
    private JButton goButton;
    private JTextArea displayArea;

    public LuxBrowserUI() {
        super("LuxBrowser");

        // Layout de la fenêtre
        setLayout(new BorderLayout());

        // Panel du haut avec la barre d'adresse et bouton Go
        JPanel topPanel = new JPanel(new BorderLayout());
        addressBar = new JTextField("lux.lux"); // url par défaut
        goButton = new JButton("Go");

        topPanel.add(addressBar, BorderLayout.CENTER);
        topPanel.add(goButton, BorderLayout.EAST);

        // Zone de contenu
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        // Ajouter à la fenêtre
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Taille et fermeture
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Action sur le bouton Go ou appui entrée dans la barre d'adresse
        goButton.addActionListener(e -> loadPage());
        addressBar.addActionListener(e -> loadPage());
    }

    // Méthode simulant le chargement d'une page Lux
    private void loadPage() {
        String url = addressBar.getText().trim();

        try {
            java.net.InetAddress.getByName(url);
        displayArea.setText("Chargement de la page : " + url + "\n\nContenu du site Lux à venir...");
        } catch (Exception e) {
            displayArea.setText("Erreur : l'adresse \"" + url + "\" est introuvable dans le DNS.");
        }
    }

    // Point d'entrée pour tester l'interface
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LuxBrowserUI browser = new LuxBrowserUI();
            browser.setVisible(true);
        });
    }
}
