package services;

import dao.AddressDao;
import models.AddressModel;

import java.io.IOException;
import java.util.List;

public class AddressService {
    private AddressDao addressDao = new AddressDao();

    public void addAddress(AddressModel addressModel) throws IOException {
        addressDao.add(addressModel);
    }

    public List<AddressModel> addressesList() throws IOException{
        List<AddressModel> addressModelList=addressDao.getList();
        return addressModelList;
    }

    public void updateAddress(AddressModel addressModel) throws IOException{
        addressDao.remove(addressModel.getId());
        addressDao.add(addressModel);
    }

    public void removeAddress(int id) throws IOException{
        addressDao.remove(id);
    }

    public AddressModel getAddressModel(int id) throws IOException{
        AddressModel addressModel = addressDao.findById(id);
        return addressModel;
    }
}

