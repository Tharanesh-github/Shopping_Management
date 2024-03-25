public class User implements java.io.Serializable{
    private String userName;
    private String password;
    private Boolean newCustomer;
    private ShoppingCart cart;
    private static final long serialVersionUID = 6531158079742180310L; // Use the value from the file


    public User(String userName, String password,Boolean newCustomer) {
        this.userName = userName;
        this.password = password;
        this.newCustomer=newCustomer;

    }


    public Boolean getNewCustomer() {
        return newCustomer;
    }

    public void setNewCustomer(Boolean newCustomer) {
        this.newCustomer = newCustomer;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ShoppingCart getMyCart(){return cart;}
    public  void setCart(ShoppingCart cart){this.cart=cart;}




    public void setPassword(String password)
    {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", newCustomer=" + newCustomer +
                '}';
    }



}