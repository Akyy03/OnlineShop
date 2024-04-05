package dao;

import models.AddressModel;

public class AddressDao extends ModelDao<AddressModel> {
    public AddressDao() {
        super("addressesList");
    }
}
