package nictru.nf.test.anndata;
import java.nio.file.Path;

/*
 * Add your custom methods to nf-test
 * 
 * @author: Nico Trummer
 */

public class Methods {

    public static AnnData anndata(String path) {
        return new AnnData(Path.of(path));
    }

    public static AnnData anndata(Path path) {
        return new AnnData(path);
    }

    /**
     * Check if a specific layer exists in the AnnData object.
     *
     * @param annData The AnnData object.
     * @param layerName The name of the layer to check.
     * @return True if the layer exists, false otherwise.
     */
    public static boolean isLayerPresent(AnnData annData, String layerName) {
        return annData.layers.contains(layerName);
    }
}