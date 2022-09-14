package com.foodtown.foodtown.api;

import com.foodtown.foodtown.business.ProductBO;
import com.foodtown.foodtown.business.UsuarioBO;
import com.foodtown.foodtown.model.ProductModel;
import com.foodtown.foodtown.model.UsuarioModel;
import com.foodtown.foodtown.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductRest {

    @Autowired
    private ProductRepository repository;
    @Autowired
    private final ProductBO productBO;

    public ProductRest(ProductBO productBO) {
        this.productBO = productBO;
    }

    @GetMapping("/all")
    public List<ProductModel> getUser() {
        return productBO.getProducts();
    }

    @PostMapping(path = "/salvar")
    public ProductModel salvar(@RequestBody ProductModel product) {
        return productBO.saveProduct(product);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void delete(@PathVariable Integer id) {
        repository.deleteById(id);
    }


}
