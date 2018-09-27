package com.example.demo.service;

import java.util.List;
import com.example.demo.model.Role;

public interface RoleService {
	public Role findRole(String role);
	public List<Role> findAll();
}
