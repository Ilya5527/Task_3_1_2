package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly=true)
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }



    @Override
    public Role getRoleByName(String name) {
        Optional<Role> optional = roleRepository.findByName(name);

        if (optional.isEmpty()) {
            throw new NullPointerException("Такой роли не существует!");
        }

        return optional.get();
    }

    @Override
    public Role getRoleById(Long id) {
        Optional<Role> optional = roleRepository.findById(id);

        if (optional.isEmpty()) {
            throw new NullPointerException("Такой роли не существует");
        }

        return optional.get();
    }

    @Override
    public List<Role> allRoles() {
        return roleRepository.findAll();
    }


    @Override
    public Role getDefaultRole() {
        return getRoleByName("ROLE_USER");
    }

    @Override
    public Role getAdminRole() {
        return getRoleByName("ROLE_ADMIN");
    }
}
