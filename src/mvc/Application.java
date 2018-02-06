package mvc;

import game.GameThread;

public class Application {

    private Controller gameController;
    private Model gameModel;
    private Viewer gameViewer;
    private Thread thread;
    /* to be singleton */

    public Application() {

    }

    public void run() throws InterruptedException {
        gameViewer = new Viewer();
        gameModel = new Model(gameViewer);
        gameController = new Controller(gameModel, gameViewer);
        thread = new Thread(new GameThread(gameModel));
        thread.start();
    }
}
