package mvc;

import game.player.Player;
import game.shapes.Shape;
import gui.MainFrame;
import gui.panels.*;
import plateGenerator.Belt;
import utilities.ResourceLoader;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.io.IOException;
import java.util.*;

import static utilities.Properties.*;


public class Viewer implements Observer {

    private Controller controller;
    private LinkedHashMap<String, JPanel> menuPanels;
    private GameGrid gameGrid;
    private MainFrame mainFrame;

    public Viewer() {
        menuPanels = new LinkedHashMap<>();
        menuPanels.put(MAIN_MENU, new MainMenu());
        /**can be done using swing utility thread**/
        menuPanels.put(PLAYER_MENU, new ChoosePlayerMenu());
        menuPanels.put(PAUSE_MENU, new PauseMenu());
        menuPanels.put(RULES, new Rules());
        mainFrame = new MainFrame(menuPanels.get(MAIN_MENU));
        mainFrame.setSize(frameWidth(), frameHeight());
        mainFrame.setVisible(true);
    }

    public void setController(Controller controller) {
        this.controller = controller;
        Iterator itr = menuPanels.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry panel = (Map.Entry) itr.next();
            ((IViewer) panel.getValue()).setController(controller);
        }
    }

    public void goToGame() {
        mainFrame.changeScene(gameGrid);
    }

    @Override
    public void update(Observable model, Object valueChanged) {
        if (valueChanged instanceof Boolean) {
            boolean twoPlayers = (boolean) valueChanged;
            Model gameModel = (Model) model;
            gameGrid = new GameGrid(twoPlayers, gameModel.getPlayers());
            gameGrid.setController(controller);
            goToGame();
        } else if (valueChanged instanceof ArrayList) {
            if (((ArrayList) valueChanged).size() > 0) {
                if (((ArrayList) valueChanged).get(0) instanceof Shape)
                    gameGrid.updateShapes((ArrayList<Shape>) valueChanged);
                if (((ArrayList) valueChanged).get(0) instanceof Player)
                    gameGrid.updatePlayers((ArrayList<Player>) valueChanged);
                if (((ArrayList) valueChanged).get(0) instanceof Belt)
                    gameGrid.updateBelts((ArrayList<Belt>) valueChanged);
            }
        } else if (valueChanged instanceof Player) {
            WinnerView winView = new WinnerView((Player) valueChanged);
            winView.setController(controller);
            mainFrame.changeScene(winView);
        }
        gameGrid.validate();
        gameGrid.repaint();
    }

    public void readInfo() {
        setCurrentPanel(PLAYER_MENU);
        ((ChoosePlayerMenu) menuPanels.get(PLAYER_MENU)).setSaved(true);
    }

    public void setCurrentPanel(String namePanel) {
        mainFrame.changeScene(menuPanels.get(namePanel));
    }

    public void exitGame() {
        mainFrame.close();
    }
    
    public void popMessage(JPanel container, boolean savedGame) {		
    	initializeGrid(container, savedGame);
    }

    public void initializeGrid (JPanel container, boolean savedGame) {
    	if (savedGame) {
            ((ChoosePlayerMenu) menuPanels.get(PLAYER_MENU)).setSaved(false);
            ArrayList<String> names = (ArrayList<String>) ((ChoosePlayerMenu) container).getConfigurations().get("names");
            String game = "";
            for (String name : names)
                game += name;
            controller.load(game);
        } else
            controller.startGame(((ChoosePlayerMenu) container).getConfigurations());
    }

    public void confirmSaving() {
        try {
            JLabel message = new JLabel("Your game has been saved!");
            message.setFont(new Font("Times New Roman", Font.PLAIN, 18));
            JOptionPane.showMessageDialog(null, message, "",
                    JOptionPane.INFORMATION_MESSAGE, new ImageIcon(ImageIO.read(ResourceLoader.loadStream(CHECK))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}