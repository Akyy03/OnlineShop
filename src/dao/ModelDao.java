package dao;

import models.Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public  class ModelDao <T extends Model>implements dao<T> {
    private String fileName;
    private FileScanner<T> fileScanner=new FileScanner<T>();

    public ModelDao(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void add(T t) throws IOException {
        File file=new File(fileName);
        List<T> list=new ArrayList<>();
        if (file.exists()){
            list=fileScanner.read(fileName);
        }
        list.add(t);
        fileScanner.write(list, fileName);
    }

    @Override
    public List<T> getList() throws IOException {
        List<T> list=fileScanner.read(fileName);
        return list;
    }

    @Override
    public void remove(int id) throws IOException {
        List<T> list=fileScanner.read(fileName);
        list.removeIf(t -> t.getId()==id);
        fileScanner.write(list,fileName);
    }

    @Override
    public T findById(int id) throws IOException {
        List<T> list=fileScanner.read(fileName);

        return list.stream().filter(t -> t.getId()==id).findFirst().get();
    }
}
