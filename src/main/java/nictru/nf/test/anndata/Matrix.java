package nictru.nf.test.anndata;

import io.jhdf.api.Dataset;

public class Matrix implements MatrixLike {
    private final int n_rows;
    private final int n_cols;

    public Matrix(Dataset dataset) {
        this.n_rows = dataset.getDimensions()[0];
        this.n_cols = dataset.getDimensions()[1];
    }

    @Override
    public int getRowCount() {
        return this.n_rows;
    }

    @Override
    public int getColumnCount() {
        return this.n_cols;
    }
}
