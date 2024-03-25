import java.util.ArrayList;

public class ShoppingCart  extends WestminsterShoppingManager{
    //private ArrayList <Product> cart;
    private ArrayList<CartProducts> cart;
    User user;
    int count;
    int totalCount=0;


    public ShoppingCart()
    {

        this.cart = new ArrayList<>();
    }
    public ArrayList<CartProducts> getCart() {
        return cart;
    }

    public void addToCart(Product item) {

        if (item.getNoOfAvailableItem() > 0) {
            boolean found = false;
            for (CartProducts cartItem : cart) {
                if (cartItem.getProduct().getProductID().equals(item.getProductID())) {
                    cartItem.incrementCount();
                    item.noOfAvailableItem--;// Decrease availability
                    item.setNoOfAvailableItem(item.noOfAvailableItem);
                    found = true;
                    break;
                }
            }
            if (!found) {
                cart.add(new CartProducts(item));
                item.noOfAvailableItem--;
                item.setNoOfAvailableItem(item.noOfAvailableItem);// Decrease availability
            }
        } else {
            System.out.println("The item is out of stock.");
        }

    }


    public double totalAmount() {
        double totalCost = 0.0;
        for (CartProducts cartItem : cart) {
            totalCost += cartItem.getProduct().getPrice() * cartItem.getCount();
        }
        return totalCost;
    }
}