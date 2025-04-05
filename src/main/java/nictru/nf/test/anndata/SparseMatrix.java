package nictru.nf.test.anndata;

import io.jhdf.GroupImpl;
import io.jhdf.api.Dataset;
import java.util.Arrays;
public class SparseMatrix implements MatrixLike {

    public SparseMatrix(GroupImpl group) {
        Dataset data = (Dataset) group.getChild("data");
        Dataset indices = (Dataset) group.getChild("indices");
        Dataset indptr = (Dataset) group.getChild("indptr");

        System.out.println(Arrays.toString(data.getDimensions()));
        System.out.println(Arrays.toString(indices.getDimensions()));
        System.out.println(Arrays.toString(indptr.getDimensions()));
    }

    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public int getColumnCount() {
        return 0;
    }
}
