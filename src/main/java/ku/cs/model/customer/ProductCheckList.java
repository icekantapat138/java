package ku.cs.model.customer;

import java.util.ArrayList;

public class ProductCheckList {

    private ArrayList<ProductCheck> productCheckArrayList;

    public ProductCheckList() {
        productCheckArrayList = new ArrayList<>();
    }

    public void addProductCheck(ProductCheck productCheck) {
        productCheckArrayList.add(productCheck);
    }

    public ArrayList<ProductCheck> getProductCheckArrayList() {
        return productCheckArrayList;
    }

    public int countProductCheck() {
        return productCheckArrayList.size();
    }

    public String toCsv() {
        String result = "";
        for (ProductCheck productCheck: productCheckArrayList){
            result += productCheck.toCsv() + "\n";
        }
        return result;
    }

    @Override
    public String toString() {
        return "ProductCheckList{" +
                "productCheckArrayList=" + productCheckArrayList +
                '}';
    }
}

