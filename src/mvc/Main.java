package mvc;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Application app = new Application();
                    app.run();
                } catch (Exception e) {
                    e.printStackTrace(System.out);
                    System.err.println("Error Running the Program");
                }
            }
        });
    }
}
