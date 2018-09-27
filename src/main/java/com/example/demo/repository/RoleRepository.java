package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Role;

/**
 * Annotation of Spring. Indicates that the class is related to the persistence layer and that it must be a singleton
 *
 */
@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer>{
	Role findByRole(String role);
	List<Role> findAll();

}
