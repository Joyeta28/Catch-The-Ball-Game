import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import utils.GameUtils;

public class GameOverScreen {
    JFrame frame;
    int boardWidth  = 630;
    int boardHeight = 460;
    Image backgroundImage;
    public GameOverScreen(int finalScore){
        frame = new JFrame("Game Over");
        frame.setSize(boardWidth, boardHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GameUtils.centerWindow(frame);

        Image backgroundImage = GameUtils.loadImage("/background.jpg");

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, boardWidth, boardHeight, this);
            }
        };

        panel.setLayout(new GridBagLayout());

        JLabel gameOverLabel = new JLabel("Game Over");
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 40));
        gameOverLabel.setForeground(Color.WHITE);

        JLabel scoreLabel = new JLabel("Final Score: " + finalScore);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        scoreLabel.setForeground(Color.black);

        JButton restartButton = new JButton("Restart");
        restartButton.setFont(new Font("Arial", Font.BOLD, 16));
        restartButton.setPreferredSize(new Dimension(150, 40));
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new MainGame();
            }
        });

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.BOLD, 16));
        exitButton.setPreferredSize(new Dimension(150, 40));
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.gridx = 0;

        gbc.gridy = 0;
        panel.add(gameOverLabel,gbc);

        gbc.gridy = 1;
        panel.add(scoreLabel,gbc);

        gbc.gridy = 2;
        panel.add(restartButton,gbc);

        gbc.gridy = 3;
        panel.add(exitButton,gbc);

        frame.setContentPane(panel);
        frame.setVisible(true);

    }
}
