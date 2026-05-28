# nft-anndata

nf-test plugin for AnnData stores in `.h5ad` and `.zarr` formats.

> **Note:** As of v0.3.3, nft-anndata is a lightweight wrapper around [nf-anndata](https://github.com/nictru/nf-anndata). The core reading logic is maintained there and pulled in as a git submodule. nft-anndata automatically inherits all fixes and new features from nf-anndata releases.

## Features

- `n_vars` and `n_obs` dimensions
- Dataframes (`obs` and `var`)
  - Column names and row names
  - Named, integer, and unnamed indices
  - Per-column data access (all numeric types, categorical, boolean, nullable, string)
  - Unique values and unique count per column
- Keys of available fields in `layers`, `obsm`, `varm`, `obsp`, `varp`, `uns`
- Sparse and dense X matrix support
- Chunked and compressed HDF5 dataset support for `.h5ad`
- AnnData-on-Zarr v2 directory store support for `.zarr`

Examples can be found [here](https://github.com/nictru/nft-anndata/blob/main/tests/test_module/methods.nf.test).

## Architecture

nft-anndata provides two nf-test integration points:

- **`anndata(path)`** — global function callable in any `then {}` block
- **`path(file).anndata()`** — extension on nf-test's `path()` object

Both return an `AnnData` instance from [nf-anndata](https://github.com/nictru/nf-anndata), which does the actual `.h5ad` or `.zarr` parsing.
