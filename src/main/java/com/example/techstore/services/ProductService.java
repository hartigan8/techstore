package com.example.techstore.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.techstore.entities.Product;
import com.example.techstore.repositories.ProductRepo;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    public Product saveOneProduct(Product product) {
        return productRepo.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product getOneProduct(Long id) {
        return productRepo.findById(id).orElse(null);
    }

    public void deleteOneProduct(Long id) {
        productRepo.deleteById(id);
    }

    public Product updateOneProduct(Long id, Product newProduct){
        Optional<Product> product = productRepo.findById(id);
        if(product.isPresent()){
            Product foundProduct = product.get();
            foundProduct.setCategory(newProduct.getCategory());
            foundProduct.setDescription(newProduct.getDescription());
            foundProduct.setQuantity(newProduct.getQuantity());
            foundProduct.setPrice(newProduct.getPrice());
            
            productRepo.save(foundProduct);
            return foundProduct;
        }
        else{
            return null;
        }
    }
    
}
