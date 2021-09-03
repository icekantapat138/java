package ku.cs.service.admin;

import ku.cs.model.customer.ProductList;

public class ProductListHardCodeDataSource {

    private ProductList product;

    public ProductListHardCodeDataSource() {
        product = new ProductList();
        readData();
    }

    private void readData(){
        ProductList lamborghini = new ProductList("Lamborghini Aventador S", "lamborghini aventador s lp740-4 740CC /544KW 0-100KM = 2.9S", "42000000");
        ProductList ferrari = new ProductList("Ferrari 488 Pista", "The Ferrari 488 Pista can punch out 720 cv at 8000 rpm, peaking at 770 Nm", "38000000");
        ProductList mclaren = new ProductList("McLaren 765LT", "McLaren 765LT compare than 720S but use Carbon Fibre ", "48000000");
        product.addProduct(lamborghini);
        product.addProduct(ferrari);
        product.addProduct(mclaren);
    }

    public ProductList getProductList() {
        return product;
    }
}
