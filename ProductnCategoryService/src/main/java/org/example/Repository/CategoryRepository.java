package org.example.Repository;

import org.example.Model.Category;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    // jpa extended through category to have access to built in db functions

}
