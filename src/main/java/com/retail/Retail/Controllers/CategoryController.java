package com.retail.Retail.Controllers;

import com.retail.Retail.Repositories.CategoryRepo;
import com.retail.Retail.Services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;


@Controller
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryRepo categoryRepo;


    @GetMapping("/list-category")
    public String showCategoryList(Model model){
        categoryService.findAllCategories(model);
        return "/category-templates/list-category";
    }


    @GetMapping("/create-category")
    public String createProductCategory(){
        return "/category-templates/create-category";
    }


    @PostMapping("/create-category")
    public String saveProductCategory(@RequestParam String name, @RequestParam("image") MultipartFile file) throws IOException {
        categoryService.saveCategory(name, file);
        return "redirect:/list-category";
    }


    @GetMapping("/list-category/remove/{id}")
    public String removeCategoryFromList(@PathVariable(value = "id") long id){
       categoryService.deleteCategoryById(id);
       return "redirect:/list-category";
    }


    @GetMapping("/edit-category/{id}")
    public String editAndShowProductCategory(@PathVariable(value = "id") long id, Model model){
        if (!categoryRepo.existsById(id)) {
            return "redirect:/list-category";
        }
        categoryService.findAndShowCategoryById(id, model);
        return "/category-templates/edit-category";
    }


    @PostMapping("/edit-category/{id}")
    public String editAndSaveProductCategory(@PathVariable(value = "id") long id, @RequestParam String name, @RequestParam("image") MultipartFile file) throws IOException {
        categoryService.saveCategory(id, name, file);
        return "redirect:/list-category";
    }
}
