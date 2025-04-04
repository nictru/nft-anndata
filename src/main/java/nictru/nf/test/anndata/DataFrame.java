package nictru.nf.test.anndata;

import io.jhdf.GroupImpl;
import io.jhdf.dataset.ContiguousDatasetImpl;
import java.util.Arrays;
import java.util.stream.IntStream;

import java.util.HashSet;
import java.util.Set;

public class DataFrame {
    String[] colnames;
    String[] rownames;
    int size;

    public DataFrame(GroupImpl group) {
        this.colnames = getColumnNames(group);
        this.rownames = getRowNames(group);
        this.size = this.rownames.length;

        // this.getColumn(group, "n_genes_by_counts");
        System.out.println(this.getColumn(group, "chemistry").n_unique());
        System.out.println(this.getColumn(group, "n_genes_by_counts").n_unique());
        System.out.println(this.getColumn(group, "label").n_unique());
    }

    private String[] getColumnNames(GroupImpl group) {
        return (String[]) group.getChildren().keySet().stream().filter(key -> !key.equals("_index"))
                .toArray(String[]::new);
    }

    private String[] getRowNames(GroupImpl group) {
        // Get the index key from attributes
        ContiguousDatasetImpl index = (ContiguousDatasetImpl) group.getChild("_index");
        return (String[]) index.getData();
    }

    public Column getColumn(GroupImpl group, String name) {
        return new Column(group, name);
    }

    class Column {
        final Object[] data;

        public Column(GroupImpl group, String name) {
            // Can be ContiguousDatasetImpl or GroupImpl
            Object data = group.getChild(name);
            if (data instanceof ContiguousDatasetImpl) {
                Object currentData = ((ContiguousDatasetImpl) data).getData();
                if (currentData instanceof String[]) {
                    this.data = (String[]) currentData;
                } else if (currentData.getClass().isArray()) {
                    // Handle primitive arrays by converting them to Object arrays
                    if (currentData instanceof int[]) {
                        this.data = Arrays.stream((int[]) currentData).boxed().toArray(Object[]::new);
                    } else if (currentData instanceof double[]) {
                        this.data = Arrays.stream((double[]) currentData).boxed().toArray(Object[]::new);
                    } else if (currentData instanceof long[]) {
                        this.data = Arrays.stream((long[]) currentData).boxed().toArray(Object[]::new);
                    } else {
                        throw new IllegalArgumentException("Unsupported array type: " + currentData.getClass());
                    }
                } else {
                    throw new IllegalArgumentException("Expected array type, got: " + currentData.getClass());
                }
            } else if (data instanceof GroupImpl) {
                GroupImpl dataGroup = (GroupImpl) data;

                ContiguousDatasetImpl categories = (ContiguousDatasetImpl) dataGroup.getChild("categories");
                Object[] categoriesArray = (Object[]) categories.getData();

                ContiguousDatasetImpl codes = (ContiguousDatasetImpl) dataGroup.getChild("codes");
                byte[] codesArray = (byte[]) codes.getData();

                this.data = decodeCategories(categoriesArray, codesArray);
            } else {
                throw new IllegalArgumentException("Invalid data type: " + data.getClass());
            }
        }

        private Object[] decodeCategories(Object[] categories, byte[] codes) {
            return IntStream.range(0, codes.length)
                    .mapToObj(i -> categories[codes[i]])
                    .toArray();
        }

        public Set<Object> getUnique() {
            if (data == null || data.length == 0) {
                return new HashSet<>();
            }

            // Use a HashSet to count unique values
            Set<Object> uniqueValues = new HashSet<>();
            for (Object value : data) {
                uniqueValues.add(value);
            }

            return uniqueValues;
        }

        public int n_unique() {
            return this.getUnique().size();
        }
    }
}
