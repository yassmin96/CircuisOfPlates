package game.player;


import game.shapes.Shape;
import utilities.ResourceLoader;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static utilities.Properties.*;

public class PlayerUI extends JComponent {

    private BufferedImage image;
    private Player player;

    public PlayerUI(Player player, String path, int direction) {
        try {
            image = ImageIO.read(ResourceLoader.loadStream(path));
            if (player.getXCenter() == 0) {
                int xCenter = frameWidth() / 2 + SHIFT * direction;
                int yCenter = frameHeight() - (image.getHeight() / 2);
                player.setDimensions(xCenter, yCenter, image.getHeight());
            }
        } catch (IOException e) {
            throw new RuntimeException("Player Image Not Found!");
        }
        this.player = player;
        this.setBounds(player.getXCenter(), player.getYCenter(), image.getWidth(), image.getHeight());
    }

    public void updatePLayerModel(Player updatedPlayer) {
        this.player = updatedPlayer;
    }

    public Player getPlayer() {
        return player;
    }

    public void draw(Graphics2D g) {
        int topLeftXCoor = player.getXCenter() - (image.getWidth() / 2);
        int topLeftYCoor = player.getYCenter() - (image.getHeight() / 2);
        g.drawImage(image, topLeftXCoor, topLeftYCoor, null);
        this.setBounds(topLeftXCoor, topLeftYCoor, image.getWidth(), image.getHeight());
        for (int i = 0; i < player.getRightStack().size(); i++) {
            Shape shape = player.getRightStack().get(i);
            synchronized (shape) {
                shape.draw(g);
            }
        }
        for (int i = 0; i < player.getLeftStack().size(); i++) {
            Shape shape = player.getLeftStack().get(i);
            synchronized (shape) {
                shape.draw(g);
            }
        }
    }
}