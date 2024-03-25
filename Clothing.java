public class Clothing extends Product implements java.io.Serializable
{
    private String Size;
    private String Colour;

    public Clothing(String productID,String productName,int noOfAvailableItem,double price,String Size,String Colour)
    {
        super(productID, productName, noOfAvailableItem, price);
        this.Size=Size;
        this.Colour=Colour;

    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    public String getColour() {

        return Colour;
    }

    public void setColour(String colour) {
        Colour = colour;
    }

    @Override
    public  String toString() {
        return "------Clothing-----" +"\n" +
                "productID= " + productID + "\n" +
                "productName= " + productName + "\n" +
                "noOfAvailableItem= " + noOfAvailableItem +"\n" +
                "price= " + price +"\n" +
                "Size= " + Size + "\n" +
                "Colour= " + Colour ;
    }
}