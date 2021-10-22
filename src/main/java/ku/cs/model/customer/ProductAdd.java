package ku.cs.model.customer;

public class ProductAdd {
    private static Product product;

    public ProductAdd(Product product) {
        this.product = product;
    }

    public static Product getProduct() {
        return product;
    }
}
