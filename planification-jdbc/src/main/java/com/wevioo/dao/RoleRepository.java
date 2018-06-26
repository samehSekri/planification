package com.wevioo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wevioo.model.Role;
import com.wevioo.model.enumeration.RoleNameEnum;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(RoleNameEnum name);
}
