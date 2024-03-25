import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

public class MyTableCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        ItemsTable model = (ItemsTable) table.getModel();
        Product product = model.tableProductList.get(row);


        // Check inventory and set background color
        if (product.getNoOfAvailableItem() < 3) {
            setBackground(Color.RED);

        }
        else {
            setBackground(Color.WHITE);
        }
        return this;


    }

}



