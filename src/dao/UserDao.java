package dao;

import models.UserModel;

public class UserDao extends ModelDao<UserModel> {
    public UserDao() {
        super("usersList");
    }
}
