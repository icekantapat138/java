package ku.cs.service.customer;

import ku.cs.model.customer.AddProduct;
import ku.cs.model.customer.AddProductList;

public class AddProductListHardCodeDataSource {
    private AddProductList addProductList;

    public AddProductListHardCodeDataSource() {
        addProductList = new AddProductList();
        readData();
    }

    private void readData() {
        AddProduct McJean = new AddProduct("jean.png", "Mc Jean", "size 26", 10, 790);
        AddProduct UniqloPolo = new AddProduct("UniqloPolo.png", "Uniqlo Polo", "size 38", 12, 550);
        AddProduct AirpodPro = new AddProduct("airpodpro.jpg", "Airpod Pro", "designed by apple california", 2,9490);
        AddProduct Hermeswallet = new AddProduct("hermeswallet.jpg", "Hermes Wallet" , "Crocodile Hide", 5, 45000);
        addProductList.addProduct(McJean);
        addProductList.addProduct(UniqloPolo);
        addProductList.addProduct(Hermeswallet);
        addProductList.addProduct(AirpodPro);
    }

    public AddProductList getAddProductList() {
        return addProductList;
    }
}
