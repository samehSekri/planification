package com.wevioo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wevioo.model.Permission;
import com.wevioo.model.enumeration.PermissionEnum;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

	Permission findByName(PermissionEnum name);
}
