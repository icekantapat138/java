package ku.cs.model.customer;

public class OrderProduct {
    private static ProductCheck productCheck;

    public OrderProduct(ProductCheck productCheck) {
        this.productCheck = productCheck;
    }

    public static ProductCheck getProduct() {
        return productCheck;
    }
}
