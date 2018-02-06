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
import java.util.LinkedHashMap;
import java.util.Map;

import static utilities.Properties.*;

public class PauseMenu extends JPanel implements ActionListener, IViewer {

	private static final long serialVersionUID = 1L;
	private static int topLeftBoxXAlignment;
	private static int topLeftBoxYAlignment;
	private ImageLoader imageLoader;
	private BufferedImage backGroundImage;
	private Map<String, JButton> buttons;
	private Controller controller;

	public PauseMenu() {
		this.setSize(frameWidth(), frameHeight());
		imageLoader = new ImageLoader();
		imageLoader.execute();
		buttons = new LinkedHashMap<>();
		setSize(frameWidth(), frameHeight());
		topLeftBoxXAlignment = frameWidth() / 10;
		topLeftBoxYAlignment = frameWidth() / 10;
		this.setLayout(null);
		this.setFocusable(true);
		setButtons();
		repaint();
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton buttonPressed = (JButton) e.getSource();
		if (buttonPressed.getName().equals(CONTINUE_BUTTON))
			controller.continueGame();
		if (buttonPressed.getName().equals(SAVE_BUTTON))
			controller.save();
		if (buttonPressed.getName().equals(MAINMENU_BUTTON))
			controller.changeDisplay(MAIN_MENU);
	}

	@Override
	public void paintComponent(Graphics g) {

		if (backGroundImage != null) {
			g.drawImage(backGroundImage, 0, 0, getWidth(), getHeight(), this);
		} else {
			System.out.println("pause menu background null");
		}
	}

	private void setButtons() {
		javax.swing.Box box = javax.swing.Box.createVerticalBox();
		ImageIcon continueGameIcon = null;
		ImageIcon saveIcon = null;
		ImageIcon mainMenuIcon = null;
		try {
			continueGameIcon = new ImageIcon(ImageIO.read(ResourceLoader.loadStream(CONTINUE_BUTTON)));
			buttons.put(CONTINUE_BUTTON, new JButton(continueGameIcon));
			buttons.get(CONTINUE_BUTTON).setName(CONTINUE_BUTTON);
			saveIcon = new ImageIcon(ImageIO.read(ResourceLoader.loadStream(SAVE_BUTTON)));
			buttons.put(SAVE_BUTTON, new JButton(saveIcon));
			buttons.get(SAVE_BUTTON).setName(SAVE_BUTTON);
			mainMenuIcon = new ImageIcon(ImageIO.read(ResourceLoader.loadStream(MAINMENU_BUTTON)));
			buttons.put(MAINMENU_BUTTON, new JButton(mainMenuIcon));
			buttons.get(MAINMENU_BUTTON).setName(MAINMENU_BUTTON);

		} catch (IOException e) {
			System.out.println("Button Image not found");
		}
		for (JButton button : buttons.values()) {
			button.setSize(mainMenuIcon.getIconWidth(), mainMenuIcon.getIconHeight());
			button.setOpaque(false);
			button.setBorderPainted(false);
			button.setContentAreaFilled(false);
			button.addActionListener(this);
			box.add(button);

		}
		box.setBounds(topLeftBoxXAlignment, topLeftBoxYAlignment, mainMenuIcon.getIconWidth(),
				3 * mainMenuIcon.getIconHeight());
		this.add(box);
	}

	private class ImageLoader extends SwingWorker<BufferedImage, Void> {
		@Override
		public BufferedImage doInBackground() {
			BufferedImage backGroundImage = null;
			try {
				backGroundImage = ImageIO.read(ResourceLoader.loadStream(NEW_GAME));
			} catch (IOException e) {
				System.out.println("backgoundImage not found");
			}
			return backGroundImage;
		}

		@Override
		public void done() {

			try {
				backGroundImage = get();
			} catch (InterruptedException ignore) {
			} catch (java.util.concurrent.ExecutionException e) {
				String why = null;
				Throwable cause = e.getCause();
				if (cause != null) {
					why = cause.getMessage();
				} else {
					why = e.getMessage();
				}
				System.err.println("Error retrieving file: " + why);
			}
		}
	}
}
