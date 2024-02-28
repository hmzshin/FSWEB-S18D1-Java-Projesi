package com.workintech.s18d1.dao;

import java.util.List;

import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;

public interface BurgerDao {
    Burger save(Burger burger);

    List<Burger> findAll();

    Burger findById(Long id);

    Burger update(Burger burger, Long id);

    Burger remove(Long id);

    List<Burger> findByPrice(Double price);

    List<Burger> findByBreadType(BreadType breadType);

    List<Burger> findByContent(String content);

}
