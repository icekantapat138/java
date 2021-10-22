package ku.cs.model.customer;

import ku.cs.model.User.CustomerAccount;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ProductList {

    private ArrayList<Product> productList;

    public ProductList() {
        productList = new ArrayList<>();
    }

    public void addProduct(Product product){
        productList.add(product);
    }

    public ArrayList<Product> getAllProductList() {
        return productList;
    }

    public int countProduct() {
        return productList.size();
    }

    public ArrayList<Product> checkStoreName(String storename) {
        ArrayList<Product> arrayList = new ArrayList<>();
        for (Product product : productList){
            if(product.searchStoreName(storename)){
                arrayList.add(product);
            }
        }
        return arrayList;
    }


    public void sort(Comparator comparator) {
        Collections.sort(productList, comparator);
    }

    public Product searchProductName(String pdname){
        for(Product product : productList){
            if(product.checkProductName(pdname)){
                return product;
            }
        }
        return null;
    }


    public String toCsv() {
        String result = "";
        for (Product product : this.productList){
            result += product.toCsv() + "\n";
        }
        return result;
    }

    @Override
    public String toString() {
        return "ProductList{" +
                "productList=" + productList +
                '}';
    }
}
