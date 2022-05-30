package com.retail.Retail.Controllers;

import com.retail.Retail.Models.Category;
import com.retail.Retail.Models.Product;
import com.retail.Retail.Repositories.CategoryRepo;
import com.retail.Retail.Repositories.ProductRepo;
import com.retail.Retail.Services.ProductServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ProductServise productServise;


    //Отображения списка продуктов категории
    @GetMapping("/product-list/{id}")
    public String showProductList(@PathVariable(value = "id") long id, Model model){
        if (!categoryRepo.existsById(id)) {
            return "redirect:/list-category";
        }
        Optional<Category> categories = categoryRepo.findById(id);
        ArrayList<Category> res = new ArrayList<>();
        categories.ifPresent(res::add);
        model.addAttribute("category", res);
        //Отображаем все продукты текущей категории
        Iterable<Product> products = productRepo.findAll();
        model.addAttribute("product", products);
        return "/product-templates/product-list";
    }


    //Отображения списка продуктов категории в виде таблицы
    @GetMapping("/product-list-table/{id}")
    public String showProductListTable(@PathVariable(value = "id") long id, Model model){
        if (!categoryRepo.existsById(id)) {
            return "redirect:/product-list";
        }
        Optional<Category> categories = categoryRepo.findById(id);
        ArrayList<Category> res = new ArrayList<>();
        categories.ifPresent(res::add);
        model.addAttribute("category", res);
        //Отображаем все продукты текущей категории
        Iterable<Product> products = productRepo.findAll();
        model.addAttribute("product", products);
        return "/product-templates/product-list-table";
    }


    //Создание нового продукта
    @GetMapping("/create-product/{id}")
    public String createNewProduct(@PathVariable(value = "id") long id){
        return "/product-templates/create-product";
    }


    //Сохранение нового продукта
    @PostMapping("/create-product/{id}")
    public String saveNewProduct(@PathVariable(value = "id") long id,
                                 @RequestParam String name,
                                 @RequestParam Long vendorCode,
                                 @RequestParam Double price,
                                 @RequestParam Double existence,
                                 @RequestParam Double discount,
                                 @RequestParam String details,
                                 @RequestParam("image1") MultipartFile file1,
                                 @RequestParam("image2") MultipartFile file2,
                                 @RequestParam("image3") MultipartFile file3,
                                 @RequestParam("image4") MultipartFile file4,
                                 @RequestParam("image5") MultipartFile file5) throws IOException {
        productServise.saveProduct(id, name, vendorCode, price, existence, discount, details, file1, file2, file3, file4, file5);
        return "redirect:/product-list/{id}";
    }


    //Редактирование продукта
    @GetMapping("/edit-product/{category_id}/{product_id}")
    public String editProduct(@PathVariable(value = "category_id") long categoryId, @PathVariable(value = "product_id") long productId, Model model) {
        if (!categoryRepo.existsById(categoryId)) {
            return "redirect:/product-list/{category_id}";
        }
        Product product = productRepo.findById(productId).orElseThrow();
        model.addAttribute("productS", product);
        return "/product-templates/edit-product";
    }


    //Сохранение отредактированного продукта
    @PostMapping("/edit-product/{category_id}/{product_id}")
    public String saveEditedProduct(@PathVariable(value = "category_id") long categoryId,
                                    @PathVariable(value = "product_id") long productId,
                                    @RequestParam String name,
                                    @RequestParam Long vendorCode,
                                    @RequestParam Double price,
                                    @RequestParam Double existence,
                                    @RequestParam Double discount,
                                    @RequestParam String details,
                                    @RequestParam("image1") MultipartFile file1,
                                    @RequestParam("image2") MultipartFile file2,
                                    @RequestParam("image3") MultipartFile file3,
                                    @RequestParam("image4") MultipartFile file4,
                                    @RequestParam("image5") MultipartFile file5) throws IOException {
        productServise.saveProduct(categoryId, productId, name, vendorCode, price, existence, discount, details, file1, file2, file3, file4, file5);
        return "redirect:/product-list/{category_id}";
    }


    //Удаление выбранного продукта
    @GetMapping("/product-list/remove/{category_id}/{product_id}")
    public String removeProduct(@PathVariable(value = "category_id") long categoryId,
                                @PathVariable(value = "product_id") long productId) {
        if (!productRepo.existsById(productId)) {
            return "redirect:/product-list/{category_id}";}
        Category category = categoryRepo.findById(categoryId).orElseThrow();
        Product product = productRepo.findById(productId).orElseThrow();
        productRepo.delete(product);
        categoryRepo.save(category);
        return "redirect:/product-list/{category_id}";
    }

    //Удаление выбранного продукта из таблицы
    @GetMapping("/product-list-table/remove/{category_id}/{product_id}")
    public String removeProductFromTable(@PathVariable(value = "category_id") long categoryId,
                                @PathVariable(value = "product_id") long productId) {
        if (!productRepo.existsById(productId)) {
            return "redirect:/product-list-table/{category_id}";}
        Category category = categoryRepo.findById(categoryId).orElseThrow();
        Product product = productRepo.findById(productId).orElseThrow();
        productRepo.delete(product);
        categoryRepo.save(category);
        return "redirect:/product-list-table/{category_id}";
    }
}
