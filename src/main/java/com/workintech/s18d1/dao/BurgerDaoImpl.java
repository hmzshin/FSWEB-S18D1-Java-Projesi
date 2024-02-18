package com.workintech.s18d1.dao;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.exceptions.BurgerException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Repository
public class BurgerDaoImpl implements BurgerDao {

    private EntityManager entityManager;

    @Transactional
    @Override
    public Burger save(Burger burger) {
        entityManager.persist(burger);
        return burger;
    }

    @Override
    public List<Burger> findAll() {

        TypedQuery<Burger> findAll = entityManager.createQuery("SELECT b FROM Burger b", Burger.class);

        return findAll.getResultList();
    }

    @Override
    public Burger findById(Long id) {
        Burger burger = entityManager.find(Burger.class, id);
        if (burger == null) {
            throw new BurgerException("Burger with given id can not find.", HttpStatus.NOT_FOUND);
        }

        return burger;
    }

    @Override
    public Burger update(Burger burger) {
        entityManager.merge(burger);
        return burger;
    }

    @Override
    public Burger remove(Long id) {
        Burger existingBurger = findById(id);
        entityManager.remove(existingBurger);
        return existingBurger;
    }

    @Override
    public List<Burger> findByPrice(Double price) {
        TypedQuery<Burger> findByPrice = entityManager.createQuery(
                "SELECT b FROM Burger b WHERE b.price>: price ORDER BY b.price desc",
                Burger.class);

        findByPrice.setParameter("price", price);

        return findByPrice.getResultList();
    }

    @Override
    public List<Burger> findByBreadType(BreadType breadType) {
        TypedQuery<Burger> findByBreadType = entityManager
                .createQuery("SELECT b FROM Burger b WHERE b.breadType=:breadType ORDER BY b.name desc", Burger.class);

        findByBreadType.setParameter("breadType", findByBreadType);

        return findByBreadType.getResultList();

    }

    @Override
    public List<Burger> findByContent(String content) {
        TypedQuery<Burger> findByContent = entityManager
                .createQuery("SELECT b FROM Burger b WHERE b.contents LIKE CONCAT('%',:content,'%')", Burger.class);

        findByContent.setParameter("content", content);

        return findByContent.getResultList();
    }

}
