package utilities;

import java.io.File;

public interface IDynamicLinkage {
	
	// public abstract Class<?> loadClass(File file, String packageName, String className);

	public abstract Class<?> loadClass(String packageName, String className);
}
