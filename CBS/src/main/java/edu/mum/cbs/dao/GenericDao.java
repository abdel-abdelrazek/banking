package edu.mum.cbs.dao;

import java.util.List;

public interface GenericDao<T> {
	/**
     * Method that returns the number of entries from a table that meet some
     * criteria (where clause params)
     * @param params
     *  sql parameters
     * @return the number of records meeting the criteria
     */

    void save(T t);

    void delete(long id);

    T findOne(long id);

    T update(T t);   
    
    List<T> findAll();

	public List<T> findAll(String s,Object  hint );

    
    
}
