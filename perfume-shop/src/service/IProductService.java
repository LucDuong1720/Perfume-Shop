package service;

import model.Product;
import utils.TypeSort;

import java.util.List;

public interface IProductService {
    List<Product> getAllProdutcs();
    void addProduct (Product product);

    Product findById(long id);

    boolean existsByName(String productName);

    boolean existById(long id);

    void update(Product newProduct);

    void deleteById(long id);

    List<Product> findByName(String productName);


    List<Product> findByOrigin(String productBrand);

    List<Product> sortById(TypeSort typeSort);

    List<Product> sortByName(TypeSort typeSort);

    List<Product> sortByBrand(TypeSort typeSort);

    List<Product> sortByQuantity(TypeSort typeSort);

    List<Product> sortByPrice(TypeSort typeSort);

    Product findProductById(long productId);
}
