package com.example.techstore.services;


import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.techstore.entities.Product;
import com.example.techstore.repositories.ProductRepo;
import com.example.techstore.requests.ProductQuantity;
import com.example.techstore.requests.ProductRequest;
import com.example.techstore.responses.ProductResponse;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    public Product saveOneProduct(ProductRequest product){
        Product productToSave = new Product();
        productToSave.setCategory(product.getCategory());
        productToSave.setDescription(product.getDescription());
        productToSave.setName(product.getName());
        productToSave.setPrice(product.getPrice());
        productToSave.setQuantity(product.getQuantity());
        productToSave.setPhoto(product.getPhoto());
        return productRepo.save(productToSave);
    }
    @Transactional
    public List<Product> getAllProducts() {
        List<Product> products = productRepo.findAll();


        return products;
    }

    public boolean checkQuantities(List<ProductQuantity> listToValidate) {
        for (ProductQuantity productQuantity : listToValidate) {
            Optional<Product> optProduct = productRepo.findById(productQuantity.getProductId());
            if(!optProduct.isPresent() || optProduct.get().getQuantity() < productQuantity.getQuantity())
            return false;
        }
        return true;
    }

    @Transactional
    public ProductResponse getOneProduct(Long id) {
        Optional<Product> oP = productRepo.findById(id);
        if(oP.isPresent()){
            Product product = oP.get();
            ProductResponse pR = new ProductResponse();
            pR.setId(product.getId());
            pR.setCategory(product.getCategory());
            pR.setDescription(product.getDescription());
            pR.setName(product.getName());
            pR.setPrice(product.getPrice());
            pR.setQuantity(product.getQuantity());
            return pR;
        }
        else{
            return null;
        }

    }

    public void deleteOneProduct(Long id) {
        productRepo.deleteById(id);
    }

    public Product updateOneProduct(ProductResponse updateProduct){
        Product product = productRepo.findById(updateProduct.getId()).get();
        product.setCategory(updateProduct.getCategory());
        product.setDescription(updateProduct.getDescription());
        product.setQuantity(updateProduct.getQuantity());
        product.setPrice(updateProduct.getPrice());
        product.setPhoto(updateProduct.getPhoto());
        productRepo.save(product);
        return product;
    }

    public void updateQuantities(List<ProductQuantity> orderList) {
        for (ProductQuantity productQuantity : orderList) {
            Product product = productRepo.findById(productQuantity.getProductId()).get();
            product.setQuantity(product.getQuantity() - productQuantity.getQuantity());
            productRepo.save(product);
        }
    }
    @Transactional
    public List<ProductResponse> findAllByCategory(String category) {
        List<Product> products = productRepo.findAllByCategory(category);
        List<ProductResponse> response = new LinkedList<>();
        for (Product product : products) {
            ProductResponse pR = new ProductResponse();
            pR.setId(product.getId());
            pR.setCategory(product.getCategory());
            pR.setDescription(product.getDescription());
            pR.setName(product.getName());
            pR.setPhoto(product.getPhoto());
            pR.setPrice(product.getPrice());
            pR.setQuantity(product.getQuantity());
            response.add(pR);
        }

        return response;
    }
    
}
