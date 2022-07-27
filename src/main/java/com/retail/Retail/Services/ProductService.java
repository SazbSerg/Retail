package com.retail.Retail.Services;

import com.retail.Retail.Models.Category;
import com.retail.Retail.Models.Product;
import com.retail.Retail.Repositories.CategoryRepo;
import com.retail.Retail.Repositories.ProductRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;

    public List<Product> getProducts() {
        List<Product> products = productRepo.findAll();
        return products;
    }


    public List<Product> getProductsByCategory(Long id) {
            Optional<Category> categories = Optional.of(categoryRepo.findById(id).orElseThrow());
            List<Product> productList = productRepo.getProductsByCategoryId(id);
            return productList;

    }


    public Product getProduct(Long id_category, Long id_product) {
        Category category = categoryRepo.findById(id_category).orElseThrow();
        String categoryName = category.getName();
        Product product = productRepo.findById(id_product).orElseThrow();
        log.warn(" Product with id {} fond in Category " + categoryName + "", id_product);
        return product;
    }


    public List<Product> getSortedProducts() {
        return productRepo.findAllAndOrderByName();
    }
}
