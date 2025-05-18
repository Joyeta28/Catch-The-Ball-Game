package utils;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public final class GameUtils {
    private GameUtils() {}

    public static Image loadImage(String path) {
        return new ImageIcon(GameUtils.class.getResource(path)).getImage();
    }

    public static int getRandomX(int max) {
        return new Random().nextInt(max);
    }

    public static void centerWindow(Window frame) {
        frame.setLocationRelativeTo(null);
    }
}
