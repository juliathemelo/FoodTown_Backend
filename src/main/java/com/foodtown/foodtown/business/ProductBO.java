package com.foodtown.foodtown.business;

import com.foodtown.foodtown.model.ProductModel;
import com.foodtown.foodtown.model.UsuarioModel;
import com.foodtown.foodtown.repository.ProductRepository;
import com.foodtown.foodtown.repository.UsuarioRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Lazy
@Service("ProductBusiness")
public class ProductBO {

    @Autowired
    private ProductRepository repository;

    public List<ProductModel> getProducts() {
        List<ProductModel> products = new ArrayList<>();
        repository.findAll().forEach(products::add);
        return products;
    }

    public ProductModel saveProduct(ProductModel product) {
        return repository.save(product);
    }

}
