import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import utils.GameUtils;

public class MenuScreen {
    private JFrame menuFrame;

    MenuScreen() {
        int boardWidth = 630;
        int boardHeight = 460;

        menuFrame = new JFrame("Main Menu");
        menuFrame.setSize(boardWidth, boardHeight);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GameUtils.centerWindow(menuFrame);

        Image backgroundImage = GameUtils.loadImage("/background.jpg");

        JPanel mainPanel = new JPanel(){
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, boardWidth, boardHeight, null);
            }
        };

        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setOpaque(false);

        JLabel title = new JLabel("Catch the Ball", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(Color.WHITE);

        JButton playButton = new JButton("Play");
        playButton.setFont(new Font("Arial", Font.BOLD, 16));
        playButton.setPreferredSize(new Dimension(180, 50));
        playButton.setFocusPainted(false);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame.dispose();
                new MainGame();
            }
        });

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.BOLD, 16));
        exitButton.setPreferredSize(new Dimension(180, 50));
        exitButton.setFocusPainted(false);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(title, gbc);

        gbc.gridy = 1;
        mainPanel.add(playButton, gbc);

        gbc.gridy = 2;
        mainPanel.add(exitButton, gbc);

        menuFrame.add(mainPanel);
        menuFrame.setVisible(true);
    }

}
