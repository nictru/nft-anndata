package nictru.nf.test.anndata;

import io.jhdf.GroupImpl;
import io.jhdf.dataset.ContiguousDatasetImpl;

public class DataFrame {
    String[] colnames;
    String[] rownames;
    int size;

    public DataFrame(GroupImpl group) {
        this.colnames = getColumnNames(group);
        this.rownames = getRowNames(group);
        this.size = this.rownames.length;
    }

    private String[] getColumnNames(GroupImpl group) {
        return (String[]) group.getChildren().keySet().stream().filter(key -> !key.equals("_index"))
                .toArray(String[]::new);
    }

    public String[] getRowNames(GroupImpl group) {
        // Get the index key from attributes
        ContiguousDatasetImpl index = (ContiguousDatasetImpl) group.getChild("_index");
        return (String[]) index.getData();
    }
}
