import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class CatchBall extends JPanel implements ActionListener,KeyListener {

   int boardWidth = 630;
   int boardHeight = 460;

    Image backgroundImage;
    Image ballImage;
    Image basketImage;

    int ballX,ballY;
    int ballSize = 40;
    int ballSpeed = 5;

    int basketX,basketY;
    int basketWidth = 80;
    int basketHeight = 50;
    int basketSpeed = 20;

    Timer timer;
    int score = 0;
    int missedBalls = 0;
    boolean gameOver = false;

    CatchBall() {
        setPreferredSize(new Dimension(boardWidth,boardHeight));
        backgroundImage = new ImageIcon(getClass().getResource("./background.jpg")).getImage();
        ballImage = new ImageIcon(getClass().getResource("./ball.png")).getImage();
        basketImage = new ImageIcon(getClass().getResource("./basket.png")).getImage();

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
        ballX = new Random().nextInt(boardWidth-ballSize);
        ballY = 0;
        basketX = (boardWidth - basketWidth)/2;
        basketY = (boardHeight - basketHeight) - 10;
        timer.start();
        repaint();
    }

    public void resetBall(){
        ballY = 0;
        ballX = new Random().nextInt(boardWidth - ballSize);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

}
