package dao;
import java.io.IOException;
import java.util.List;

public interface dao <T> {

    public void add(T t) throws IOException;

    public List<T> getList() throws IOException;

    public void remove (int cod) throws IOException;

    public T findById(int id) throws IOException;

}