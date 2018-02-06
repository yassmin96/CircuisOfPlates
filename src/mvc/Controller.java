package mvc;


import game.player.PlayerUI;

import javax.swing.*;
import java.util.LinkedHashMap;

import static utilities.Properties.MAIN_MENU;
import static utilities.Properties.PAUSE_MENU;

public class Controller {


    protected Model model;
    protected Viewer viewer;

    public Controller(Model model, Viewer viewer) {
        this.model = model;
        this.viewer = viewer;
        this.viewer.setController(this);
    }

    public void movePlayer(PlayerUI playerUI, int step) {
        model.movePlayer(playerUI.getPlayer(), step);
    }

    public void pauseGame() {
        model.pauseGame();
        viewer.setCurrentPanel(PAUSE_MENU);
    }

    public void continueGame() {
        model.continueGame();
        viewer.goToGame();
    }

    public void save() {
        model.save();
        viewer.confirmSaving();
        viewer.setCurrentPanel(MAIN_MENU);
    }

    public void load(String game) {
        model.load(game);
    }

    public void loadInfo() {
        viewer.readInfo();
    }

    public void popMessage(JPanel container, boolean savedGame) {
        viewer.popMessage(container, savedGame);
    }

    public void changeDisplay(String namePanel) {
        viewer.setCurrentPanel(namePanel);
    }

    public void startGame(LinkedHashMap<String, Object> settings) {
        model.startGame(settings, true);
    }

    public void exitGame() {
        viewer.exitGame();
    }

    public void playAgain() {
        viewer.goToGame();
        model.playAgain();
    }
}