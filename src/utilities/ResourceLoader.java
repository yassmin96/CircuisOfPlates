package utilities;

import java.io.InputStream;
import java.net.URL;

public class ResourceLoader {

	public static InputStream loadStream(String path) {
		InputStream input = ResourceLoader.class.getClassLoader().getResourceAsStream(path);
		return input;
	}

}
