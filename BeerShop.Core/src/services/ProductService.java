package services;

import customexceptions.DatabaseNotFoundException;
import enumerations.BeerCategory;
import models.Product;
import models.ProductViewModel;
import models.ProductsViewModel;
import repositories.MongoUnitOfWork;
import repositories.UnitOfWork;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by mario on 27.5.2017..
 */
public class ProductService {

    UnitOfWork unitOfWork;

    public ProductService() throws FileNotFoundException, DatabaseNotFoundException {
        unitOfWork = new MongoUnitOfWork();
        int a = 10;
    }

    public ProductsViewModel getProducts() throws FileNotFoundException, DatabaseNotFoundException {

        ProductsViewModel model = new ProductsViewModel();

        model.setLightBeer((ArrayList<ProductViewModel>) getProductByCategory(BeerCategory.Svijetlo));
        model.setDarkBeer((ArrayList<ProductViewModel>) getProductByCategory(BeerCategory.Tamno));
        model.setWheatBeer((ArrayList<ProductViewModel>) getProductByCategory(BeerCategory.Psenicno));
        model.setOtherBeer((ArrayList<ProductViewModel>) getProductByCategory(BeerCategory.Ostalo));
        model.setSpecials((ArrayList<ProductViewModel>) getSpecials());

        return model;
    }

    public ProductViewModel getProductById(String id) throws FileNotFoundException, DatabaseNotFoundException, NoSuchElementException {

        List<Product> products = unitOfWork.getProductRepository().getAll();
        ProductViewModel model = null;

        try {
            Product product = products.stream().filter((p) -> p.getId().equals(id)).findFirst().get();

            model = new ProductViewModel();
            model.setProductId(product.getId());
            model.setName(product.getName());
            model.setImgUrl(product.getImgUrl());
            model.setCategory(product.getCategory());
            model.setPrice(product.getPrice());

        } catch (NoSuchElementException e) {
            throw e;
        }

        return model;
    }

    private List<ProductViewModel> getProductByCategory(BeerCategory category) throws FileNotFoundException, DatabaseNotFoundException {

        List<ProductViewModel> model = new ArrayList<>();

        List<Product> products = unitOfWork.getProductRepository().getAll();

        List<Product> filteredProducts = products.stream().filter(
                (p) -> p.getCategory().equals(category.toString()))
                .collect(Collectors.toList());

        filteredProducts.forEach((p) -> model.add(new ProductViewModel(p)));

        return model;
    };

    private List<ProductViewModel> getSpecials() throws FileNotFoundException, DatabaseNotFoundException {

        List<ProductViewModel> model = new ArrayList<>();

        List<Product> products = unitOfWork.getProductRepository().getAll();

        int n = products.size();

       if (n >= 8) {
           n = 8;
       }

        Collections.shuffle(products);

        for (int i = 0; i < n; i++) {
            model.add(new ProductViewModel(products.get(i)));
        }

        return model;
    }

}
