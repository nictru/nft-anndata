# Changelog

## [Unreleased]

## [0.5.0] - 2026-05-28

- Bump to nf-anndata [0.5.0](https://github.com/nictru/nf-anndata/releases/tag/v0.5.0)

## [0.4.1] - 2026-03-25

- Bump to nf-anndata [0.4.1](https://github.com/nictru/nf-anndata/releases/tag/v0.4.1)

## [0.4.0] - 2026-03-23

- Bump to nf-anndata [0.4.0](https://github.com/nictru/nf-anndata/releases/tag/v0.4.0)

## [0.3.5] - 2026-03-23

- Fix chunked HDF5 dataset support by compiling against the patched jhdf fork from [nf-anndata](https://github.com/nictru/nf-anndata) instead of upstream `io.jhdf:jhdf:0.9.2`
- This fixes loading of R-generated h5ad files (e.g. decontX output) which use chunked storage with byte-shuffle compression

## [0.3.4] - 2026-03-17

- Bump to nf-anndata [0.3.4](https://github.com/nictru/nf-anndata/releases/tag/v0.3.4)

## [0.3.3] - 2026-03-16

This release aligns nft-anndata with nf-anndata [0.3.3](https://github.com/nictru/nf-anndata/releases/tag/v0.3.3) and marks a structural change: **nft-anndata is now a lightweight wrapper around nf-anndata**. The core AnnData reading logic (`AnnData`, `DataFrame`, `DataFrameColumn`, `DataFrameIndex`) is no longer maintained in this repository — it lives in nf-anndata and is pulled in as a git submodule at build time.

This means nft-anndata automatically benefits from all fixes and improvements made in nf-anndata, including:
- Support for chunked HDF5 datasets
- Named indices, integer indices, empty observations
- Nullable columns, all numeric types, unicode support

## [0.1.0]

Initial release of nft-anndata