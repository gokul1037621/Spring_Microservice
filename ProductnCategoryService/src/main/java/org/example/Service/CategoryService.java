package org.example.Service;


import org.example.Model.Category;
import org.example.Model.Product;
import org.example.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    /*
    category service class to define the functions that will be invoked by the controller during
    run time. controller calls the service class as and when required. dependency injection is done
    using autowired to use the repository object within the application context.
    th object uses pre built functions within JPA to perform db related tasks such as save, delete and
    search
     */
    @Autowired
    CategoryRepository categoryRepositoryObject;

    public Category saveCategory(Category c){
        return categoryRepositoryObject.save(c);
    }

    public List<Category> saveAllCategories(List<Category> c){
        return categoryRepositoryObject.saveAll(c);
    }

    public Category updateCategory(Category c){
        return categoryRepositoryObject.saveAndFlush(c);
    }

    public Optional<Category> findCategoryById(int id){
        return categoryRepositoryObject.findById(id);
    }

    public void deleteCategoryById(int id){
        categoryRepositoryObject.deleteById(id);
    }

    public boolean existCategoryById(int id){
        return categoryRepositoryObject.existsById(id);
    }

    public List<Category> findAllCategories(){
        return categoryRepositoryObject.findAll();
    }

    // functions to get by any field


    public boolean returnfield(String product){
        List<Category> p1 = categoryRepositoryObject.findAll();
        for(Category p : p1){
            if(p.getCategoryName().equals(product)){
                return true;
            }
            else if(p.getCategoryDescription().equals(product)){
                return true;
            }
        }
        return false;
    }

    public Optional<Category> getByField(Category product){
        if(product.getCategoryDescription()!=null){
            if(returnfield(product.getCategoryDescription())){
                return categoryRepositoryObject.findById(product.getCategoryId());
            }
            else if(returnfield(product.getCategoryName())){
                return categoryRepositoryObject.findById(product.getCategoryId());
            }
        }
        return null;
    }


}
