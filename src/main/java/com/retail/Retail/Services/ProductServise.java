package com.retail.Retail.Services;

import com.retail.Retail.Models.Category;
import com.retail.Retail.Models.Product;
import com.retail.Retail.Repositories.CategoryRepo;
import com.retail.Retail.Repositories.ProductRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ProductServise {
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Value("${upload.path.product}")
    private String uploadPathProduct;

    public void saveProduct(@PathVariable(value = "id") long id,
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

        if ((file1 != null && !file1.getOriginalFilename().isEmpty()) ||
            (file2 != null && !file2.getOriginalFilename().isEmpty()) ||
            (file3 != null && !file3.getOriginalFilename().isEmpty()) ||
            (file4 != null && !file4.getOriginalFilename().isEmpty()) ||
            (file5 != null && !file5.getOriginalFilename().isEmpty())) {
            File uploadDir = new File(uploadPathProduct);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
        }

        Category category = categoryRepo.findById(id).orElseThrow();
        List<Product> products = category.getProducts();
        Product product = new Product();
        product.setName(name);
        product.setVendorCode(vendorCode);
        product.setPrice(price);
        product.setExistence(existence);
        product.setDiscount(discount);
        product.setDetails(details);


        if (file1 != null && !file1.getOriginalFilename().isEmpty()) {
            String uuidFile1 = UUID.randomUUID().toString();
            String resultFilename1 = uuidFile1 + "." + file1.getOriginalFilename();
            file1.transferTo(new File(uploadPathProduct + "/" + resultFilename1));
            product.setImage1(resultFilename1);
        }

        if (file2 != null && !file2.getOriginalFilename().isEmpty()) {
            String uuidFile2 = UUID.randomUUID().toString();
            String resultFilename2 = uuidFile2 + "." + file2.getOriginalFilename();
            file2.transferTo(new File(uploadPathProduct + "/" + resultFilename2));
            product.setImage2(resultFilename2);
        }

        if (file3 != null && !file3.getOriginalFilename().isEmpty()) {
            String uuidFile3 = UUID.randomUUID().toString();
            String resultFilename3 = uuidFile3 + "." + file3.getOriginalFilename();
            file3.transferTo(new File(uploadPathProduct + "/" + resultFilename3));
            product.setImage3(resultFilename3);
        }

        if (file4 != null && !file4.getOriginalFilename().isEmpty()) {
            String uuidFile4 = UUID.randomUUID().toString();
            String resultFilename4 = uuidFile4 + "." + file4.getOriginalFilename();
            file4.transferTo(new File(uploadPathProduct + "/" + resultFilename4));
            product.setImage4(resultFilename4);
        }

        if (file5 != null && !file5.getOriginalFilename().isEmpty()) {
            String uuidFile5 = UUID.randomUUID().toString();
            String resultFilename5 = uuidFile5 + "." + file5.getOriginalFilename();
            file5.transferTo(new File(uploadPathProduct + "/" + resultFilename5));
            product.setImage5(resultFilename5);
        }

        products.add(product);
        category.setProducts(products);
        categoryRepo.save(category);
        log.info("Новый продукт успешно создан и сохранён в базу данных.");
    }

    //Перегрузка
    public void saveProduct(@PathVariable(value = "category_id") long categoryId,
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

        if ((file1 != null && !file1.getOriginalFilename().isEmpty()) ||
            (file2 != null && !file2.getOriginalFilename().isEmpty()) ||
            (file3 != null && !file3.getOriginalFilename().isEmpty()) ||
            (file4 != null && !file4.getOriginalFilename().isEmpty()) ||
            (file5 != null && !file5.getOriginalFilename().isEmpty())) {
            File uploadDir = new File(uploadPathProduct);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
        }

        Category category = categoryRepo.findById(categoryId).orElseThrow();
        List<Product> products = category.getProducts();
        //Product product = new Product();
        Product product = productRepo.findById(productId).orElseThrow();
        product.setName(name);
        product.setVendorCode(vendorCode);
        product.setPrice(price);
        product.setExistence(existence);
        product.setDiscount(discount);
        product.setDetails(details);

        if (file1 != null && !file1.getOriginalFilename().isEmpty()) {
            String uuidFile1 = UUID.randomUUID().toString();
            String resultFilename1 = uuidFile1 + "." + file1.getOriginalFilename();
            file1.transferTo(new File(uploadPathProduct + "/" + resultFilename1));
            product.setImage1(resultFilename1);
        }

        if (file2 != null && !file2.getOriginalFilename().isEmpty()) {
            String uuidFile2 = UUID.randomUUID().toString();
            String resultFilename2 = uuidFile2 + "." + file2.getOriginalFilename();
            file2.transferTo(new File(uploadPathProduct + "/" + resultFilename2));
            product.setImage2(resultFilename2);
        }

        if (file3 != null && !file3.getOriginalFilename().isEmpty()) {
            String uuidFile3 = UUID.randomUUID().toString();
            String resultFilename3 = uuidFile3 + "." + file3.getOriginalFilename();
            file3.transferTo(new File(uploadPathProduct + "/" + resultFilename3));
            product.setImage3(resultFilename3);
        }

        if (file4 != null && !file4.getOriginalFilename().isEmpty()) {
            String uuidFile4 = UUID.randomUUID().toString();
            String resultFilename4 = uuidFile4 + "." + file4.getOriginalFilename();
            file4.transferTo(new File(uploadPathProduct + "/" + resultFilename4));
            product.setImage4(resultFilename4);
        }

        if (file5 != null && !file5.getOriginalFilename().isEmpty()) {
            String uuidFile5 = UUID.randomUUID().toString();
            String resultFilename5 = uuidFile5 + "." + file5.getOriginalFilename();
            file5.transferTo(new File(uploadPathProduct + "/" + resultFilename5));
            product.setImage5(resultFilename5);
        }

        productRepo.save(product);
        products.add(product);
        log.info("Продукт успешно отредактирован и сохранён в базу данных.");
    }

}
