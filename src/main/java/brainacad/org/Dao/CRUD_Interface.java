package brainacad.org.Dao;

import brainacad.org.Dao.DB_Dao.QueryExecutor;
import brainacad.org.Models.Customer.Customer;
import brainacad.org.Models.Language.Language;

import java.util.List;

public interface CRUD_Interface<T>
{
    int add(T t);
    int update(T t);
    int delete(int id);
    void showAll();
    boolean checkOFNull(T t);
}
