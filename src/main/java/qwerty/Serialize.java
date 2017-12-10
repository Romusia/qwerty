package qwerty;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.Collection;
import java.util.TreeSet;

public interface Serialize<T> {
	
	public void serialize(T obj, String title) throws IOException;
	
	public T deserialize(String title) throws IOException;
	

}
