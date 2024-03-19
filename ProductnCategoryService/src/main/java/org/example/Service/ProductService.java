package org.example.Service;

import org.example.Model.Product;
import org.example.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    /*
    product service object which will be invoked by controller during run time. this uses autowired
    to inject the repo object as a dependency within the application context so that built in JPA
    functions such as save and delete are available directly for use by the controller.
     */
    @Autowired
    ProductRepository prodRepo;

    public Product saveProduct(Product product){
        return prodRepo.save(product);
    }

    public List<Product> saveAllProducts(List<Product> allProducts){
        return prodRepo.saveAll(allProducts);
    }

    public Product findProdById(int id){
        Optional<Product> findProdObj = prodRepo.findById(id);
        return findProdObj.get();
    }

    public List<Product> findAllProducts(){
        return prodRepo.findAll();
    }

    public boolean existProdById(int id){
        System.out.println(prodRepo.existsById(id));
        return prodRepo.existsById(id);
    }

    public void deleteProdById(int id){
        prodRepo.deleteById(id);
    }

    public Product updateProduct(Product product){
        Product prodObj = prodRepo.saveAndFlush(product);
        return prodObj;
    }

    public boolean returnfield(String product){
        List<Product> p1 = prodRepo.findAll();
        for(Product p : p1){
            if(p.getProductName().equals(product)){
                return true;
            }
            else if(p.getProductDescription().equals(product)){
                return true;
            }
        }
        return false;
    }

    public Optional<Product> getByField(Product product){
        if(product.getProductCategory()!=null){
            if(returnfield(product.getProductName())){
                return prodRepo.findById(product.getProductId());
            }
            else if(returnfield(product.getProductDescription())){
                return prodRepo.findById(product.getProductId());
            }
        }
        return null;
    }

}
