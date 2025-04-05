package nictru.nf.test.anndata;

import io.jhdf.GroupImpl;
import io.jhdf.api.Dataset;

import java.util.Map;
import java.util.HashMap;
public class Matrices {
    private final Map<String, MatrixLike> matrices = new HashMap<>();

    public Matrices(GroupImpl group) {
        group.getChildren().forEach((key, value) -> {
            System.out.println(key + ": " + value.getType());

            if (value instanceof GroupImpl) {
                if (((GroupImpl) value).getChildren().containsKey("indptr")) {
                    this.matrices.put(key, new SparseMatrix((GroupImpl) value));
                } else {
                    this.matrices.put(key, new DataFrame((GroupImpl) value));
                }

            } else if (value instanceof Dataset) {
                this.matrices.put(key, new Matrix((Dataset) value));
            }
        });
    }

    public MatrixLike get(String key) {
        return this.matrices.get(key);
    }
}
