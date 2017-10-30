package pl.drat.dominik.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.drat.dominik.dao.entity.Role;
import pl.drat.dominik.dao.repository.RoleRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository repository;

    @Override
    public Role createRole(Role role) {
        return repository.save(role);
    }

    @Override
    public Role getRole(Long id) {
        return repository.findOne(id);
    }

    @Override
    public Role updateRole(Long id, Role role) {
        Role existingRole = repository.findOne(id);
        existingRole.setRole(role.getRole());
        return repository.save(existingRole);
    }

    @Override
    public void deleteRole(Long id) {
        repository.delete(id);
    }

    @Override
    public List<Role> getRoleList() {
        return (List<Role>) repository.findAll();
    }
}
