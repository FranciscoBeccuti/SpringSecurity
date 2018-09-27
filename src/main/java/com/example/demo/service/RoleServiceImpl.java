package com.example.demo.service;


import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Role;
import com.example.demo.repository.RoleRepository;

/**
 * This class has two functions, be a bean in the service layer and serve the RestFull
 *
 */
@Service("roleService")
@Path("/role")
public class RoleServiceImpl implements RoleService{

	
	@Autowired
    private RoleRepository roleRepository;

	/**
	 * The method has two functions, that of returning a ROLE if it is consulted by another class or returning a ROLE in JSON format when the url is entered "/role/Role" where Role is an name of role.
	 * The "Path ("/{role}") defines that it receives a variable. That variable is assigned with the "PathParam" annotation
	 * @params Role, name of the type of role.
	 * @return object of Role
	 */
	
    @GET
    @Produces("application/json")
    @Path("/{role}")
	@Override
	public Role findRole(@PathParam("role") String role) {
		return roleRepository.findByRole(role);
	}
    
    
    @GET
    @Produces("application/json")
	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	

}
