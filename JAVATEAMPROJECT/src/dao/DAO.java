package dao;

import java.util.ArrayList;

public interface DAO <E, K>{
    public E save(E e);
    public ArrayList<E> findById(K k);
    public ArrayList<E> findAll();
    public boolean delete(K k);

}
