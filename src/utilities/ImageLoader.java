package utilities;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

public class ImageLoader extends SwingWorker<BufferedImage, Void> {
    
    private String path;
    private BufferedImage loadedImage;
    private JPanel panel;
	public ImageLoader(String path, BufferedImage loadedImage, JPanel panel) {
    	this.path = path;
    	this.loadedImage = loadedImage;
    	this.panel = panel;
    }
    @Override
    public BufferedImage doInBackground() {
        BufferedImage backGroundImage = null;
		try {
			backGroundImage = ImageIO.read(ResourceLoader.loadStream(this.path));
		} catch (IOException e) {
			System.out.println("backgoundImage not found");
		}
        return backGroundImage;
    }

    @Override
    public void done() {
        
        try {
        	this.loadedImage = get();
        	if (loadedImage == null) {
        		System.out.println("null swing");
        	}
        	this.panel.repaint();
        } catch (InterruptedException ignore) {}
        catch (java.util.concurrent.ExecutionException e) {
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