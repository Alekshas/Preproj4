package com.example.preproj4.dao;


import com.example.preproj4.security.Role;
import com.example.preproj4.security.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Set;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> rootEntry = cq.from(User.class);
        CriteriaQuery<User> all = cq.select(rootEntry);
        TypedQuery<User> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public void add(User user) {
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        entityManager.persist(user);
    }

    @Override
    public void delete(User user) {
        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }


    @Override
    public User getById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getByName(String name) {
        return entityManager.createQuery("select u from User u where u.email= :name", User.class).setParameter("name", name).getSingleResult();
    }
    /*@PostConstruct
    public  void  setUserList(){
        add(new User("Alex","Doronin",12,"lexa","qwe", Set.of(new Role(1L,"ADMIN"))));
        add(new User("ewqqwe","qwe",12,"ewq","qwe", Set.of(new Role(1L,"USER"))));
    }
    @PreDestroy
    public  void  deleteUserList(){
        delete(new User("Alex","Doronin",12,"lexa","qwe", Set.of(new Role(1L,"ADMIN"))));
        delete(new User("ewqqwe","qwe",12,"ewq","qwe", Set.of(new Role(1L,"USER"))));
    }*/
    public UserDaoImpl() {
        super();
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}

