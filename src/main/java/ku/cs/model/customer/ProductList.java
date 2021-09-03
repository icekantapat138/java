package ku.cs.model.customer;

import java.util.ArrayList;

public class ProductList {

    private String product;
    private String describe;
    private String price;
    private ArrayList<ProductList> productLists;

    public ProductList(String product, String describe, String price) {
        this.product = product;
        this.describe = describe;
        this.price = price;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public ArrayList<ProductList> getProductLists() {
        return productLists;
    }

    public void setProductLists(ArrayList<ProductList> productLists) {
        this.productLists = productLists;
    }

    public ProductList() {
        productLists = new ArrayList<>();
    }

    public void addProduct(ProductList pd) {
        productLists.add(pd);
    }

    public ArrayList<ProductList> getAllProduct() {
        return productLists;
    }

    @Override
    public String toString() {
        return product + "," +  price;
    }
}
