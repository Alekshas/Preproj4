package com.example.preproj4.dao;

import com.example.preproj4.security.Role;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao{


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getAllRoles() {
        return null;
    }

    @Override
    public void add(Role role) {
        entityManager.persist(role);
    }

    @Override
    public void delete(Role role) {
        entityManager.remove(entityManager.contains(role) ? role : entityManager.merge(role));
    }

    /*@PostConstruct
    public void setBaseRoleList(){
        add(new Role(1L,"ADMIN"));
        add(new Role(2L,"USER"));
    }
    @PreDestroy
    public void deleteBaseRoleList(){
        delete(new Role(1L,"ADMIN"));
        delete(new Role(2L,"USER"));
    }*/

}
