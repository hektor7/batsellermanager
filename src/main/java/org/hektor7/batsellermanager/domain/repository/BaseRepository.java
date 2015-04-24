package org.hektor7.batsellermanager.domain.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

/**
 * 
 * Repository for as base of our repositories.
 * 
 * @author hector
 *
 * @param <T>
 *            Entity
 * @param <ID>
 *            Class of the id attribute
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends
		Repository<T, ID> {

	/**
	 * It saves and updates the entity.
	 * 
	 * @param entity
	 *            to be saved
	 * @return saved entity
	 */
	public T save(T entity);
	
	/**
	 * It retrieves all elements from database.
	 * @return List of given entity
	 */
	public List<T> findAll();
	
	/**
	 * It retrieves an entity by the given Id.
	 * 
	 * @param id Entity's id
	 * @return entity
	 */
	public T findOne(ID id);
	
	/**
	 * It deletes a given entity.
	 * 
	 * @param entity to be deleted
	 */
	public void delete(T entity);
	
	

}
