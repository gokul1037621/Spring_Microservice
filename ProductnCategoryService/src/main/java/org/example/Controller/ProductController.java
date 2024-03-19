package org.example.Controller;

import org.example.Model.Category;
import org.example.Model.Product;
import org.example.Service.CategoryService;
import org.example.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/*
product controller object that calls the service object of the same through dependency injection
functions are performed by the service object, which receives inputs through the path mapping provided
in the controller. This is used to handle crud http requests through APIs and data is sent and received
in JSON format depending on the function parameters.
 */

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService prodServ;

    @Autowired
    CategoryService categoryService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public Product save(@RequestBody Product product){
        return prodServ.saveProduct(product);
    }

    @PostMapping("/addall")
    public List<Product> saveAll(@RequestBody List<Product> allProducts){
        return prodServ.saveAllProducts(allProducts);
    }

    @GetMapping("/findbyid/{id}")
    public void findById(@PathVariable(name = "id") int id){
        if(prodServ.existProdById(id)){
            System.out.println(prodServ.findProdById(id));
        }else{
            System.out.println("Product is not present");
        }
    }

    @PutMapping("/product-with-category/{id}")
    public Category link(@PathVariable(name = "id") int id, @RequestBody Product product){
        Category c1 = categoryService.findCategoryById(id).get();
        List<Product> p1 = c1.getProductList();
        System.out.println(p1);
        p1.add(product);
        c1.setProductList(p1);
        System.out.println(p1);
        System.out.println(c1.getProductList());
        return categoryService.updateCategory(c1);
    }

    @GetMapping("/getall")
    public List<Product> findall(){
        return prodServ.findAllProducts();
    }

    @DeleteMapping("/deletebyid/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable("id") int id){
        try {
            boolean userExists = prodServ.existProdById(id);
            if (userExists){
                prodServ.deleteProdById(id);
                System.out.println("Product id "+id+" successfully deleted");
            }else{
                System.out.println("Product does not exist");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @PostMapping("/updateproduct")
    public Product update(@RequestBody Product product){
        Product newProd = product;
        System.out.println("Id of new product is " + newProd.getProductId());
        if(prodServ.existProdById(newProd.getProductId())){
            Product updatedProd = prodServ.updateProduct(product);
            return updatedProd;
        }
        System.out.println("Product does not exists");
        return null;
    }

    @GetMapping("/getByanyfield")
    public Product getbyfield(@RequestBody Product product){
        return getbyfield(product);
    }
}
