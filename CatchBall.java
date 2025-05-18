import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import utils.GameUtils;

public class CatchBall extends JPanel implements ActionListener,KeyListener {

    private final int boardWidth = 630;
    private final int boardHeight = 460;

    private Image backgroundImage;
    private Image ballImage;
    private Image basketImage;

    private int ballX, ballY;
    private final int ballSize = 40;
    private final int ballSpeed = 5;

    private int basketX, basketY;
    private final int basketWidth = 80;
    private final int basketHeight = 50;
    private final int basketSpeed = 20;

    private Timer timer;
    private int score = 0;
    private int missedBalls = 0;
    private boolean gameOver = false;

    CatchBall() {
        setPreferredSize(new Dimension(boardWidth,boardHeight));
        backgroundImage = GameUtils.loadImage("/background.jpg");
        ballImage = GameUtils.loadImage("/ball.png");
        basketImage = GameUtils.loadImage("/basket.png");

        ballX = new Random().nextInt(boardWidth-ballSize);
        ballY = 0;
        basketX = (boardWidth-basketWidth)/2;
        basketY = boardHeight - basketHeight - 10;

        setFocusable(true);
        addKeyListener(this);

        timer = new Timer(10,this);
        resetGame();
        timer.start();
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, boardWidth, boardHeight, null);
        g.drawImage(ballImage,ballX,ballY,ballSize,ballSize,null);
        g.drawImage(basketImage,basketX,basketY,basketWidth,basketHeight,null);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial",Font.BOLD,20));
        g.drawString("Score " + score,20,30);
        g.drawString("Missed: " + missedBalls,20,60);

        if(gameOver){
            g.setFont(new Font("Arial",Font.BOLD,40));
            g.setColor(Color.RED);
            g.drawString("Score : " + score,boardWidth/2-100,boardHeight/2-50);
            g.drawString("Game Over!", boardWidth/2-130,boardHeight/2);
            g.drawString("Press 'R' to Restart", boardWidth / 2 - 180, boardHeight / 2 + 40);
            timer.stop();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            ballY += ballSpeed;

            if (ballY >= basketY && ballX + ballSize >= basketX && ballX <= basketX + basketWidth) {
                score++;
                resetBall();
            }
            if (ballY > boardHeight) {
                missedBalls++;
                resetBall();
            }
            if(missedBalls>=3){
                gameOver = true;
                timer.stop();

                SwingUtilities.invokeLater(()->{
                    Window window = SwingUtilities.getWindowAncestor(this);
                    if(window != null){
                        window.dispose();
                    }
                    new GameOverScreen(score);
                });
            }
            repaint();
        }
    }

    public void keyPressed(KeyEvent e) {
        if (!gameOver) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT && basketX > 0) {
                basketX -= basketSpeed;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT && basketX < boardWidth - basketWidth) {
                basketX += basketSpeed;
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_R){
            resetGame();
        }
        repaint();
    }

    public void resetGame(){
        score = 0;
        missedBalls = 0;
        gameOver = false;
        ballX = GameUtils.getRandomX(boardWidth - ballSize);
        ballY = 0;
        basketX = (boardWidth - basketWidth)/2;
        basketY = (boardHeight - basketHeight) - 10;
        timer.start();
        repaint();
    }

    public void resetBall(){
        ballY = 0;
        ballX = GameUtils.getRandomX(boardWidth - ballSize);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

}
