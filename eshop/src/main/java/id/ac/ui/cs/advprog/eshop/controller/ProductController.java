package id.ac.ui.cs.advprog.eshop.controller;
import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.CarModificationService;
import id.ac.ui.cs.advprog.eshop.service.CarQueryService;
import id.ac.ui.cs.advprog.eshop.service.CarQueryServiceImpl;
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
    @Autowired
    private CarModificationService carModificationService;
    @Autowired
    private CarQueryService carQueryService;

    @GetMapping("/create")
    public String createProductPage(Model model) {
        model.addAttribute("product", new Product());
        return "CreateProduct";
    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute Product product) {
        service.create(product);
        return "redirect:/product/list";
    }

    @GetMapping("/list")
    public String productListPage(Model model) {
        model.addAttribute("products", service.findAll());
        return "ProductList";
    }

    @GetMapping("/edit/{id}")
    public String editProductPage(Model model, @PathVariable String id) {
        Product product = service.findById(id);
        if (product == null) {
            return "redirect:/product/list";
        }
        model.addAttribute("product", product);
        return "EditProduct";
    }

    @PostMapping("/edit/{id}")
    public String editProductPost(@PathVariable String id, @ModelAttribute Product product) {
        product.setProductId(id);
        service.edit(product);
        return "redirect:/product/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable String id) {
        service.deleteProductById(id);
        return "redirect:/product/list";
    }

    @Controller
    @RequestMapping("/car")
    class CarController extends ProductController {
        @Autowired
        private CarQueryServiceImpl carService;

        @GetMapping("/createCar")
        public String createCarPage(Model model) {
            Car car = new Car();
            model.addAttribute("car", car);
            return "createCar";
        }

        @PostMapping("/createCar")
        public String createCarPost(@ModelAttribute Car car, Model model) {
            carModificationService.create(car);
            return "redirect:/listCar";
        }

        @GetMapping("/listCar")
        public String carListPage(Model model) {
            List<Car> allCars = carQueryService.findAll();
            model.addAttribute("cars", allCars);
            return "carList";
        }

        @GetMapping("/editCar/{carId}")
        public String editCarPage(Model model, @PathVariable String carId) {
            Car car = carQueryService.findById(carId);
            model.addAttribute("car", car);
            return "editCar";
        }

        @PostMapping("/editCar")
        public String editCarPost(@ModelAttribute Car car, Model model) {
            System.out.println(car.getCarId());
            carModificationService.update(car.getCarId(), car);

            return "redirect:/listCar";
        }

        @PostMapping("/deleteCar")
        public String deleteCar(@RequestParam("carId") String carId) {
            carModificationService.deleteCarById(carId);
            return "redirect:/listCar";
        }
    }
}
