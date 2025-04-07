#!/usr/bin/env python

import anndata as ad
import scanpy as sc

adata = sc.datasets.pbmc3k_processed()

adata.obs.index.name = None
adata.var.index.name = None

adata.write_h5ad("pbmc3k_processed.h5ad")
