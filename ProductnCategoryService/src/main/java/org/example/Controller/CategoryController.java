package org.example.Controller;

import org.example.Model.Category;
import org.example.Model.Product;
import org.example.Service.CategoryService;
import org.example.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
category controller class that calls the service object through dependency injection
to invoke the db functions defined by the service class to perform http crud requests and also handlle
the entity to entity mapping between categeory and product. path mappings are used to handle requets
along with request parameters and variables within the path of the request itself
 */

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public Category save(@RequestBody Category category){
        return categoryService.saveCategory(category);
    }

    @PostMapping("/addall")
    public List<Category> saveAll(@RequestBody List<Category> allCategories){
        return categoryService.saveAllCategories(allCategories);
    }

    @GetMapping("/findbyid/{id}")
    public void findById(@PathVariable(name = "id") int id){
        if(categoryService.existCategoryById(id)){
            System.out.println(categoryService.findCategoryById(id));
        }else{
            System.out.println("Product is not present");
        }
    }
    @GetMapping("/getall")
    public List<Category> findall(){
        return categoryService.findAllCategories();
    }

    @DeleteMapping("/deletebyid/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable("id") int id){
        try {
            boolean categoryExists = categoryService.existCategoryById(id);
            if (categoryExists){
                List<Product> p1 = categoryService.findCategoryById(id).get().getProductList();
                for(Product p : p1){
                    productService.deleteProdById(p.getProductId());
                }
                categoryService.deleteCategoryById(id);
                System.out.println("Category id "+id+" successfully deleted");
            }else{
                System.out.println("Category does not exist");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @PostMapping("/updateCategory")
    public Category update(@RequestBody Category category){
        Category newCategory = category;
        System.out.println("Id of new product is " + newCategory.getCategoryId());
        if(categoryService.existCategoryById(newCategory.getCategoryId())){
            Category updatedCategory = categoryService.updateCategory(category);
            return updatedCategory;
        }
        System.out.println("Product does not exists");
        return null;
    }

    @GetMapping("/getByanyfield")
    public Product getbyfield(@RequestBody Category product){
        return getbyfield(product);
    }
}
