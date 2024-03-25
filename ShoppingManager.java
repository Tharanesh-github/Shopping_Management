import java.awt.event.ActionEvent;
import java.io.IOException;
import java.awt.*;
import javax.swing.*;


public interface ShoppingManager
{
    public   void addProduct()  ;


    public  void deleteProduct();

    public  void printProduct() ;

    public void saveFile() ;
    ActionEvent customerPage(User verifiedUser);
    public void verifyCustomer();



}