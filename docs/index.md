# nft-anndata

nf-test plugin for the AnnData file format

## Start using the plugin

To start using the plugin please add it to your `nf-test.config` file:

```groovy title="nf-test.config"
config {
    plugins {
        load "nft-anndata@0.1.0"
    }
}
```

## Use a development version

To use the development version, please do the following steps:

- Clone the [nft-anndata repository](https://github.com/nictru/nft-anndata)

=== "HTTPS"

    ```bash
    git clone https://github.com/nictru/nft-anndata.git
    ```

=== "SSH"

    ```bash
    git clone git@github.com:nictru/nft-anndata.git
    ```

- Run the build script

```bash
./build.sh
```

- Add the jar location (visible at the end of the build script output) to the `nf-test.config` file

```groovy title="nf-test.config"
config {
    plugins {
        loadFromFile "full/path/to/the/plugin/jar"
    }
}
```

- Or add the plugin jar to an nf-test command:

```bash title="Terminal"
nf-test test --plugins full/path/to/the/plugin/jar
```

!!! note

    The plugin jar will always be located in the `target/` directory in the root of the plugin repository


