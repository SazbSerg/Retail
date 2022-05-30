package com.retail.Retail.Controllers;

import com.retail.Retail.Models.Category;
import com.retail.Retail.Repositories.CategoryRepo;
import com.retail.Retail.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    private CategoryRepo categoryRepo;

    @GetMapping("/list-category")
    public String showCategoryList(Model model){
        //categoryService.findAllCategories(model);
        Iterable<Category> categories = categoryRepo.findAll();
        model.addAttribute("category", categories);
        return "/category-templates/list-category";
    }

    @GetMapping("/create-category")
    public String createProductCategory(){
        return "/category-templates/create-category";
    }

    //("hasAuthority('ROLE_USER')")
    @PostMapping("/create-category")
    public String saveProductCategory(@RequestParam String name, @RequestParam("image") MultipartFile file) throws IOException {
        categoryService.saveCategory(name, file);
        return "redirect:/list-category";
    }

    @GetMapping("/list-category/remove/{id}")
    public String removeCategoryFromList(@PathVariable(value = "id") long id){
        Category category = categoryRepo.findById(id).orElseThrow();
        categoryRepo.delete(category);
        return "redirect:/list-category";
    }

    @GetMapping("/edit-category/{id}")
    public String editAndShowProductCategory(@PathVariable(value = "id") long id, Model model){
        if (!categoryRepo.existsById(id)) {
            return "redirect:/list-category";
        }
        Optional<Category> categories = categoryRepo.findById(id);
        ArrayList<Category> res = new ArrayList<>();
        categories.ifPresent(res::add);
        model.addAttribute("category", res);
        return "/category-templates/edit-category";
    }

    @PostMapping("/edit-category/{id}")
    public String editAndSaveProductCategory(@PathVariable(value = "id") long id, @RequestParam String name, @RequestParam("image") MultipartFile file) throws IOException {
        categoryService.saveCategory(id, name, file);
        return "redirect:/list-category";
    }
}
