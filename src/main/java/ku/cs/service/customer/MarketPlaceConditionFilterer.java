package ku.cs.service.customer;

import ku.cs.model.customer.AddProduct;
import ku.cs.model.customer.AddProductList;

public class MarketPlaceConditionFilterer implements ConditionFilterer<AddProduct> {

    private int maxfive;

    public MarketPlaceConditionFilterer(int maxfive) {
        this.maxfive = 5;
    }

    @Override
    public boolean check(AddProduct addProduct) {
        return addProduct.getCount() >= maxfive;
    }
}
