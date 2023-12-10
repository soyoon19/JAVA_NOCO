package dao;

import java.util.ArrayList;

public interface DAO <E, K> extends NoPKDAO<E>{
    E findById(K k);
    boolean delete(K k);
    boolean update(E e);

}
