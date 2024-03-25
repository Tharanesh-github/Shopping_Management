public class Electronics extends Product implements java.io.Serializable
{
    private String brandName;
    private int warrantyPeriod;

    public Electronics(String productID,String productName,int noOfAvailableItem,double price,String brandName,int warrantyPeriod)
    {
        super(productID,productName,noOfAvailableItem,price);
        this.brandName=brandName;
        this.warrantyPeriod=warrantyPeriod;

    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    @Override
    public  String toString() {
        return "-----Electronics------" +"\n"+
                "productID= " + productID +"\n"+
                "productName= " + productName + "\n" +
                "noOfAvailableItem= " + noOfAvailableItem +"\n" +
                "price= " + price +"\n" +
                "brandName= " + brandName +"\n" +
                "warrantyPeriod= " + warrantyPeriod ;
    }
}