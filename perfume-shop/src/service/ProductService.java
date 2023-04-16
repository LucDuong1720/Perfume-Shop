package service;

import model.Product;
import utils.DateUtils;
import utils.FileUtils;
import utils.TypeSort;

import java.time.Instant;
import java.util.*;

public class ProductService implements IProductService {
    public final String pathProduct = "D:/lucduong1720/Perfume-Shop/perfume-shop/data/products.csv";
    private static ProductService instance;
    public static ProductService getInstance() {
        if (instance == null)
            instance = new ProductService();
        return instance;
    }

    @Override
    public List<Product> getAllProdutcs() {
        return FileUtils.readData(pathProduct, Product.class);
    }

    @Override
    public void addProduct(Product product) {
        List<Product> products = getAllProdutcs();
        products.add(product);
        FileUtils.writeDataToFile(pathProduct, products);
    }

    @Override
    public Product findById(long id) {
        List<Product> products = getAllProdutcs();
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
    @Override
    public boolean existsByName(String productName) {
        List<Product> products = getAllProdutcs();
        for (Product product : products) {
            if (product.getName().equals(productName))
                return true;
        }
        return false;
    }

    @Override
    public boolean existById(long id) {
        return findById(id) != null;
    }

    @Override
    public void update(Product newProduct) {
        List<Product> products = getAllProdutcs();
        for (Product product : products) {
            if (product.getId() == newProduct.getId()) {
                product.setName(newProduct.getName());
                product.setBrand(newProduct.getBrand());
                product.setOrigin(newProduct.getOrigin());
                product.setCapacity(newProduct.getCapacity());
                product.setQuantity(newProduct.getQuantity());
                product.setPrice(newProduct.getPrice());
                FileUtils.writeDataToFile(pathProduct, products);
                break;
            }
        }
    }

    @Override
    public void deleteById(long id) {
        List<Product> products = getAllProdutcs();
        for (int i = 0; i < products.size(); i++) {
            if ((products.get(i)).getId() == id) {
                products.remove(products.get(i));
            }
        }
        FileUtils.writeDataToFile(pathProduct, products);
    }

    @Override
    public List<Product> findByName(String productName) {
        List<Product> products = getAllProdutcs();
        List<Product> productsFind = new ArrayList<>();
        for (Product item : products) {
            if ((item.getName().toUpperCase()).contains(productName.toUpperCase())) {
                productsFind.add(item);
            }
        }
        if (productsFind.isEmpty()) {
            return null;
        }
        return productsFind;
    }

    @Override
    public List<Product> findByOrigin(String productBrand) {
        List<Product> products = getAllProdutcs();
        List<Product> productsFind = new ArrayList<>();
        for (Product item : products) {
            if ((item.getOrigin().toUpperCase()).contains(productBrand.toUpperCase())) {
                productsFind.add(item);
            }
        }
        if (productsFind.isEmpty()) {
            return null;
        }
        return productsFind;
    }

    @Override
    public List<Product> sortById(TypeSort typeSort) {
        List<Product> products = getAllProdutcs();
        if (typeSort == TypeSort.ASC) {
            products.sort((o1, o2) -> {
                double result = o1.getId() - o2.getId();
                if (result == 0)
                    return 0;
                return result > 0 ? 1 : -1;
            });
        }
        if (typeSort == TypeSort.DESC) {
            products.sort((o1, o2) -> {
                double result = o1.getId() - o2.getId();
                if (result == 0)
                    return 0;
                return result > 0 ? -1 : 1;
            });
        }
        FileUtils.writeDataToFile(pathProduct, products);
        return products;
    }

    @Override
    public List<Product> sortByName(TypeSort typeSort) {
        List<Product> products = getAllProdutcs();
        if (typeSort == TypeSort.ASC) {
            products.sort((o1, o2) -> {
                double result = o1.getName().compareTo(o2.getName());
                if (result == 0)
                    return 0;
                return result > 0 ? 1 : -1;
            });
        }
        if (typeSort == TypeSort.DESC) {
            products.sort((o1, o2) -> {
                double result = o1.getName().compareTo(o2.getName());
                if (result == 0)
                    return 0;
                return result > 0 ? -1 : 1;
            });
        }
        FileUtils.writeDataToFile(pathProduct, products);
        return products;
    }

    @Override
    public List<Product> sortByBrand(TypeSort typeSort) {
        List<Product> products = getAllProdutcs();
        if (typeSort == TypeSort.ASC) {
            products.sort((o1, o2) -> {
                double result = o1.getBrand().compareTo(o2.getBrand());
                if (result == 0)
                    return 0;
                return result > 0 ? 1 : -1;
            });
        }
        if (typeSort == TypeSort.DESC) {
            products.sort((o1, o2) -> {
                double result = o1.getBrand().compareTo(o2.getBrand());
                if (result == 0)
                    return 0;
                return result > 0 ? -1 : 1;
            });
        }
        FileUtils.writeDataToFile(pathProduct, products);
        return products;
    }

    @Override
    public List<Product> sortByQuantity(TypeSort typeSort) {
        List<Product> products = getAllProdutcs();
        if (typeSort == TypeSort.ASC) {
            products.sort((o1, o2) -> {
                int result = o1.getQuantity() - o2.getQuantity();
                if (result == 0)
                    return 0;
                return result > 0 ? 1 : -1;
            });
        }
        if (typeSort == TypeSort.DESC) {
            products.sort((o1, o2) -> {
                int result = o1.getQuantity() - o2.getQuantity();
                if (result == 0)
                    return 0;
                return result > 0 ? -1 : 1;
            });
        }
        FileUtils.writeDataToFile(pathProduct, products);
        return products;
    }

    @Override
    public List<Product> sortByPrice(TypeSort typeSort) {
        List<Product> products = getAllProdutcs();
        if (typeSort == TypeSort.ASC) {
            products.sort((o1, o2) -> {
                double result = o1.getPrice() - o2.getPrice();
                if (result == 0)
                    return 0;
                return result > 0 ? 1 : -1;
            });
        }
        if (typeSort == TypeSort.DESC) {
            products.sort((o1, o2) -> {
                double result = o1.getPrice() - o2.getPrice();
                if (result == 0)
                    return 0;
                return result > 0 ? -1 : 1;
            });
        }
        FileUtils.writeDataToFile(pathProduct, products);
        return products;
    }

    @Override
    public Product findProductById(long productId) {
        List<Product> products = getAllProdutcs();
        for (Product product : products) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }
}