package org.hektor7.batsellermanager.domain.repository;

import org.hektor7.batsellermanager.domain.AppUser;
import org.springframework.data.repository.CrudRepository;

public interface AppUserRepository extends CrudRepository<AppUser, Long>{
	
}
