package services;

import dao.CategoryDao;
import models.CategoryModel;

import java.io.IOException;
import java.util.List;

public class CategoryService {
    private CategoryDao categoryDao = new CategoryDao();

    public void addCategory(CategoryModel categoryModel) throws IOException{
        categoryDao.add(categoryModel);
    }

    public List<CategoryModel> categoriesList() throws IOException{
        List<CategoryModel> categoryModelList=categoryDao.getList();
        return categoryModelList;
    }

    public void updateCategory(CategoryModel categoryModel) throws IOException{
        categoryDao.remove(categoryModel.getId());
        categoryDao.add(categoryModel);
    }

    public void removeCategory(int id) throws IOException{
        categoryDao.remove(id);
    }

    public CategoryModel getCategoryModel(int id) throws IOException{
        CategoryModel categoryModel = categoryDao.findById(id);
        return categoryModel;
    }

    public void showCategoriesList() throws IOException {
        List<CategoryModel> categoryModelList = categoryDao.getList();
        if (categoryModelList.isEmpty()) {
            System.out.println("No categories defined.\n");
        } else {
            categoryModelList.stream().forEach(categoryModel -> System.out.println("Category ID: " + categoryModel.getId() +
                    " | Category Name: " + categoryModel.getCategoryName()));
        }
    }
}
