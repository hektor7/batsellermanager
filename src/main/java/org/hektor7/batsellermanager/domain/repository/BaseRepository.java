package org.hektor7.batsellermanager.domain.repository;

import java.io.Serializable;

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

}
