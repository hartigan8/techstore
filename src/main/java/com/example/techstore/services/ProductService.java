package com.example.techstore.services;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
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
        String pType = product.getPhoto().substring(0, product.getPhoto().indexOf(","));
        String p = new String(product.getPhoto().substring(product.getPhoto().indexOf(",") + 1));
        byte[] photo = null;
        try {
            photo = Base64.getDecoder().decode(p.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        productToSave.setPhotoType(pType);
        productToSave.setPhoto(photo);
        productToSave.setPrice(product.getPrice());
        productToSave.setQuantity(product.getQuantity());

        return productRepo.save(productToSave);
    }
    @Transactional
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepo.findAll();
        List<ProductResponse> response = new LinkedList<>();
        for (Product product : products) {
            String b64 = product.getPhotoType() + "," + Base64.getEncoder().encodeToString(product.getPhoto());
            ProductResponse pR = new ProductResponse();
            pR.setId(product.getId());
            pR.setCategory(product.getCategory());
            pR.setDescription(product.getDescription());
            pR.setName(product.getName());
            pR.setPhoto(b64);
            pR.setPrice(product.getPrice());
            pR.setQuantity(product.getQuantity());
            response.add(pR);
        }

        return response;
    }

    public boolean checkQuantities(List<ProductQuantity> listToValidate) {
        for (ProductQuantity productQuantity : listToValidate) {
            Optional<Product> optProduct = productRepo.findById(productQuantity.getProductId());
            if(!optProduct.isPresent() || optProduct.get().getQuantity() < productQuantity.getProductId())
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
            String b64 = product.getPhotoType() + "," + Base64.getEncoder().encodeToString(product.getPhoto());
            pR.setId(product.getId());
            pR.setCategory(product.getCategory());
            pR.setDescription(product.getDescription());
            pR.setName(product.getName());
            pR.setPhoto(b64);
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
        // photo update
        String pType = updateProduct.getPhoto().substring(0, updateProduct.getPhoto().indexOf(","));
        String p = new String(updateProduct.getPhoto().substring(updateProduct.getPhoto().indexOf(",") + 1));
        byte[] photo = null;
        try {
            photo = Base64.getDecoder().decode(p.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        product.setPhoto(photo);
        product.setPhotoType(pType);
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
            String b64 = product.getPhotoType() + "," + Base64.getEncoder().encodeToString(product.getPhoto());
            ProductResponse pR = new ProductResponse();
            pR.setId(product.getId());
            pR.setCategory(product.getCategory());
            pR.setDescription(product.getDescription());
            pR.setName(product.getName());
            pR.setPhoto(b64);
            pR.setPrice(product.getPrice());
            pR.setQuantity(product.getQuantity());
            response.add(pR);
        }

        return response;
    }
    
}
