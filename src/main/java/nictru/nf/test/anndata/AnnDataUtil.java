package nictru.nf.test.anndata;

import io.jhdf.GroupImpl;
import io.jhdf.HdfFile;
import io.jhdf.dataset.ContiguousDatasetImpl;

import java.nio.file.Path;
import java.util.Arrays;

public class AnnDataUtil {
    public static void main(String[] args) {
        readAnnData(Path.of("merged.h5ad"));
    }

    public static void readAnnData(Path path) {
        HdfFile file = new HdfFile(path);
        file.getChildren().forEach((key, value) -> {
            System.out.println(key + ": " + value.getType());
        });

        // Print all attributes of obs
        GroupImpl obs = (GroupImpl) file.getChild("obs"); // class io.jhdf.GroupImpl
        GroupImpl condition = (GroupImpl) obs.getChild("condition");
        condition.getChildren().forEach((key, value) -> {
            System.out.println(key + ": " + value.getType());
        });
        ContiguousDatasetImpl categories = (ContiguousDatasetImpl) condition.getChild("categories");
        String[] categoriesArray = (String[]) categories.getData();

        ContiguousDatasetImpl codes = (ContiguousDatasetImpl) condition.getChild("codes");
        byte[] codesArray = (byte[]) codes.getData();

        String[] decodedCategories = decodeCategories(categoriesArray, codesArray);
        // Count occurrences of each unique category
        java.util.Map<String, Long> categoryCounts = Arrays.stream(decodedCategories)
                .collect(java.util.stream.Collectors.groupingBy(
                        category -> category,
                        java.util.stream.Collectors.counting()
                ));
        System.out.println("Category counts: " + categoryCounts);

        Object data = file.getChild("obs").getAttribute("column-order").getData();

        String[] columnOrder = (String[]) data;
        System.out.println(Arrays.toString(columnOrder));

        file.close();
    }

    /*
     * Given a list of existing categories and the codes encoding a category for each observation,
     * return the list of categories for each observation.
     */
    private static String[] decodeCategories(String[] categories, byte[] codes) {
        String[] decodedCategories = new String[codes.length];
        for (int i = 0; i < codes.length; i++) {
            int index = codes[i];
            decodedCategories[i] = categories[index];
        }
        return decodedCategories;
    }
}
