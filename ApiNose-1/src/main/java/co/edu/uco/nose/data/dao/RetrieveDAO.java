package co.edu.uco.nose.data.dao;

import java.util.List;

public interface RetrieveDAO<E, ID> {
	
	List<E> findAll();
	
	List<E> findByFilter(E entity);
	
	E findById(ID id);

}
