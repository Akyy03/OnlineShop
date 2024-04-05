package services;

import dao.ProductDao;
import models.BrandModel;
import models.CategoryModel;
import models.ProductModel;

import java.io.IOException;
import java.util.List;

public class ProductService {
    private ProductDao productDao = new ProductDao();

    public void addProduct(ProductModel productModel) throws IOException {
        productDao.add(productModel);
    }

    public List<ProductModel> productsList() throws IOException{
        List<ProductModel> productModelList=productDao.getList();
        return productModelList;
    }

    public void updateProduct(ProductModel productModel) throws IOException{
        productDao.remove(productModel.getId());
        productDao.add(productModel);
    }

    public void removeProduct(int id) throws IOException{
        productDao.remove(id);
    }

    public ProductModel getProductModel(int id) throws IOException{
        ProductModel productModel = productDao.findById(id);
        return productModel;
    }

    public void showList() throws IOException{
        List<ProductModel> productsList = productDao.getList();
        if (productsList.isEmpty()) {
            System.out.println("No products on stock.\n");
        } else {
            System.out.println("All our products: ");
            for (ProductModel product : productsList) {
                System.out.println("Product ID: " + product.getId() + " | Product Name: " + product.getProductName());
                System.out.println("Price: " + product.getPrice() + " | Available Quantity: " + product.getQuantity());
                System.out.println("Max Quantity: " + product.getMaxQuantity());

                BrandModel brand = product.getBrand();
                if (!brand.getBrandName().isEmpty()) {
                    System.out.println("Brand: " + brand.getBrandName());
                } else {
                    System.out.println("No brand specified");
                }

                CategoryModel category = product.getCategory();
                if (!category.getCategoryName().isEmpty()) {
                    System.out.println("Category: " + category.getCategoryName() + "\n");
                } else {
                    System.out.println("No category specified\n");
                }
            }
        }
    }
}
