package dao;

import java.util.ArrayList;

public interface DAO <E, K>{
    public boolean insert(E e);
    public E findById(K k);
    public ArrayList<E> findAll();
    public boolean delete(K k);


}
