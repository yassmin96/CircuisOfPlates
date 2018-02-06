package gui;

import javax.swing.*;

public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel panel;
    private FrameClose close;

    public MainFrame(JPanel start) {
        panel = start;
        /**change feel of the button**/
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            //should log error with UIManager
            e.printStackTrace();
        }
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        close = new FrameClose();
        this.addWindowListener(close);
    }

    public void changeScene(JPanel current) {
        remove(panel);
        repaint();
        setContentPane(current);
        panel = current;
        panel.requestFocusInWindow();
        setVisible(true);
    }

    public void close() {
        close.closeWindow();
    }

    private class FrameClose extends java.awt.event.WindowAdapter {

        public void windowClosing(java.awt.event.WindowEvent windowevent) {
            closeWindow();
        }

        private void closeWindow() {
            /*if (JOptionPane.showConfirmDialog(panel,
	            "Do you want to save your artwork ?", "We shall miss you",
	            JOptionPane.YES_NO_OPTION,
	            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
            try {
                System.out.println("Saving");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Enter Valid Filename");
                return;
            }
	            
	        }*/
            System.exit(0);
        }
    }
}
