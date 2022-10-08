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
import java.util.Optional;

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
    
    public ProductModel updateProduct(ProductModel product) {
       ProductModel productFromDb = repository.getById(product.getId());
        
        productFromDb.setCategoria(product.getCategoria());
        productFromDb.setDescricao(product.getDescricao());
        productFromDb.setName(product.getName());
        productFromDb.setPreco(product.getPreco());
        productFromDb.setValidade(product.getValidade());
        
        return repository.save(productFromDb);
    }
}

