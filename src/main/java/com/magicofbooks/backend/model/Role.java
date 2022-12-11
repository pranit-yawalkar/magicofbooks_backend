package com.magicofbooks.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {
	@Id
	@Column(name = "role_name")
	private String roleName;

	@Column(name = "role_desc")
	private String roleDescription;

	public Role() {
	}

	public Role(String roleName, String roleDescription) {
		this.roleName = roleName;
		this.roleDescription = roleDescription;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	@Override
	public String toString() {
		return "Role{" +
				"roleName='" + roleName + '\'' +
				", roleDescription='" + roleDescription + '\'' +
				'}';
	}
}
