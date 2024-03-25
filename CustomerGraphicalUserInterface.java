import org.w3c.dom.ls.LSOutput;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class CustomerGraphicalUserInterface extends WestminsterShoppingManager {

    public JTable table;
    JTable table2;

    JPanel firstPanel;
    JPanel secondPanel;
    JPanel thirdPanel;
    JPanel fourthPanel;
    JScrollPane scrollPane;
    JScrollPane scrollPane2;
    ArrayList<Product> filteredList;
    ItemsTable tableModel;
    JLabel productLabel;

    JComboBox productsType;

    CartTableModel myCartTable;
    Frame2 frame2;



    ShoppingCart myShoppingCart;
    private User user;



    private void updateTable(String selectedType) {
        productList = readFile();
        filteredList = new ArrayList<>();
        if (selectedType.equals("All")) {
            filteredList.addAll(productList);
            System.out.println(filteredList.size());
            System.out.println(" ");
        } else {
            for (Product product : productList) {
                if (product.getClass().getSimpleName().equalsIgnoreCase(selectedType)) {
                    filteredList.add(product);
                    System.out.println(filteredList.size());
                    System.out.println(" ");
                }
            }
        }
        if (scrollPane != null) {
            firstPanel.remove(scrollPane);
        }


        tableModel = new ItemsTable(filteredList); // Create model with filtered list

        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(250, 120, 850, 200);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        table.setGridColor(Color.BLACK);


        int i;
        int j;
        for (i = 0; i < 6; i++) {
            for (j = 0; j < filteredList.size(); j++) {
                tableModel.getValueAt(j, i);
            }
        }
        table.getColumnModel().getColumn(0).setCellRenderer(new MyTableCellRenderer());
        table.getSelectionModel().addListSelectionListener(rowListener);
        table.getTableHeader().setBackground(Color.LIGHT_GRAY);
        scrollPane.setVisible(true);
        firstPanel.add(scrollPane);


        firstPanel.revalidate();
        firstPanel.repaint();


    }



    ListSelectionListener rowListener = new ListSelectionListener() {
        public void valueChanged(ListSelectionEvent event) {
            if (table != null) {
                if (!event.getValueIsAdjusting()) {
                    int viewRow = table.getSelectedRow();
                    if (viewRow != -1) {
                        int modelRow = table.convertRowIndexToModel(viewRow);
                        Product product = tableModel.tableProductList.get(modelRow);
                        updateProductLabel(product); // Call method to update label
                        //System.out.println("not null");
                    } else {
                        clearProductLabel(); // Clear label if no row is selected

                    }
                } else System.out.println("");
            }
        }


        private void updateProductLabel(Product product) {

            if (productLabel != null) { // Remove existing label if present
                secondPanel.remove(productLabel);
            }

            if (product instanceof Electronics) {
                productLabel = new JLabel("<html>------------------------<br/><br/>Selected Product Information <br/><br/> Product ID : " + product.getProductID() + "<br/><br/>Product name: " + product.getProductName() +
                        " <br/><br/>Product Category: " + product.getClass().getSimpleName() + " <br/><br/>Brand name: " + ((Electronics) product).getBrandName() + " <br/><br/>Warranty period (Months): " + ((Electronics) product).getWarrantyPeriod()
                        + " <br/><br/>No of available items: " + product.getNoOfAvailableItem() + "<br/><br/> -------------------------</html>", SwingConstants.CENTER);

            } else if (product instanceof Clothing) {
                productLabel = new JLabel("<html>------------------------<br/><br/>Selected Product Information <br/><br/> Product ID : " + product.getProductID() + "<br/><br/>Product name: " + product.getProductName() +
                        " <br/><br/>Product Category: " + product.getClass().getSimpleName() + " <br/><br/>Size: " + ((Clothing) product).getSize() + " <br/><br/>Colour : " + ((Clothing) product).getColour()
                        + " <br/><br/>No of available items: " + product.getNoOfAvailableItem() + "<br/><br/> -------------------------</html>", SwingConstants.CENTER);
            }
            productLabel.setBounds(400, 350, 400, 200);
            productLabel.setVisible(true);
            secondPanel.add(productLabel);
            //secondPanel.add(productLabel); // Use a layout manager
            secondPanel.revalidate(); // Ensure label is displayed
        }




        private void clearProductLabel() {
            if (productLabel != null) {
                secondPanel.remove(productLabel);
                productLabel = null;
                secondPanel.revalidate();
            }
        }
    };

    ActionListener cartActionListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (table != null) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int modelRow = table.convertRowIndexToModel(selectedRow);
                    Product selectedProduct = tableModel.tableProductList.get(modelRow);


                    /*if (myShoppingCart == null) {
                        myShoppingCart = new shoppingCart();
                    }*/
                    myShoppingCart = user.getMyCart();
                    myShoppingCart.addToCart(selectedProduct);
                    //System.out.println("clicked btn"+myShoppingCart.getCart().size());
                    saveFile();
                    updateTable(productsType.getSelectedItem().toString());


                }
            } else
                System.out.println("no table");

        }
    };

    ActionListener cbActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedType = (String) productsType.getSelectedItem();
            updateTable(selectedType);
            //System.out.println("ActionEvent");

        }
    };


    ItemListener cbItemListener = new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String selectedType = (String) productsType.getSelectedItem();
                updateTable(selectedType);
            }
        }
    };
    private void updateCartTable()
    {
        /*if (scrollPane2 != null) {
            frame2.remove(scrollPane2);
        }*/

        myCartTable = new CartTableModel(myShoppingCart); // Create model with filtered list

        table2 = new JTable(myCartTable);
        scrollPane2 = new JScrollPane(table2);
        scrollPane2.setBounds(50, 50, 600, 300);
        scrollPane2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        table2.setGridColor(Color.BLACK);
        //System.out.println("MyShopping cart size"+myShoppingCart.getCart().size());



        int i;
        int j;
        for (i = 0; i < 3; i++) {
            for (j = 0; j <myShoppingCart.getCart().size() ; j++) {
                myCartTable.getValueAt(j, i);
            }
        }

        table2.getTableHeader().setBackground(Color.LIGHT_GRAY);
        scrollPane2.setVisible(true);
        thirdPanel.add(scrollPane2,BorderLayout.CENTER);


        thirdPanel.revalidate();
        thirdPanel.repaint();

    }

    private void finalOutput(User user) {
        double discountTen=0;
        double discountTwenty=0;

        JLabel text1 = new JLabel(" Total :                                                                         "+ myShoppingCart.totalAmount());
        //JLabel text2 = new JLabel(" First Purchase discount (10%) :                                           -" + discount1);
        // JLabel text3 = new JLabel(" Three items in the same Category Discount (20%) :                         -" + discount2);
        //JLabel text4 = new JLabel(" Final Total :                                                              " + (myShoppingCart.totalAmount() - myShoppingCart.totalAmount() * 0.1 - myShoppingCart.totalAmount() * 0.2));

        /* text1.setBounds(250,500,100,40);
         text2.setBounds(250,550,100,40);
         text3.setBounds(250,600,100,40);*/

        fourthPanel.add(text1);
        if (user.getNewCustomer() == true) {
            discountTen=myShoppingCart.totalAmount() * 0.1;
            //JLabel text2 = new JLabel("First Purchase discount (10%) :                                      -" + discount1);
            JLabel firstPurchaseDiscounttext2 = new JLabel(" First Purchase discount (10%) :                                   -" + discountTen);
            fourthPanel.add(firstPurchaseDiscounttext2);
        }



        for (CartProducts cartItem : user.getMyCart().getCart())
        {
            int productCount = cartItem.getCount();
            if (productCount >= 3) {
                discountTwenty=myShoppingCart.totalAmount() * 0.2;
                //JLabel text3 = new JLabel(" Three items in the same Category Discount (20%) : -" + discount2);
                JLabel discount20text3 = new JLabel(" Three items in the same Category Discount (20%) :   -" + discountTwenty);
                fourthPanel.add(discount20text3);
                break;
            }

        }


        //JLabel text4 = new JLabel(" Final Total :                                                        " + (myShoppingCart.totalAmount() - discount1-discount2));
        JLabel text4 = new JLabel(" Final Total :                                                                 "+ (myShoppingCart.totalAmount() - myShoppingCart.totalAmount() * 0.1 - myShoppingCart.totalAmount() * 0.2));

        text4.setBounds(250,650,100,40);
        fourthPanel.add(text4);

    }



    public class frame extends JFrame {
        public frame() {
            setTitle("Westminster Shopping Center");
            setLayout(new BorderLayout());
            setSize(1500, 1200);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



            firstPanel = new JPanel();
            firstPanel.setLayout(null);
            secondPanel = new JPanel();





        }
    }


    public  CustomerGraphicalUserInterface(User user) {
        //JFrame frame = new JFrame("Westminster Shopping center ");
        // frame.setSize(1500,1200);
        //frame.setLayout(new BorderLayout());
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.user = user;
        frame frame1 = new frame();


        // firstPanel = new JPanel();
        //firstPanel.setLayout(null);


        JLabel text = new JLabel("Select Product Category");
        text.setBounds(500, 45, 200, 100);
        firstPanel.add(text);


        String productTypeDropDownList[] = {"All", "Electronics", "Clothing"};
        productsType = new JComboBox<>(productTypeDropDownList);
        productsType.setSelectedIndex(0);
        productsType.setBounds(700, 65, 200, 40);
        firstPanel.add(productsType);




        productsType.addActionListener(cbActionListener);
        productsType.addItemListener(cbItemListener);


        //JButton shoppingCartBtn = new JButton("Shopping Cart");
        // shoppingCartBtn.setBounds(1250, 20, 150, 80);
        //shoppingCartBtn.addActionListener(viewCartActionListener);
        // firstPanel.add(shoppingCartBtn);


        /*shoppingCartBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame2 = new JFrame();
                frame2.setSize(900,700);
                frame2.setLayout(new BorderLayout());
                frame2.setTitle("Shopping cart");
                frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame2.setVisible(true);

            }
        });*/



        secondPanel.setBackground(Color.GRAY);

        JButton btn = new JButton("Add to Shopping Cart");
        btn.setSize(30, 50);
        btn.setBounds(800, 100, 200, 100);
        btn.addActionListener(cartActionListener);
        secondPanel.add(btn);


        frame1.add(firstPanel, BorderLayout.CENTER);
        frame1.add(secondPanel, BorderLayout.SOUTH);


        frame1.setVisible(true);
        updateTable(productsType.getSelectedItem().toString());

    }

    class Frame2 extends JFrame {
        public Frame2() {
            setSize(900, 700);
            setLayout(new BorderLayout());
            setTitle("Shopping Cart");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        }
    }


}