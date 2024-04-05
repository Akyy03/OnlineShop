package services;

import dao.UserDao;
import models.UserModel;

import java.io.IOException;
import java.util.List;

public class UserService {
    private UserDao userDao = new UserDao();

    public void addUser(UserModel userModel) throws IOException {
        userDao.add(userModel);
    }

    public List<UserModel> usersList() throws IOException{
        List<UserModel> userModelList=userDao.getList();
        return userModelList;
    }

    public void updateUser(UserModel userModel) throws IOException{
        userDao.remove(userModel.getId());
        userDao.add(userModel);
    }

    public void removeUser(int id) throws IOException{
        userDao.remove(id);
    }

    public UserModel getUserModel(int id) throws IOException{
        UserModel userModel = userDao.findById(id);
        return userModel;
    }
}


