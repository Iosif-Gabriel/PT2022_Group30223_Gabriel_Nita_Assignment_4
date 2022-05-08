package View;

import javax.swing.*;
import javax.swing.table.TableColumn;

public class ProductTableView {
    public String[] column={"Title","Rating","Calories","Protein","Fat","Sodium","Price"};
    private JTable prodJTable;
    private JScrollPane jScrollPane;

    public ProductTableView(String[][] prod){
        JFrame tab=new JFrame("Products");
        tab.setResizable(false);
        prodJTable =new JTable(prod,column);
        prodJTable.setRowSelectionAllowed(true);
        prodJTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jScrollPane=new JScrollPane(prodJTable);
        tab.add(jScrollPane);
        tab.setBounds(660,110,1010,520);
        TableColumn col= prodJTable.getColumnModel().getColumn(0);
        col.setPreferredWidth(410);
        tab.setVisible(true);
    }

    public JTable getProdJTable() {
        return prodJTable;
    }
}
