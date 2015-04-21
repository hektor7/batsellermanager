package org.hektor7.batsellermanager.domain.repository;

import java.io.Serializable;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends
		Repository<T, ID> {
	public T save(T entity);
	
}
