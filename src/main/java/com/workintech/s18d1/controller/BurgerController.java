package com.workintech.s18d1.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workintech.s18d1.dao.BurgerDao;
import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.util.BurgerValidation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/burgers")
public class BurgerController {

    private final BurgerDao burgerDao;

    public BurgerController(BurgerDao burgerDao) {
        this.burgerDao = burgerDao;
    }

    @PostMapping
    public Burger save(@RequestBody Burger burger) {
        BurgerValidation.checkName(burger.getName());
        burgerDao.save(burger);
        return burger;
    }

    @GetMapping
    public List<Burger> findAll() {
        return burgerDao.findAll();
    }

    @GetMapping(path = "/{id}")
    public Burger findBtId(@PathVariable("id") Long id) {
        return burgerDao.findById(id);
    }

    @PutMapping("/{id}")
    public Burger update(@PathVariable("id") Burger id, @RequestBody Burger burger) {
        return burgerDao.update(burger);
    }

    @DeleteMapping("/{id}")
    public Burger delete(@PathVariable("id") Long id) {
        return burgerDao.remove(id);
    }

    @GetMapping("/breadType/{breadType}")
    public List<Burger> getMethodName(@PathVariable("breadType") String breadType) {
        BreadType bType = BreadType.valueOf(breadType);
        return burgerDao.findByBreadType(bType);
    }

    @GetMapping("/price/{price}")
    public List<Burger> findByPrice(@PathVariable("price") Double price) {
        return burgerDao.findByPrice(price);
    }

    @GetMapping("/content/{content}")
    public List<Burger> findByContent(@PathVariable("content") String content) {
        return burgerDao.findByContent(content);
    }

}
