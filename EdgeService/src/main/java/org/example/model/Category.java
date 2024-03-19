package org.example.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/*
category entity defined through annotations to be an entity in the postgresql database along with 2
constructors. also mapped one to many to the product entity using a list as a data member.
cascading is also given, which means that any change made here will reflect in the mapped entity as well
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {


    private int categoryId;


    private String categoryName;


    private String categoryDescription;


    private List<Product> productList;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
