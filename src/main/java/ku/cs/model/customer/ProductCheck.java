package ku.cs.model.customer;

public class ProductCheck {
    private String pdname;
    private String requireproduct;
    private String totalprice;
    private String image;


    public ProductCheck(String pdname, String requireproduct, String totalprice, String image) {
        this.pdname = pdname;
        this.requireproduct = requireproduct;
        this.totalprice = totalprice;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPdname() {
        return pdname;
    }

    public void setPdname(String pdname) {
        this.pdname = pdname;
    }

    public String getRequireproduct() {
        return requireproduct;
    }

    public void setRequireproduct(String requireproduct) {
        this.requireproduct = requireproduct;
    }

    public String getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }

    public String toCsv() {
        return pdname + "," + requireproduct + "," + totalprice + "," + image;
    }

    @Override
    public String toString() {
        return "ProductCheck{" +
                "pdname='" + pdname + '\'' +
                ", requireproduct='" + requireproduct + '\'' +
                ", totalprice='" + totalprice + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
