package gui.panels;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import mvc.Controller;
import mvc.IViewer;
//import utilities.ImageLoader;
import utilities.ResourceLoader;

import static utilities.Properties.*;

public class MainMenu extends JPanel implements ActionListener, IViewer {

	private static final long serialVersionUID = 1L;
	private static int topLeftBoxXAlignment;
	private static int topLeftBoxYAlignment;
	private ImageLoader imageLoader;
	private BufferedImage backGroundImage;
	private Map<String, JButton> buttons;
	private Controller controller;

	public MainMenu() {
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
		if (buttonPressed.getName().equals(NEW_GAME_BUTTON))
			controller.changeDisplay(PLAYER_MENU);
		if (buttonPressed.getName().equals(LOAD_BUTTON))
			controller.loadInfo();
		if (buttonPressed.getName().equals(QUIT_BUTTON))
			controller.exitGame();
		if (buttonPressed.getName().equals(RULES_BUTTON)) {
			controller.changeDisplay(RULES);
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		if (backGroundImage != null) {
			g.drawImage(backGroundImage, 0, 0, getWidth(), getHeight(), this);
		} else {
			System.out.println(" main menu background null");
		}
	}

	private void setButtons() {

		javax.swing.Box box = javax.swing.Box.createVerticalBox();
		ImageIcon newGameIcon = null;
		ImageIcon loadIcon = null;
		ImageIcon rulesIcon = null;
		ImageIcon quitIcon = null;
		try {
			newGameIcon = new ImageIcon(ImageIO.read(ResourceLoader.loadStream(NEW_GAME_BUTTON)));
			buttons.put(NEW_GAME_BUTTON, new JButton(newGameIcon));
			buttons.get(NEW_GAME_BUTTON).setName(NEW_GAME_BUTTON);
			loadIcon = new ImageIcon(ImageIO.read(ResourceLoader.loadStream(LOAD_BUTTON)));
			buttons.put(LOAD_BUTTON, new JButton(loadIcon));
			buttons.get(LOAD_BUTTON).setName(LOAD_BUTTON);
			rulesIcon = new ImageIcon(ImageIO.read(ResourceLoader.loadStream(RULES_BUTTON)));
			buttons.put(RULES_BUTTON, new JButton(rulesIcon));
			buttons.get(RULES_BUTTON).setName(RULES_BUTTON);
			quitIcon = new ImageIcon(ImageIO.read(ResourceLoader.loadStream(QUIT_BUTTON)));
			buttons.put(QUIT_BUTTON, new JButton(quitIcon));
			buttons.get(QUIT_BUTTON).setName(QUIT_BUTTON);
		} catch (IOException e) {
			System.out.println("Button Image not found");
		}
		for (JButton button : buttons.values()) {
			button.setSize(newGameIcon.getIconWidth(), newGameIcon.getIconHeight());
			button.setOpaque(false);
			button.setBorderPainted(false);
			button.setContentAreaFilled(false);
			button.addActionListener(this);
			box.add(button);

		}
		box.setBounds(topLeftBoxXAlignment, topLeftBoxYAlignment, newGameIcon.getIconWidth(),
				5 * newGameIcon.getIconHeight());
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
				MainMenu.this.repaint();
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