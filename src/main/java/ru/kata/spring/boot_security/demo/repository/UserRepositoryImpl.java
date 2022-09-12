package ru.kata.spring.boot_security.demo.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void deleteById(long id) {
//        entityManager.createQuery("delete from User u where u.id = :id", User.class).setParameter("id", id).executeUpdate();
        User user = findById(id);
        entityManager.remove(user);
    }

    @Override
    public User findByUsername(String username) {
        User user = null;
        try {
            user = entityManager.createQuery("select u from User u where u.username = :username ", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new NoResultException("Пользователь с такими данными не найден, пожалуйста пройдите регистрацию");
        }
        return user;
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public List<User> findAll() {

        List<User> userList = entityManager.createQuery("select u from User u", User.class).getResultList();

        return userList;
    }

    @Override
    public User findById(long id) {
        return entityManager.find(User.class, id);
    }
}
