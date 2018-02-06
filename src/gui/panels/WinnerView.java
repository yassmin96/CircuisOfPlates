package gui.panels;

import java.awt.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.*;

import game.player.Player;
import mvc.Controller;
import mvc.IViewer;
import utilities.ResourceLoader;

import static utilities.Properties.*;

public class WinnerView extends JPanel implements ActionListener,IViewer {

	private static final long serialVersionUID = 1L;
	private static int topLeftBoxXAlignment;
	private static int topLeftBoxYAlignment;
	private BufferedImage backGroundImage;
	private Map<String, JButton> buttons;
	private Controller controller;
	
	public WinnerView(Player player) {
		setSize(frameWidth(), frameHeight());
		try {
			backGroundImage = ImageIO.read(ResourceLoader.loadStream(NEW_GAME));
		} catch (IOException e) {
			throw new RuntimeException("Image Not Found!");
		}
		this.setLayout(null);
		this.setFocusable(true);
		try {
			JLabel message = new JLabel(player.getName() + " wins with "
					+ Integer.toString(player.getScore()) + " points");
			message.setFont(new Font("Times New Roman", Font.PLAIN, 18));
			JOptionPane.showMessageDialog(null, message, "Game Over",
                    JOptionPane.INFORMATION_MESSAGE, new ImageIcon(ImageIO.read(ResourceLoader.loadStream(TROPHY))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		topLeftBoxXAlignment = frameWidth() / 10;
		topLeftBoxYAlignment = frameWidth() / 10;
		buttons = new LinkedHashMap<>();
		setButtons();
		repaint();
	}

	public void setController(Controller controller){
		this.controller= controller;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton buttonPressed = (JButton) e.getSource();
		if (buttonPressed.getName().equals(NEW_GAME_BUTTON))
			controller.changeDisplay(PLAYER_MENU);
		if (buttonPressed.getName().equals(QUIT_BUTTON))
			controller.exitGame();
		if (buttonPressed.getName().equals(CONTINUE_BUTTON)) {
			controller.playAgain();
		}		
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(backGroundImage, 0, 0, getWidth(), getHeight(), this);
	}

	private void setButtons() {
		javax.swing.Box box = javax.swing.Box.createVerticalBox();
		ImageIcon continueIcon = null;
		ImageIcon newGameIcon = null;
		ImageIcon quitIcon = null;
		try {
			continueIcon = new ImageIcon(ImageIO.read(ResourceLoader.loadStream(CONTINUE_BUTTON)));
			buttons.put(CONTINUE_BUTTON, new JButton(continueIcon));
			buttons.get(CONTINUE_BUTTON).setName(CONTINUE_BUTTON);
			newGameIcon = new ImageIcon(ImageIO.read(ResourceLoader.loadStream(NEW_GAME_BUTTON)));
			buttons.put(NEW_GAME_BUTTON, new JButton(newGameIcon));
			buttons.get(NEW_GAME_BUTTON).setName(NEW_GAME_BUTTON);
			quitIcon = new ImageIcon(ImageIO.read(ResourceLoader.loadStream(QUIT_BUTTON)));
			buttons.put(QUIT_BUTTON, new JButton(quitIcon));
			buttons.get(QUIT_BUTTON).setName(QUIT_BUTTON);
		} catch (IOException e) {
			System.out.println("Button Image not found");
		}
		for (JButton button : buttons.values()) {
			button.setSize(continueIcon.getIconWidth(), continueIcon.getIconHeight());
			button.setOpaque(false);
			button.setBorderPainted(false);
			button.setContentAreaFilled(false);
			button.addActionListener(this);
			box.add(button);

		}
		box.setBounds(topLeftBoxXAlignment, topLeftBoxYAlignment, continueIcon.getIconWidth(),
				5 * continueIcon.getIconHeight());
		this.add(box);

	}
}

