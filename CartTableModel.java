import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class CartTableModel extends AbstractTableModel {
    private String columnNames[] = {"Product Name", "Number Of Items", "Price"};
    ShoppingCart myCart;
    int countOfItem;
    ArrayList<CartProducts> myTableProductList;

    public CartTableModel( ShoppingCart myCart ) {

        this.myCart = myCart;
        myTableProductList = myCart.getCart();
    }




    @Override
    public int getRowCount() {
        return myTableProductList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        CartProducts product = myTableProductList.get(row);
        if (col == 0) {
            if (product.getProduct() instanceof Electronics) {
                return product.getProduct().getProductID() + "\n" + ((Electronics) product.getProduct()).getBrandName() + "\n" + ((Electronics) product.getProduct()).getWarrantyPeriod() + " months warranty";
            } else if (product.getProduct() instanceof Clothing) {
                return product.getProduct().getProductID() + "\n" + ((Clothing) product.getProduct()).getColour() + "\n" + ((Clothing) product.getProduct()).getSize();
            }
        } else if (col == 1) {
            if (myCart != null) {
                countOfItem =product.getCount();
                //System.out.println("here my count"+countOfItem);
                return countOfItem;
            }
        } else if (col == 2) {
            return product.getProduct().getPrice() * countOfItem; // Price
        }
        return null;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Class getColumnClass(int col) {
        if (col == 0) {
            return String.class;
        } else if (col == 1) {
            return Integer.class;
        } else {
            return Double.class;
        }
    }
}