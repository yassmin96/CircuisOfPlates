package utilities;
 
import java.awt.Toolkit;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;
 
public class DynamicLinkage implements IDynamicLinkage {

    private static final String shapeJarPath = "jars/shapes";
    private URL url;
    private URLClassLoader classLoader;
    private Class<?> loadedClass;
   
    public DynamicLinkage() {
 
    }
 
    @Override
    public Class<?> loadClass(String packageName, String className) {
        try {
             url = ClassLoader.getSystemClassLoader().getResource( shapeJarPath + "/" + className + ".jar" );
             File temp = File.createTempFile(className, ".txt");
             FileUtils.copyURLToFile(url, temp);
             URL urltemp = temp.toURI().toURL();
        	
            classLoader = new URLClassLoader(new URL[] { urltemp });   
            loadedClass = classLoader.loadClass(packageName + "." + className);
        } catch (Exception e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Cannot Load Class!", "Warning", JOptionPane.PLAIN_MESSAGE);
            e.printStackTrace();
        }
        return loadedClass;
    }
}

