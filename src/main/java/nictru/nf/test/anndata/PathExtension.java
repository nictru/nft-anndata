package nictru.nf.test.anndata;
import java.nio.file.Path;

/*
 * Add your custom extensions to the path() method
 * 
 * @author: Nico Trummer
 */

public class PathExtension {
	public static AnnData anndata(String path) {
		return new AnnData(Path.of(path));
	}

	public static AnnData anndata(Path path) {
		return new AnnData(path);
	}
}