package nictru.nf.test.anndata;

import java.nio.file.Path;
import io.jhdf.HdfFile;
import io.jhdf.GroupImpl;
import java.util.Arrays;
import java.util.Set;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
public class AnnData extends HdfFile {
    final DataFrame obs;
    final DataFrame var;

    final String[] obs_names;
    final String[] var_names;

    final int n_obs;
    final int n_vars;

    final Set<String> layers;

    public AnnData(Path path) {
        super(path);

        Set<String> fields = this.getFields();
        String[] expectedFields = {"X", "layers", "obs", "var", "obsm", "varm", "obsp", "varp", "uns"};
        List<String> missingFields = Arrays.stream(expectedFields).filter(field -> !fields.contains(field)).collect(Collectors.toList());
        if (!missingFields.isEmpty()) {
            throw new IllegalArgumentException("Missing fields: " + missingFields);
        }

        this.obs = new DataFrame((GroupImpl) this.getChild("obs"));
        this.var = new DataFrame((GroupImpl) this.getChild("var"));

        this.obs_names = this.obs.rownames;
        this.var_names = this.var.rownames;

        this.n_obs = this.obs.size;
        this.n_vars = this.var.size;

        this.layers = this.getLayers();
    }


    private Set<String> getFields() {
        return this.getChildren().keySet();
    }

    private Set<String> getLayers() {
        GroupImpl layers = (GroupImpl) this.getChild("layers");
        return layers.getChildren().keySet();
    }

    public static void main(String[] args) {
        AnnData annData = new AnnData(Path.of("merged.h5ad"));
        System.out.println(annData.layers);
        annData.close();
    }
}
