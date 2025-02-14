package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/create")
    public String createProductPage(Model model) {
        model.addAttribute("product", new Product());
        return "createProduct";
    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute Product product) {
        service.create(product);
        return "redirect:/product/list";
    }

    @GetMapping("/list")
    public String productListPage(Model model) {
        model.addAttribute("products", service.findAll());
        return "productList";
    }

    @GetMapping("/edit/{id}")
    public String editProductPage(Model model, @PathVariable("id") String productId){
        String productID = productId;
        Product product = service.findProductByID(productID);
        model.addAttribute("product", product);
        return "editProduct";
    }

    @PostMapping("/edit/{id}")
    public String editProductPost(@ModelAttribute Product product, Model model,
                                  @PathVariable("id") String productId){
        product.setProductId(productId);
        service.edit(product);
        return "redirect:/product/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteProductPage(Model model, @PathVariable("id") String productId){
        service.deleteProductByID(productId);
        return "redirect:/product/list";
    }
}
