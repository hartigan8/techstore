package com.example.techstore.controllers;

import com.example.techstore.entities.Product;
import com.example.techstore.requests.ProductRequest;
import com.example.techstore.responses.ProductResponse;
import com.example.techstore.services.ProductService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @PostMapping
    @PreAuthorize("hasAuthority('admin') or hasAuthority('employee')")
    public Product saveOneProduct(@RequestBody ProductRequest product) {
        
        return productService.saveOneProduct(product);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('employee') or hasAuthority('admin')")
    public void deleteOneProduct(@PathVariable Long id) {
        productService.deleteOneProduct(id);
    }
    @GetMapping(value = {"/{id}"})
    public ProductResponse getOneProduct(@PathVariable(name = "id") Long id) {
        ProductResponse product = productService.getOneProduct(id);
         return product;  
    }
    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }
    @PutMapping
    @PreAuthorize("hasAuthority('employee') or hasAuthority('admin')")
    public Product updateOneProduct(@RequestBody ProductResponse product){
        return productService.updateOneProduct(product);
    }

    @GetMapping("category/{category}")
    public List<ProductResponse> findAllByCategory(@PathVariable String category) {
        return productService.findAllByCategory(category);
    }
    
    
}
