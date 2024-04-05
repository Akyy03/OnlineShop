package models;

import java.util.ArrayList;
import java.util.List;

public class UserModel extends Model {
    private String email;
    private String name;
    private float balance;
    private List<ProductModel> shoppingCart;
    private AddressModel address;

    public UserModel() {
        this.shoppingCart = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public List<ProductModel> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(List<ProductModel> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public boolean addToCart(ProductModel product, int quantity) {
        int totalQuantityInCart = 0;
        for (ProductModel item : shoppingCart) {
            if (item.getId() == product.getId()) {
                totalQuantityInCart += item.getCartQuantity();
            }
        }
        if (product.getQuantity() - totalQuantityInCart >= quantity) {
            for (ProductModel item : shoppingCart) {
                if (item.getId() == product.getId()) {
                    item.setCartQuantity(item.getCartQuantity() + quantity);
                    return true;
                }
            }
            product.setCartQuantity(quantity);
            shoppingCart.add(product);
            return true;
        } else {
            return false;
        }
    }

    public void removeFromCart(int productId) {
        ProductModel productToRemove = null;
        for (ProductModel product : this.shoppingCart) {
            if (product.getId() == productId) {
                productToRemove = product;
                break;
            }
        }
        if (productToRemove != null) {
            this.shoppingCart.remove(productToRemove);
            System.out.println("Product with ID " + productId + " was removed from your cart.");
        } else {
            System.out.println("Product with ID " + productId + " not found in your cart.");
        }
    }

    public AddressModel getAddress() {
        return address;
    }

    public void setAddress(AddressModel address) {
        this.address = address;
    }
}
