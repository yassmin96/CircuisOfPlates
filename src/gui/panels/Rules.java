package gui.panels;

import mvc.Controller;
import mvc.IViewer;
import utilities.ResourceLoader;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static utilities.Properties.*;

public class Rules extends JPanel implements ActionListener, IViewer {

    private BufferedImage rules;
    private ImageIcon backIcon;
    private JButton back;
    private Controller controller;

    public Rules() {
        try {
            rules = ImageIO.read(ResourceLoader.loadStream(GAME_RULES));
            backIcon = new ImageIcon(ImageIO.read(ResourceLoader.loadStream(BACK_BUTTON)));
        } catch (IOException e) {
            throw new RuntimeException("Image Not Found!");
        }
        back = new JButton(backIcon);
        back.setSize(backIcon.getIconWidth(), backIcon.getIconHeight());
        back.setBounds(frameWidth() / 2 - backIcon.getIconWidth() / 2, frameHeight() - backIcon.getIconHeight(),
                backIcon.getIconWidth(), backIcon.getIconHeight());
        back.setBorderPainted(false);
        back.setContentAreaFilled(false);
        back.addActionListener(this);
        add(back);
        setSize(frameWidth(), frameHeight());
        setLayout(null);
        setFocusable(true);
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(rules, 0, 0, getWidth(), getHeight(), this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back)
            controller.changeDisplay(MAIN_MENU);
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }
}
