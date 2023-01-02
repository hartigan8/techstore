package com.example.techstore.services;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.techstore.entities.Product;
import com.example.techstore.repositories.ProductRepo;
import com.example.techstore.requests.ProductQuantity;
import com.example.techstore.requests.ProductRequest;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    public Product saveOneProduct(ProductRequest product){
        Product productToSave = new Product();
        productToSave.setCategory(product.getCategory());
        productToSave.setDescription(product.getDescription());
        productToSave.setName(product.getName());
        
        String p = new String(product.getPhoto().substring(product.getPhoto().indexOf(",") + 1));
        byte[] photo = null;
        try {
            photo = Base64.getDecoder().decode(p.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        productToSave.setPhoto(photo);
        productToSave.setPrice(product.getPrice());
        productToSave.setQuantity(product.getQuantity());

        return productRepo.save(productToSave);
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public boolean checkQuantities(List<ProductQuantity> listToValidate) {
        for (ProductQuantity productQuantity : listToValidate) {
            Optional<Product> optProduct = productRepo.findById(productQuantity.getProductId());
            if(!optProduct.isPresent() || optProduct.get().getQuantity() < productQuantity.getProductId())
            return false;
        }
        return true;
    }

    public Product getOneProduct(Long id) {
        return productRepo.findById(id).orElse(null);
    }

    public void deleteOneProduct(Long id) {
        productRepo.deleteById(id);
    }

    public Product updateOneProduct(Product updateProduct){
        Product product = productRepo.findById(updateProduct.getId()).get();
        product.setCategory(updateProduct.getCategory());
        product.setDescription(updateProduct.getDescription());
        product.setQuantity(updateProduct.getQuantity());
        product.setPrice(updateProduct.getPrice());
        // photo update
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

    public List<Product> findAllByCategory(String catergory) {
        return productRepo.findAllByCategory();
    }
    
}
