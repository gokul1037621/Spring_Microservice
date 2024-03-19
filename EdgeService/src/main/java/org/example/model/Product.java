package org.example.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



/*
product entity defined in postgresql database through annotations, with 2 constructors being created
the entity is also mapped many to one to the category entity through annotation, with cascading enabled
which means that any change reflected here will affect the mapped entity as well.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {


    private int productId;


    private String productName;


    private String productDescription;


    private Category productCategory;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Category getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(Category productCategory) {
        this.productCategory = productCategory;
    }
}
