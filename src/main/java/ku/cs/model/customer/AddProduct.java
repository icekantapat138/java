package ku.cs.model.customer;

public class AddProduct {

    private String image;
    private String productname;
    private String describeproduct;
    private int count;
    private double priceproduct;

    public AddProduct(String image, String productname, String describeproduct, int count, double priceproduct) {
        this.image = image;
        this.productname = productname;
        this.describeproduct = describeproduct;
        this.count = count;
        this.priceproduct = priceproduct;
    }

    public String getImage() {
        return image;
    }

    public String getProductname() {
        return productname;
    }

    public String getDescribeproduct() {
        return describeproduct;
    }

    public int getCount() {
        return count;
    }

    public double getPriceproduct() {
        return priceproduct;
    }

    public boolean checkCount() {
        int minProduct = 5;
        if (this.count < minProduct){
            return false;
        }else {
            return true;
        }
    }

    public void addCount(double pieces){
        if (pieces <= 0){
            return;
        }
        count += pieces;
    }

    public AddProduct() {
        return;
    }

    @Override
    public String toString()  {
        return productname + "," + count + "," + priceproduct;
    }

    public String toCsv() {
        return "Product" + "," + image + "," + productname + "," + describeproduct + "," + count + "," + priceproduct;
    }
}
