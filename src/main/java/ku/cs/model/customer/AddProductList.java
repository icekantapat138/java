package ku.cs.model.customer;

import java.util.ArrayList;

public class AddProductList {
    private ArrayList<AddProduct> products;

    public AddProductList() {
        products = new ArrayList<>();
    }

    public void addProduct(AddProduct pd) {
        products.add(pd);
    }

    public ArrayList<AddProduct> getAllProducts() {
        return products;
    }

    public int countProduct() {
        return products.size();
    }

    public String toCsv() {
        String result = "";
        for (AddProduct addProduct : this.products){
            result += addProduct.toCsv() + "\n";
        }
        return result;
    }

    @Override
    public String toString() {
        return "AddProductList{" +
                "products=" + products +
                '}';
    }
}
