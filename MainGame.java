import javax.swing.*;
import java.awt.*;


public class MainGame {
    public static void main(String[] args) {
        MenuScreen menuScreen = new MenuScreen();
    }

    MainGame(){
        int boardWidth = 630;
        int boardHeight = 460;

        JFrame frame = new JFrame("Catch The Ball");
        frame.setSize(boardWidth,boardHeight);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        CatchBall catchBall = new CatchBall();

        frame.add(catchBall);
        frame.pack();
        frame.setVisible(true);
    }
}
