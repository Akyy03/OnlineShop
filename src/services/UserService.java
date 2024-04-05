package services;

import dao.UserDao;
import models.AddressModel;
import models.ProductModel;
import models.UserModel;

import java.io.IOException;
import java.util.List;

public class UserService {
    private UserDao userDao = new UserDao();

    public void addUser(UserModel userModel) throws IOException {
        userDao.add(userModel);
    }

    public List<UserModel> usersList() throws IOException {
        List<UserModel> userModelList = userDao.getList();
        return userModelList;
    }

    public void updateUser(UserModel userModel) throws IOException {
        userDao.remove(userModel.getId());
        userDao.add(userModel);
    }

    public void removeUser(int id) throws IOException {
        userDao.remove(id);
    }

    public UserModel getUserModel(int id) throws IOException {
        UserModel userModel = userDao.findById(id);
        return userModel;
    }

    public void showList() throws IOException {
        List<UserModel> usersList = userDao.getList();
        if (usersList.isEmpty()) {
            System.out.println("No users found.\n");
        } else {
            System.out.println("All users: ");
            for (UserModel user : usersList) {
                System.out.println("User ID: " + user.getId() + " | Name: " + user.getName());
                System.out.println("Email address: " + user.getEmail() + " | Balance: " + user.getBalance());

                AddressModel address = user.getAddress();
                if (!address.getCountry().isEmpty()) {
                    System.out.println("Country: " + address.getCountry());
                } else {
                    System.out.println("No country specified");
                }
                if (!address.getCity().isEmpty()) {
                    System.out.println("City: " + address.getCity());
                } else {
                    System.out.println("No city specified");
                }
                if (!address.getStreet().isEmpty()) {
                    System.out.println("Street: " + address.getStreet());
                } else {
                    System.out.println("No street specified");
                }
                Integer zipcode = address.getZipcode();
                if (zipcode != null && zipcode != 0) {
                    System.out.println("Zip code: " + address.getZipcode());
                } else {
                    System.out.println("No zip code specified");
                }
            }
        }
    }
}


