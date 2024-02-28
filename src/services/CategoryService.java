package services;

import models.CategoryModel;
import models.ProductModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CategoryService {
    private static List<CategoryModel> categoriesList = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    private static int idCounter = 1;

    public void showCategories() {
        if (categoriesList.isEmpty()) {
            System.out.println("No categories defined.\n");
        } else {
            System.out.println("Categories: ");
            for (CategoryModel category : categoriesList) {
                System.out.println("Category ID: " + category.getId()
                        + " | Category Name: " + category.getCategoryName());
            }
        }
    }

    public void addCategory() {
        while (true) {
            CategoryModel categoryModel = new CategoryModel();
            System.out.println("Enter a category name (or type 'quit' to cancel): ");
            String categoryName = scanner.nextLine();
            if (categoryName.equalsIgnoreCase("quit")) {
                break;
            }

            categoryModel.setId(idCounter++);
            categoryModel.setCategoryName(categoryName);
            categoriesList.add(categoryModel);

            System.out.println("Category added successfully!\n");
        }
    }

    public void updateCategory() {
        System.out.println("Enter the ID of the category that you wish to update: ");
        int categoryId = scanner.nextInt();
        scanner.nextLine();

        CategoryModel category = null;
        for (CategoryModel category2 : categoriesList) {
            if (category2.getId() == categoryId) {
                category = category2;
                break;
            }
        }

        if (category == null) {
            System.out.println("Category with ID " + categoryId + " not found.");
            return;
        }

        System.out.println("Enter a new category name (or leave blank to keep current): ");
        String newCategoryName = scanner.nextLine();
        if (!newCategoryName.isEmpty()) {
            category.setCategoryName(newCategoryName);
        }

        System.out.println("Category updated successfully!\n");
    }

    public void removeCategory() {
        System.out.println("Enter the ID of the category that you wish to remove: ");
        int categoryId = scanner.nextInt();
        scanner.nextLine();

        CategoryModel removedCategory = null;
        for (int i = 0; i < categoriesList.size(); i++) {
            CategoryModel category = categoriesList.get(i);
            if (category.getId() == categoryId) {
                removedCategory = categoriesList.remove(i);
                break;
            }
        }

        if (removedCategory == null) {
            System.out.println("Category with ID " + categoryId + " not found.");
        } else {
            System.out.println("Category removed successfully!\n");
        }
    }
}
