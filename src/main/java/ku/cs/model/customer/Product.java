package ku.cs.model.customer;

public class Product {

    private String storename;
    private String lowproduct;
    private String pdname;
    private String pddetail;
    private String pdamount;
    private String pdprice;
    private String pdimage;
    private String date;

    public Product(String storename, String lowproduct, String pdname, String pddetail, String pdamount, String pdprice, String pdimage, String date) {
        this.storename = storename;
        this.lowproduct = lowproduct;
        this.pdname = pdname;
        this.pddetail = pddetail;
        this.pdamount = pdamount;
        this.pdprice = pdprice;
        this.pdimage = pdimage;
        this.date = date;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public String getLowproduct() {
        return lowproduct;
    }

    public void setLowproduct(String lowproduct) {
        this.lowproduct = lowproduct;
    }

    public String getPdname() {
        return pdname;
    }

    public void setPdname(String pdname) {
        this.pdname = pdname;
    }

    public String getPddetail() {
        return pddetail;
    }

    public void setPddetail(String pddetail) {
        this.pddetail = pddetail;
    }

    public String getPdamount() {
        return pdamount;
    }

    public void setPdamount(String pdamount) {
        this.pdamount = pdamount;
    }

    public String getPdprice() {
        return pdprice;
    }

    public void setPdprice(String pdprice) {
        this.pdprice = pdprice;
    }

    public String getPdimage() {
        return pdimage;
    }

    public void setPdimage(String pdimage) {
        this.pdimage = pdimage;
    }

    public int pdcountToInt(){
        return Integer.parseInt(pdamount);
    }

    public String getDate() {
        return date;
    }

    public int pdAmounttoInt() {
        return Integer.parseInt(pdamount);
    }

    public int pdpriceToInt(){
        return Integer.parseInt(pdprice);
    }

    public boolean searchStoreName(String storename){
        return this.storename.equals(storename);
    }

    public boolean checkProductName(String pdname) {
        return this.pdname.equals(pdname);
    }

    public String toCsv() {
        return storename + "," + lowproduct + "," + pdname + "," + pddetail + "," + pdamount + "," + pdprice + "," + pdimage + "," + date;
    }

    public boolean checkLowAmount() {
        if (Integer.parseInt(getPdamount()) < Integer.parseInt(getLowproduct())){
            return true;
        }
        return false;
    }


    @Override
    public String toString() {
        return storename + "," + pdname;
    }
}

