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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import static utilities.Properties.*;

public class ChoosePlayerMenu extends JPanel implements ActionListener, IViewer {

    private static final long serialVersionUID = 1L;
    private static int topLeftBoxXAlignment;
    private static int topLeftBoxYAlignment;
    private BufferedImage backGroundImage;
    private Map<String, JButton> buttons;
    private Controller controller;
    private JPanel playerInfoPanel;
    private JTextField userName;
    private JTextField userName2;
    private JComboBox<String> levelsList;
    private boolean twoPLayers;
    private boolean savedGame;
    private String dataLevel;

    public ChoosePlayerMenu() {
        try {
            backGroundImage = ImageIO.read(ResourceLoader.loadStream(NEW_GAME));
        } catch (IOException e) {
            throw new RuntimeException("Image Not Found!");
        }
        savedGame = false;
        this.setSize(frameWidth(), frameHeight());
        this.setLayout(null);
        this.setFocusable(true);
        buttons = new LinkedHashMap<>();
        topLeftBoxXAlignment = frameWidth() / 4;
        topLeftBoxYAlignment = frameWidth() / 4;
        setButtons();
        repaint();
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setSaved(boolean current) {
        savedGame = current;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(backGroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    private void setButtons() {
        javax.swing.Box box = javax.swing.Box.createHorizontalBox();
        ImageIcon onePlayerIcon = null;
        ImageIcon twoPlayerIcon = null;
        ImageIcon mainMenuIcon = null;
        try {
            onePlayerIcon = new ImageIcon(ImageIO.read(ResourceLoader.loadStream(ONEPLAYER_BUTTON)));
            buttons.put(ONEPLAYER_BUTTON, new JButton(onePlayerIcon));
            buttons.get(ONEPLAYER_BUTTON).setName(ONEPLAYER_BUTTON);
            twoPlayerIcon = new ImageIcon(ImageIO.read(ResourceLoader.loadStream(TWOPLAYERS_BUTTON)));
            buttons.put(TWOPLAYERS_BUTTON, new JButton(twoPlayerIcon));
            buttons.get(TWOPLAYERS_BUTTON).setName(TWOPLAYERS_BUTTON);
            mainMenuIcon = new ImageIcon(ImageIO.read(ResourceLoader.loadStream(MAINMENU_BUTTON)));
            buttons.put(MAINMENU_BUTTON, new JButton(mainMenuIcon));
            buttons.get(MAINMENU_BUTTON).setName(MAINMENU_BUTTON);
        } catch (IOException e) {
            System.out.println("Button Image not found");
        }
        for (JButton button : buttons.values()) {
            button.setSize(onePlayerIcon.getIconWidth(), onePlayerIcon.getIconHeight());
            button.setOpaque(false);
            button.setBorderPainted(false);
            button.setContentAreaFilled(false);
            button.addActionListener(this);
            box.add(button);
            box.add(Box.createHorizontalStrut(onePlayerIcon.getIconWidth() / 3));
        }
        box.remove(buttons.get(MAINMENU_BUTTON));
        buttons.get(MAINMENU_BUTTON).setBounds(frameWidth() - mainMenuIcon.getIconWidth() - 100,
                frameHeight() - mainMenuIcon.getIconHeight() - 100, mainMenuIcon.getIconWidth(), mainMenuIcon.getIconHeight());
        this.add(buttons.get(MAINMENU_BUTTON));
        box.setBounds(topLeftBoxXAlignment, topLeftBoxYAlignment, 4 * twoPlayerIcon.getIconWidth(),
                twoPlayerIcon.getIconHeight());
        this.add(box);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonPressed = (JButton) e.getSource();
        twoPLayers = false;
        if (buttonPressed.getName().equals(ONEPLAYER_BUTTON)) {
            twoPLayers = false;
            buttons.get(TWOPLAYERS_BUTTON).setEnabled(false);
        }
        if (buttonPressed.getName().equals(TWOPLAYERS_BUTTON)) {
            twoPLayers = true;
            buttons.get(ONEPLAYER_BUTTON).setEnabled(false);
        }

        if (buttonPressed.getName().equals(MAINMENU_BUTTON)) {
            controller.changeDisplay(MAIN_MENU);
            reset();
            return;
        }
        playerInfoPanel = new PlayerInfoPanel(twoPLayers);
        ChoosePlayerMenu.this.add(playerInfoPanel);
        ChoosePlayerMenu.this.repaint();
    }

    public LinkedHashMap<String, Object> getConfigurations() {
        LinkedHashMap<String, Object> settings = new LinkedHashMap<>();
        ArrayList<String> names = new ArrayList<>();
        names.add(userName.getText());
        if (twoPLayers)
            names.add(userName2.getText());

        settings.put("twoPlayers", twoPLayers);
        settings.put("level", dataLevel);
        settings.put("names", names);
        return settings;
    }

    private void reset() {
        for (JButton button : buttons.values())
            button.setEnabled(true);
        userName = null;
        userName2 = null;
        levelsList.setSelectedIndex(-1);
        remove(playerInfoPanel);
    }

    private class PlayerInfoPanel extends JPanel implements ActionListener {
        private static final int WIDTH = 500;
        private static final int HEIGHT = 400;
        private static final int NAME_WIDTH = 200;
        private static final int NAME_HEIGHT = 30;
        private static final int NAME_SHIFT = 10;
        private boolean twoPlayers;
        private JButton play;
        private JLabel signUp;
        private ImageIcon signUpIcon;
        private ImageIcon playIcon;

        private PlayerInfoPanel(boolean twoPlayers) {
            this.setBackground(new Color(241, 196, 15));
            this.twoPlayers = twoPlayers;
            this.setLayout(null);
            this.setBounds(frameWidth() / 3 + 30, frameHeight() / 12, WIDTH, HEIGHT);
            try {
                signUpIcon = new ImageIcon(ImageIO.read(ResourceLoader.loadStream(SIGNUP_BUTTON)));
                signUp = new JLabel(signUpIcon);
                signUp.setOpaque(false);
                signUp.setBounds(WIDTH / 2 - signUpIcon.getIconWidth() / 2, 0, signUpIcon.getIconWidth(),
                        signUpIcon.getIconHeight());
                this.add(signUp);
                playIcon = new ImageIcon(ImageIO.read(ResourceLoader.loadStream(PLAY_BUTTON)));
                play = new JButton(playIcon);
                play.setOpaque(false);
                play.setContentAreaFilled(false);
                play.setBorderPainted(false);
                play.setBounds(WIDTH / 2 - playIcon.getIconWidth() / 2, HEIGHT - playIcon.getIconHeight() - 20,
                        playIcon.getIconWidth(), playIcon.getIconHeight());
                this.add(play);
                play.addActionListener(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (twoPlayers) {
                setInfoTwoPlayer(this);
            } else {
                setInfoOnePlayer(this);

            }
            if (!savedGame) {
                JLabel difficultyLevel = new JLabel("DIFFICULTY LEVEL");
                difficultyLevel.setFont(new Font("AR DARLING", Font.BOLD, 24));
                difficultyLevel.setBounds(NAME_SHIFT, signUpIcon.getIconHeight() + (NAME_HEIGHT * 3),
                        NAME_WIDTH + 8 * NAME_SHIFT, NAME_HEIGHT);
                final DefaultComboBoxModel<String> levels = new DefaultComboBoxModel<>();
                levels.addElement("Easy");
                levels.addElement("Medium");
                levels.addElement("Hard");
                levelsList = new JComboBox<>(levels);
                levelsList.setSelectedIndex(-1);
                levelsList.setBounds(NAME_WIDTH + 8 * NAME_SHIFT, signUpIcon.getIconHeight() + (NAME_HEIGHT * 3),
                        NAME_WIDTH, NAME_HEIGHT);
                levelsList.addActionListener(this);
                this.add(difficultyLevel);
                this.add(levelsList);
            }
        }

        private void setInfoOnePlayer(JPanel current) {
            JLabel name = new JLabel("PLAYER NAME");
            name.setFont(new Font("AR DARLING", Font.BOLD, 24));
            name.setBounds(NAME_SHIFT, signUpIcon.getIconHeight(), NAME_WIDTH, NAME_HEIGHT);
            userName = new JTextField();
            userName.setBounds(NAME_SHIFT + NAME_WIDTH, signUpIcon.getIconHeight(), NAME_WIDTH, NAME_HEIGHT);
            current.add(name);
            current.add(userName);
        }

        private void setInfoTwoPlayer(JPanel current) {
            JLabel name = new JLabel("PLAYER_ONE NAME");
            name.setFont(new Font("AR DARLING", Font.BOLD, 24));
            name.setBounds(NAME_SHIFT, signUpIcon.getIconHeight(), NAME_WIDTH + 5 * NAME_SHIFT, NAME_HEIGHT);
            userName = new JTextField();
            userName.setBounds(6 * NAME_SHIFT + NAME_WIDTH, signUpIcon.getIconHeight(), NAME_WIDTH, NAME_HEIGHT);
            JLabel name2 = new JLabel("PLAYER_TWO NAME");
            name2.setBounds(NAME_SHIFT, signUpIcon.getIconHeight() + NAME_HEIGHT, NAME_WIDTH + 6 * NAME_SHIFT,
                    NAME_HEIGHT);
            name2.setFont(new Font("AR DARLING", Font.BOLD, 24));
            userName2 = new JTextField();
            userName2.setBounds(6 * NAME_SHIFT + NAME_WIDTH, signUpIcon.getIconHeight() + NAME_HEIGHT, NAME_WIDTH,
                    NAME_HEIGHT);
            current.add(name);
            current.add(userName);
            current.add(name2);
            current.add(userName2);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == levelsList)
                dataLevel = levelsList.getItemAt(levelsList.getSelectedIndex());
            if (e.getSource() == play) {
                if ((dataLevel == null && !savedGame) || userName == null || (twoPlayers && userName2 == null)) {
                    try {
                        JLabel message = new JLabel("PLease fill the items left empty!");
                        message.setFont(new Font("Times New Roman", Font.PLAIN, 18));
                        JOptionPane.showMessageDialog(null, message, "Warning",
                                JOptionPane.ERROR_MESSAGE, new ImageIcon(ImageIO.read(ResourceLoader.loadStream(WARNING))));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    ChoosePlayerMenu.this.remove(this);
                    ChoosePlayerMenu.this.repaint();
                    ChoosePlayerMenu.this.controller.popMessage(ChoosePlayerMenu.this, savedGame);
                    reset();
                }
            }
        }
    }
}
