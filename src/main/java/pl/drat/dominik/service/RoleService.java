package pl.drat.dominik.service;

import pl.drat.dominik.dao.entity.Role;

import java.util.List;

public interface RoleService {

    Role createRole(Role role);

    Role getRole(Long id);

    Role updateRole(Long id, Role role);

    void deleteRole(Long id);

    List<Role> getRoleList();
}
