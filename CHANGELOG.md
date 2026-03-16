# Changelog

## [Unreleased]

## [0.3.3] - 2026-03-16

This release aligns nft-anndata with nf-anndata [0.3.3](https://github.com/nictru/nf-anndata/releases/tag/v0.3.3) and marks a structural change: **nft-anndata is now a lightweight wrapper around nf-anndata**. The core AnnData reading logic (`AnnData`, `DataFrame`, `DataFrameColumn`, `DataFrameIndex`) is no longer maintained in this repository — it lives in nf-anndata and is pulled in as a git submodule at build time.

This means nft-anndata automatically benefits from all fixes and improvements made in nf-anndata, including:
- Support for chunked HDF5 datasets
- Named indices, integer indices, empty observations
- Nullable columns, all numeric types, unicode support

## [0.1.0]

Initial release of nft-anndata