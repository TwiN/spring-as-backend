package org.twinnation.springasbackend.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.twinnation.springasbackend.domain.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsernameIgnoreCase(String username);
	
}
