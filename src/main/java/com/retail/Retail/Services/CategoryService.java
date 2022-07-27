package com.retail.Retail.Services;

import com.retail.Retail.Models.Category;
import com.retail.Retail.Repositories.CategoryRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {

    private final CategoryRepo categoryRepo;

    @Value("${upload.path.category}")
    private String uploadPathCategory;


    public void findAllCategories(Model model) {
        Iterable<Category> categories = categoryRepo.findAll();
        model.addAttribute("category", categories);
    }


    public void saveCategory(@RequestParam String name, @RequestParam("image") MultipartFile file) throws IOException {
        if ((file != null && !file.getOriginalFilename().isEmpty())) {
            File uploadDir = new File(uploadPathCategory);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
        }
        Category category = new Category();
        category.setName(name);
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPathCategory + "/" + resultFilename));
            category.setImage(resultFilename);
        }
        categoryRepo.save(category);
        log.info("Новый категория продуктов успешно сохранёна в базу данных.");
    }


    public void saveCategory(Long id, @RequestParam String name, @RequestParam("image") MultipartFile file) throws IOException {
        if ((file != null && !file.getOriginalFilename().isEmpty())) {
            File uploadDir = new File(uploadPathCategory);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
        }
        Category category = categoryRepo.findById(id).orElseThrow();
        category.setName(name);
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPathCategory + "/" + resultFilename));
            category.setImage(resultFilename);
        }
        categoryRepo.save(category);
        log.info("Категория продуктов успешно отредактирована и сохранёна в базу данных.");
    }


    public void deleteCategoryById(long id) {
        Category category = categoryRepo.findById(id).orElseThrow();
        categoryRepo.delete(category);
    }


    public void findAndShowCategoryById(long id, Model model) {
        Optional<Category> categories = categoryRepo.findById(id);
        ArrayList<Category> res = new ArrayList<>();
        categories.ifPresent(res::add);
        model.addAttribute("category", res);
    }
}
