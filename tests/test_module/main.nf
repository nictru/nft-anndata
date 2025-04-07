process COPY_H5AD {
    input:
    path(input)

    output:
    path("*.h5ad"), emit: h5ad

    script:
    """
    cp $input ${input.baseName}.copy.h5ad
    """
}