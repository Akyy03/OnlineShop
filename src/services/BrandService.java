package services;

import dao.BrandDao;
import models.BrandModel;

import java.io.IOException;
import java.util.List;

public class BrandService {
    private BrandDao brandDao = new BrandDao();

    public void addBrand(BrandModel brandModel) throws IOException {
        brandDao.add(brandModel);
    }

    public List<BrandModel> brandsList() throws IOException{
        List<BrandModel> brandModelList=brandDao.getList();
        return brandModelList;
    }

    public void updateBrand(BrandModel brandModel) throws IOException{
        brandDao.remove(brandModel.getId());
        brandDao.add(brandModel);
    }

    public void removeBrand(int id) throws IOException{
        brandDao.remove(id);
    }

    public BrandModel getBrandModel(int id) throws IOException{
        BrandModel brandModel = brandDao.findById(id);
        return brandModel;
    }

    public void showBrandsList() throws IOException{
        List<BrandModel> brandModelList=brandDao.getList();
        brandModelList.stream().forEach(brandModel -> System.out.println("Brand ID: " + brandModel.getId() +
                " | Brand Name: " + brandModel.getBrandName()));
    }
}
