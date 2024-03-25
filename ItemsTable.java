import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ItemsTable extends AbstractTableModel
{
    private String columnNames[]={"Product ID","Name","Category","Price(£)","No of available items","Info"};
    ArrayList<Product> tableProductList;

    public ItemsTable(ArrayList<Product> list)
    {

        tableProductList=list;

    }
    @Override
    public int getRowCount() {
        return tableProductList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Object temp = null;
        if (col == 0) {
            temp = tableProductList.get(row).getProductID();
        } else if (col == 1) {
            temp = tableProductList.get(row).getProductName();
        } else if (col == 2) {
            temp = tableProductList.get(row).getClass().getSimpleName();
        } else if (col == 3) {
            temp = new Double(tableProductList.get(row).getPrice());
        } else if (col == 4){
            temp = tableProductList.get(row).getNoOfAvailableItem();
        } else if (col == 5) {
            Product product = tableProductList.get(row);
            if (product instanceof Electronics) {
                temp = ((Electronics) product).getBrandName() + ", " + ((Electronics) product).getWarrantyPeriod()+" months warranty";
            } else if (product instanceof Clothing) {
                temp = ((Clothing) product).getColour() + ", " + ((Clothing) product).getSize();
            }

        }
        if (temp == null) {
            temp="-";
        }

        return temp;
    }



    public String getColumnName(int col) {
        return columnNames[col];
    }
    public Class getColumnClass(int col) {
        if (col == 0) {
            return Product.class;
        } else if (col == 3) {
            return Double.class;

        } else if (col==4)
        {
            return int.class;
        } else {
            return String.class;
        }

    }

    /*public void setBackgroundColor(JTable table,int row)
    {


    }*/



}