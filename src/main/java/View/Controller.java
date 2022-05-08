package View;

import BuisinessLogic.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Controller {

    private AdminView adminView;
    private LogInRegisterView logInView;
    private LogInRegister logInRegister;
    private ProductTableView productTable;
    private SearchView searchView;
    private DeliveryService deliveryService;
    private final Set<MenuItem> csvProd;
    private DeleteView deleteView;
    private CustomerView customerView;
    private AddView ad;
    private ModifyView modifyView;
    private OrderView orderView;
    private EmployeeView employeeView;
    private Report1View raport1View;
    private Report2View raport2View;
    private Report3View raport3View;
    private Report4View raport4View;

    public Controller(LogInRegisterView logInView) throws IOException, ClassNotFoundException {
        this.logInView=logInView;
        this.logInRegister=new LogInRegister();
        this.adminView=new AdminView();
        this.deleteView=new DeleteView();
        this.deliveryService=new DeliveryService();
        deliveryService. importProducts();
        this.customerView=new CustomerView();
        this.searchView=new SearchView();
        this.ad=new AddView();
        this.modifyView=new ModifyView();
        this.orderView=new OrderView();
        this.employeeView=new EmployeeView();
        raport1View=new Report1View();
        raport2View=new Report2View();
        raport3View=new Report3View();
        raport4View=new Report4View();

        csvProd=deliveryService.getSer().deserializeP();
        logInView.addLogInButtonListener(new LogInButton());
        logInView.addRegisterButtonListener(new RegisterButtonAddListener());
        //adminView.addViewProdListener(new AdminCustomerEmployeeOptionsAddListener());
        adminView.addImportProdListener(new AdminCustomerEmployeeOptionsAddListener());
        adminView.addDelProdListener(new AdminCustomerEmployeeOptionsAddListener());
        adminView.addAddProdListener(new AdminCustomerEmployeeOptionsAddListener());
        adminView.addModifyProdListener(new AdminCustomerEmployeeOptionsAddListener());
        adminView.addGetRap1Listener(new AdminCustomerEmployeeOptionsAddListener());
        adminView.addGetRap2Listener(new AdminCustomerEmployeeOptionsAddListener());
        adminView.addGetRap3Listener(new AdminCustomerEmployeeOptionsAddListener());
        adminView.addGetRap4Listener(new AdminCustomerEmployeeOptionsAddListener());
        customerView.addViewAllButtonListener(new AdminCustomerEmployeeOptionsAddListener());
        customerView.addSearchButtonListener(new AdminCustomerEmployeeOptionsAddListener());
        customerView.addOrderButtonListener(new AdminCustomerEmployeeOptionsAddListener());
        raport1View.addGenerateListener(new ReportAddListener());
        raport2View.addGenerateListener(new ReportAddListener());
        raport3View.addGenerateListener(new ReportAddListener());
        raport4View.addGenerateListener(new ReportAddListener());
        deliveryService.addObserver(employeeView);
        modifyView.addModifyButtonListener(new ModifyAddListener());
        deleteView.addDelProdListener(new DeleteButtonAddListener());
        searchView.addSearchListener(new SearchButtonAddListener());
        ad.addAddButtonListener(new AddProductAddListener());
        orderView.addPlaceOrderListener(new PlaceOrderAddListener());
    }

    class LogInButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==logInView.getLoginButton()){
                try {
                    if(logInRegister.logInCustomer(logInView.getLoginUserText(),logInView.getLoginPassText())){
                        customerView.setVisible(true);
                        JOptionPane.showMessageDialog(null,"corect creditals");
                    }
                    else{
                        if(logInRegister.adminLogIn(logInView.getLoginUserText(),logInView.getLoginPassText())){
                            adminView.setVisible(true);
                        }else if(logInRegister.employeeLogIn(logInView.getLoginUserText(),logInView.getLoginPassText())){
                            employeeView.setVisible(true);
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"Incorect creditals2");
                        }

                    }
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }

            }

        }
    }

    class RegisterButtonAddListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==logInView.getRegisterButton()){
                if(!logInRegister.register(logInView.getRegisterUserText(),logInView.getRegisterPassText())){
                    JOptionPane.showMessageDialog(null,"Acount already exists!");
                }
            }
        }
    }


    class DeleteButtonAddListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==deleteView.getDeleteButton()){
                String jtitle=deleteView.getDelTextField();
                try {
                    deliveryService.deleteProduct(jtitle);
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
        }
    }


    class SearchButtonAddListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==searchView.getSearchButton()) {
                Set<MenuItem> items;
                String name="";
                double rating=-1;
                int calories=-1,protein=-1,fat=-1, sodium=-1, price=-1;
                if(!searchView.getPriceJText().isBlank())
                    price= Integer.parseInt(searchView.getPriceJText());
                if(!searchView.getSodiumJText().isBlank())
                    sodium= Integer.parseInt(searchView.getSodiumJText());
                if(!searchView.getFatJText().isBlank())
                    fat= Integer.parseInt(searchView.getFatJText());
                if(!searchView.getProteinJText().isBlank())
                    protein= Integer.parseInt(searchView.getProteinJText());
                if(!searchView.getCaloriesJtext().isBlank())
                    calories= Integer.parseInt(searchView.getCaloriesJtext());
                if(!searchView.getRatingJText().isBlank())
                    rating= Double.parseDouble(searchView.getRatingJText());
                if(!searchView.getTitleJtext().isBlank())
                    name=searchView.getTitleJtext();

                try {
                    items=deliveryService.search(name,rating,calories,protein,fat,sodium,price);
                    productTable=new ProductTableView(deliveryService.getProductsForTableFromSet(items));
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }

        }
    }

    public class AddProductAddListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==ad.getAddButton()){
                    try{
                        BaseProduct base = new BaseProduct(ad.getTitleJtext(),
                                Double.parseDouble(ad.getRatingJText()),
                                Integer.parseInt(ad.getCaloriesJText()),
                                Integer.parseInt(ad.getProteinJText()),
                                Integer.parseInt(ad.getFatJText()),
                                Integer.parseInt(ad.getSodiumJText()),
                                Integer.parseInt(ad.getPriceJText()));
                        deliveryService.addProduct(base);
                    }catch (NumberFormatException | ClassNotFoundException | IOException ex){
                        ex.printStackTrace();
                    }
                }
        }
    }

    public class AdminCustomerEmployeeOptionsAddListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==customerView.getViewButton()){
                try {
                    productTable=new ProductTableView(deliveryService.getProductsForTable());
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
            if(e.getSource()==adminView.getDelButton()) {
                deleteView.setVisible(true);
            }
            if(e.getSource()==adminView.getAddButton()){
                ad.setVisible(true);
            }
            if(e.getSource()==adminView.getModifyButton()){
                modifyView.setVisible(true);
            }
            if(e.getSource()==customerView.getSearchButton()){
                searchView.setVisible(true);
            }
            if(e.getSource()==customerView.getCreateOrderButton()){
                orderView.setVisible(true);
            }
            if(e.getSource()==adminView.getRap1button()){
                raport1View.setVisible(true);
            }
            if(e.getSource()==adminView.getRap2Button()){
                raport2View.setVisible(true);
            }
            if(e.getSource()==adminView.getRap3button()){
                raport3View.setVisible(true);
            }
            if(e.getSource()==adminView.getRap4Button()){
                raport4View.setVisible(true);
            }
            if(e.getSource()==adminView.getImportButton()){
                productTable=new ProductTableView(deliveryService.getProductsForTableFromSet(csvProd));
            }

        }
    }

    public class ModifyAddListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==modifyView.getModifyButton()){
                String nameOld="",newName="";
                nameOld=modifyView.getOldNameJText();
                double rating=-1;
                int calories=-1,protein=-1,fat=-1, sodium=-1, price=-1;
                if(!modifyView.getTitleJText().isBlank())
                    newName=modifyView.getTitleJText();
                if(!modifyView.getRatingJText().isBlank())
                    rating= Double.parseDouble(modifyView.getRatingJText());
                if(!modifyView.getCaloriesJText().isBlank())
                    calories= Integer.parseInt(modifyView.getCaloriesJText());
                if(!modifyView.getProteinJText().isBlank())
                    protein=Integer.parseInt(modifyView.getProteinJText());
                if(!modifyView.getFatJText().isBlank())
                    fat=Integer.parseInt(modifyView.getFatJText());
                if(!modifyView.getSodiumJText().isBlank())
                    sodium=Integer.parseInt(modifyView.getSodiumJText());
                if(!modifyView.getPriceJText().isBlank())
                    price=Integer.parseInt(modifyView.getPriceJText());

                try {
                    deliveryService.modifyProduct(nameOld,newName,rating,calories,protein,fat,sodium,price);
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
        }
    }

    public class PlaceOrderAddListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(orderView.getPlaceOrderButton()==e.getSource()){
                int[] rows=productTable.getProdJTable().getSelectedRows();
                Set<MenuItem> products= new HashSet<>();
                for(int q:rows){
                    try {
                        if (deliveryService.isComposite((String) productTable.getProdJTable().getValueAt(q, 0))) {
                                for (MenuItem m : deliveryService.getSer().deserializeP()) {
                                    if (m.getTitle().equals(productTable.getProdJTable().getValueAt(q, 0))) {
                                        MenuItem c = new CompositeProduct((String) productTable.getProdJTable().getValueAt(q, 0), ((CompositeProduct) m).getCompProd());
                                        products.add(c);
                                    }
                                }

                        } else {
                            MenuItem bs = new BaseProduct((String) productTable.getProdJTable().getValueAt(q, 0),
                                    Double.parseDouble((String) productTable.getProdJTable().getValueAt(q, 1)), Integer.parseInt((String) productTable.getProdJTable().getValueAt(q, 2)),
                                    Integer.parseInt((String) productTable.getProdJTable().getValueAt(q, 3)), Integer.parseInt((String) productTable.getProdJTable().getValueAt(q, 4)),
                                    Integer.parseInt((String) productTable.getProdJTable().getValueAt(q, 5)), Integer.parseInt((String) productTable.getProdJTable().getValueAt(q, 6)));
                            products.add(bs);
                        }
                    } catch (IOException | ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
                try {
                    deliveryService.createOrder(products,Integer.parseInt(orderView.getCustomerID()));
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                JOptionPane.showMessageDialog(null,"Order Placed");
            }
        }
    }

    public class ReportAddListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(raport1View.getGenerateButton()==e.getSource()){
                try {
                    deliveryService.generateReport1(raport1View.getStartHourjText(),raport1View.getEndHourJText());
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
            if(raport2View.getGenerateButton()==e.getSource()){
                try {
                    deliveryService.generateReport2(Integer.parseInt(raport2View.getNrJText()));
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
            if(raport3View.getGenerateButton()==e.getSource()){
                try {
                    deliveryService.generateReport3(Integer.parseInt(raport3View.getNrtimesjText()),Integer.parseInt(raport3View.getAmountJText()));
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
            if(raport4View.getGenerateButton()==e.getSource()){
                try {
                    deliveryService.generateReport4(Integer.parseInt(raport4View.getDayJText()));
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }

        }
    }

}
