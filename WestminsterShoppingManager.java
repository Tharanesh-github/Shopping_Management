import java.awt.event.ActionEvent;
import java.util.*;
import java.io.*;
import java.util.Collections;
public  class WestminsterShoppingManager implements ShoppingManager,java.io.Serializable
{
    private static final long serialVersionUID = 1L;
    ArrayList<Product> productList = new ArrayList<>();
    static Scanner input = new Scanner(System.in);
    static int noOfProducts;
    String productID = "";

    ArrayList<User> userList=new ArrayList<>();

    ShoppingCart myShoppingCart;

    public static int intInputValidation(Scanner scanner, String prompt) {
        int userInput = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print(prompt);
                userInput = input.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: Please Enter A Valid Integer.");
                input.next();
            }
        }
        return userInput;
    }

    public String productIdValidation() {
        boolean validInput1 = false;
        while (!validInput1) {
            System.out.print("Enter the Product ID(Eg: P01 ,P50):  ");
            if (input.hasNextInt()) {
                System.out.println("Error: Please Enter a Correct Input.");
                input.next();
            } else {
                productID = input.next().toUpperCase();
                if (productID.charAt(0) != 'P' || productID.length() != 3 || !(Character.isDigit(productID.charAt(1)) && Character.isDigit(productID.charAt(2)))) {
                    System.out.println("Error: Please Enter a Correct Input.");
                } else {
                    validInput1 = true;
                }
            }
        }
        return productID;

    }
    public void addProduct() {

        noOfProducts=productList.size();
        if (noOfProducts < 50) {
            //i have to check this iteration
            productID = productIdValidation();
            System.out.print("Enter the Product name: ");
            String productName = input.next();
            int noOfItems = intInputValidation(input, "Enter the no.of Items: ");

            while (noOfItems > 50 ){
                System.out.println("Maximum 50 Products can be added");
                System.out.print("Enter the no.of Items: ");
                noOfItems = input.nextInt();
            }

            boolean validInput2 = false;
            double price = 0.0;
            while (!validInput2) {
                System.out.print("Enter the Price of the Product:  ");
                if (input.hasNextDouble()) {
                    price = input.nextDouble();
                    validInput2 = true;
                } else {
                    System.out.println("Error: Please Enter a Correct Value");
                    input.next();
                }
            }

            boolean validInput3 = false;
            System.out.println("\nOption for electronics is 1 \n Option for clothing is 2 \n ");
            Product p = null;
            while (!validInput3) {
                int option = intInputValidation(input, "Enter the Product Type: ");
                if (option == 1) {
                    System.out.print("Enter the Brand Name: ");
                    String brandName = input.next();
                    int warrantyPeriod = intInputValidation(input, "Enter the Warranty Period(No.of Months): ");
                    p = new Electronics(productID, productName, noOfItems, price, brandName, warrantyPeriod);
                    validInput3 = true;
                } else if (option == 2) {
                    boolean validInput4 = false;
                    int i;
                    String size = " ";
                    String[] sizeList = {"S", "M", "L", "XL", "XXL"};
                    while (!validInput4) {
                        System.out.print("\nEnter the Dress Size(S,M,L,XL,XXL): ");
                        size = input.next().toUpperCase();
                        for (i = 0; i < 5; i++) {
                            if (sizeList[i].equals(size)) {
                                validInput4 = true;
                                break;
                            }
                        }
                        if (!validInput4) {
                            System.out.print("Error: Please Enter a  Correct input.");
                        }
                    }
                    System.out.print("Enter The Colour:  ");
                    String colour = input.next();
                    p = new Clothing(productID, productName, noOfItems, price, size, colour);
                    validInput3 = true;
                } else {
                    System.out.println("Entered a Invalid Number. Please Enter the Correct Number");
                }
            }
            productList.add(p);
            noOfProducts++;

        } else {
            System.out.println("50 Products only can be Added");

        }
    }

    public void deleteProduct() {
        productList=readFile();
        productID = productIdValidation();
        boolean validInput6 =false;
        boolean itemAvailable=false;
        for(int i = 0; i < productList.size(); i++)
        {
            Product product = productList.get(i);
            if(productID.equals(product.getProductID())){
                itemAvailable=true;
                System.out.println(product.toString());
                while (!validInput6) {
                    System.out.print("Do you want to Delete the Product(Y/N): ");
                    String answer = input.next().toUpperCase();
                    if (answer.equals("Y")) {
                        productList.remove(product);
                        System.out.println(productID + " is deleted.");
                        saveFile();
                        validInput6=true;
                        break;
                    } else if (answer.equals("N") ){
                        System.out.println("Thankyou");
                        validInput6=true;
                        break;
                    }
                    else{
                        System.out.println("Enter the Correct Input");
                    }
                }

            }


        }if(!itemAvailable){
            System.out.println(productID+"is not available");
        }


    }

    public void printProduct()  {
        productList=readFile();
        Collections.sort(productList);
        for(Product product : productList){
            System.out.println(product.toString());
            System.out.println("----------------------");
        }
    }

    public ArrayList<Product> readFile(){

        if (Utility.fileExists("file.ser")) try
        {
            FileInputStream fileIn = new FileInputStream("file.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            productList = (ArrayList<Product>) in.readObject();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return productList;
    }

    public void saveFile()  {

        try{
            FileOutputStream fileOut = new FileOutputStream("file.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(productList);
            //System.out.println("Products are saved successfully");
            out.close();


        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }


    public  void verifyCustomer()
    {
        boolean validInput8=false;
        while (!validInput8) {
            System.out.println("Are you a New Customer (Y/N): ");
            String result = input.next().toUpperCase();
            if (result.equals("Y")) {
                System.out.println("Create a Username and Password.");
                System.out.println("Create a Username:  ");
                String username=input.next();
                System.out.println("Create a Password: ");
                String password= input.next();
                User user=new User(username,password,true);
                userList.add(user);
                System.out.println(userList.get(0));
                myShoppingCart=new ShoppingCart();
                user.setCart(myShoppingCart);
                userSaveFile();
                customerPage(user);
                validInput8=true;
                break;
            } else if (result.equals("N") ){
                userList=userReadFile();
                System.out.println(userList.get(0));
                System.out.println("Enter Your Username: ");
                String username= input.next();
                System.out.println("Enter Your Password: ");
                String password= input.next();
                boolean userThere=false;
                for( User u:userList){
                    if(u.getUserName().equals(username))
                    {
                        if((u.getPassword().equals(password) ))
                        {   u.setNewCustomer(false);
                            myShoppingCart=new ShoppingCart();
                            u.setCart(myShoppingCart);
                            userSaveFile();
                            System.out.println(u.getNewCustomer());
                            userThere=true;
                            customerPage(u);
                            validInput8=true;
                            break;}

                    }}
                if(!userThere){
                    System.out.println("User or Password is Wrong");
                    validInput8=true;
                    break;
                }

            }
            else{
                System.out.println("Enter The Correct Input");
            }
        }

    }


    public ActionEvent customerPage(User verifiedUser)
    {

        new CustomerGraphicalUserInterface( verifiedUser);
        return null;
    }

    public void userSaveFile()  {

        try{
            FileOutputStream userFileOut = new FileOutputStream("user.ser");
            ObjectOutputStream userOut = new ObjectOutputStream(userFileOut);
            userOut.writeObject(userList);
            System.out.println("User details are saved successfully");
            userOut.close();


        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public ArrayList<User> userReadFile(){

        if (Utility.fileExists("user.ser")) try
        {
            FileInputStream userFileIn = new FileInputStream("user.ser");
            ObjectInputStream userIn = new ObjectInputStream(userFileIn);
            userList = (ArrayList<User>) userIn.readObject();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return userList;
    }







    public static void main(String[] args) {

        ShoppingManager Manager = new WestminsterShoppingManager();
        boolean validInput5 = false;
        while(!validInput5)

        {
            System.out.println("||-------------------------------------------------------------||");
            System.out.println("||         Welcome to the Westminster online shop              || ");
            System.out.println("||-------------------------------------------------------------||");
            System.out.println("||     -To Add New Product enter                   '1'         ||");
            System.out.println("||     -To Delete a Product enter                  '2'         ||");
            System.out.println("||     -To Print the list of Products enter        '3'         ||");
            System.out.println("||     -To Save in the File enter                  '4'         ||");
            System.out.println("||     -To Open GUI enter                          '5'         ||");
            System.out.println("||     -To Exit the Program enter                  '6'         ||");
            System.out.println("||-------------------------------------------------------------||");
            int option = intInputValidation(input, "Enter Your Selection:  ");
            switch (option) {
                case (1):
                    Manager.addProduct();
                    break;
                case (2):
                    Manager.deleteProduct();
                    break;
                case (3):
                    Manager.printProduct();
                    break;
                case (4):
                    Manager.saveFile();
                    break;
                case (5):
                    Manager.verifyCustomer();
                    break;
                case (6):
                    System.out.println("Program Is Ending");
                    validInput5 = true;
                    break;
                default:
                    System.out.println("Enter A Valid Integer");
            }
        }
    }
}