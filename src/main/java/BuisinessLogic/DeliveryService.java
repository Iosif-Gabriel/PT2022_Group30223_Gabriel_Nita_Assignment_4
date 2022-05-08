package BuisinessLogic;

import DataLayer.Bill;
import DataLayer.Serializator;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DeliveryService extends Observable implements IDeliveryServiceProcessing {
    private Serializator ser=new Serializator();
    private Bill bill=new Bill();

    public boolean formed() throws IOException, ClassNotFoundException {
        if(ser.deserializeP().isEmpty())
            return false;
        return true;
    }

    /**
     * Importa setul de produse
     * @pre products.csv exista
     * @post setul de produse importat din products.csv
     */
    @Override
    public void importProducts() throws IOException, ClassNotFoundException {
        File file =new File("src/main/resources/products.csv");
        assert file.exists();
        Set<MenuItem> baseProds= ser.deserializeP();
        Set<MenuItem> distinctProd;
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/products.csv"))) {
            String CSV;
            reader.readLine();
            while ((CSV = reader.readLine()) != null) {
                String[] data = CSV.split(",");
                BaseProduct b = new BaseProduct(data[0].stripTrailing(), Double.parseDouble(data[1]), Integer.parseInt(data[2]), Integer.parseInt(data[3]),
                        Integer.parseInt(data[4]), Integer.parseInt(data[5]), Integer.parseInt(data[6]));
                baseProds.add(b);
            }
            distinctProd = baseProds.stream()
                    .filter(distinctByKey(baseProd -> baseProd.getTitle()))
                    .collect(Collectors.toSet());
            ser.serializeP(distinctProd);
            assert !distinctProd.isEmpty();
            assert formed();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adauga un nou produs in meniu
     * @param prod produsul care trebuie adaugat
     * @pre prod != null
     * @post setul de produse contine prod
     */
    @Override
    public void addProduct(MenuItem prod) throws IOException, ClassNotFoundException {
        assert prod!=null;
        Set<MenuItem> menuItemSet= ser.deserializeP();
        menuItemSet.add(prod);
        ser.serializeP(menuItemSet);
        assert menuItemSet.contains(prod);
        assert formed();

    }

    /**
     * Sterge un produs din meniu
     * @param title titlul produsului care trebuie sters
     * @pre title != null
     * @post setul de produse nu mai contine produsul cu titlul dat
     */
    @Override
    public void deleteProduct(String title) throws IOException, ClassNotFoundException {
        assert !title.isBlank();
        Set<MenuItem> menuItems= ser.deserializeP();
        MenuItem item=null;
        for(MenuItem item1:menuItems){
            if(item1.getTitle().equals(title)){
                menuItems.remove(item1);
                item=item1;
                break;
            }
        }
        ser.serializeP(menuItems);
        assert !menuItems.contains(item);
        assert formed();
    }

    /**
     * Modifica artibutele unui produs din meniu
     * @param currentTitle titlul produsului care trebuie modificat
     * @param newTitle noul titlu, in caz ca se doreste modificarea titlului
     * @param rating noul rating, in caz ca se doreste modificarea rating-ului
     * @param calories noile calorii, in caz ca se doreste modificarea caloriilor
     * @param protein noile proteine, in caz ca se doreste modificarea proteinelor
     * @param fat noile grasimi, in caz ca se doreste modificarea grasimilor
     * @param sodium noul sodiu, in caz ca se doreste modificarea sodiului
     * @param price noul pret, in caz ca se doreste modificarea pretului
     * @pre currentTitle != null
     * @post produsul modificat
     */
    @Override
    public void modifyProduct(String currentTitle, String newTitle, double rating, int calories, int protein, int fat, int sodium, int price) throws IOException, ClassNotFoundException {
        assert !currentTitle.isBlank();
        Set<MenuItem> itemSet= ser.deserializeP();
        MenuItem menuItem=null;
        int k=1;
        for(MenuItem tem:itemSet){
            if(tem.getTitle().equals(currentTitle)){
                menuItem=tem;
                if(!newTitle.equals("")){
                    for (MenuItem tem1:itemSet){
                        if(tem1.getTitle().equals(newTitle))
                            k=0;
                    }
                    if(k==1) tem.setTitle(newTitle);
                }
                if(rating!=-1) tem.setRating(rating);
                if(calories!=-1) tem.setCalories(calories);
                if(protein!=-1) tem.setProtein(protein);
                if(fat!=-1)tem.setFat(fat);
                if(sodium!=-1)tem.setSodium(sodium);
                if(price!=-1)tem.setPrice(price);
                for (MenuItem tem1:itemSet){
                    if(tem1 instanceof CompositeProduct){
                        for(BaseProduct base:((CompositeProduct)tem1).getCompProd())
                            if(base.getTitle().equals(currentTitle)){
                                base.setTitle(tem.getTitle());
                                base.setPrice(tem.getPrice());
                                base.setRating(tem.getRating());
                                base.setSodium(tem.getSodium());
                                base.setFat(tem.getFat());
                                base.setProtein(tem.getProtein());
                                base.setCalories(tem.getCalories());
                            }
                    }
                }
            }
        }
        ser.serializeP(itemSet);
        assert !itemSet.contains(menuItem);
        assert formed();
    }

    /**
     * Afiseaza comenzile plasate intr-un anumit interval de timp
     * @param startHour ora minima din intervalul orar
     * @param endHour ora maxima din intervalul orar
     * @pre startHour != null, endHour != null
     * @post comenzile plasate in intervalul orar dat
     */
    @Override
    public void generateReport1(String startHour, String endHour) throws IOException, ClassNotFoundException {
        assert !startHour.isBlank() || !endHour.isBlank();
        HashMap<Order, Set<MenuItem>> orderSetHashMap = ser.deserializeO();
        Set<Order> orders = orderSetHashMap.keySet();
        orders = orders.stream()
                .filter(order -> order.getOrderDate().getHours() > Integer.parseInt(startHour) && order.getOrderDate().getHours() < Integer.parseInt(endHour))
                .collect(Collectors.toCollection(HashSet::new));
        orders.stream().forEach(System.out::println);
        assert !orders.isEmpty();
        assert formed();
    }

    /**
     * Produsele comandate de mai multe ori decat numarul specificat
     * @param specifiedNumberOfTimes numarul minim de ori
     * @pre specifiedNumberOfTimes > 0
     * @post produsele comandate de mai multe ori decat specifiedNumberOfTimes
     */
    @Override
    public void generateReport2(int specifiedNumberOfTimes) throws IOException, ClassNotFoundException {
        assert specifiedNumberOfTimes > 0;
        HashMap<Order, Set<MenuItem>> orderHash = ser.deserializeO();
        Collection<Set<MenuItem>> set = orderHash.values();
        ArrayList<MenuItem> menuItems = new ArrayList<>();
        set.stream().forEach(s -> {
            s.stream().forEach(s1 -> {
                menuItems.add(s1);
            });
        });
        int[] ints = new int[menuItems.size()];
        for (int i = 0; i < menuItems.size(); i++) {
            ints[i] = Collections.frequency(menuItems, menuItems.get(i));
        }
        for (int i = 0; i < menuItems.size() - 1; i++) {
            for (int j = i + 1; j < menuItems.size(); j++) {
                if (menuItems.get(i).equals(menuItems.get(j))) {
                    ints[j] = 0;
                }
            }
        }
        for (int i = 0; i < ints.length; i++) {
            if (ints[i] >= specifiedNumberOfTimes) {
                System.out.println(menuItems.get(i));
            }
        }
        assert !menuItems.isEmpty();
        assert formed();
    }

    /**
     * Clientii care au comandat de mai multe ori decat numarul specificat si comanda carora a fost mai mare decat suma specificata
     * @param specifiedNumberOfTimes numarul de ori
     * @param specifiedAmount suma minima
     * @pre specifiedNumberOfTimes > 0, specifiedAmount > 0
     * @post clientii care respecta criteriile
     */
    @Override
    public void generateReport3(int specifiedNumberOfTimes, int specifiedAmount) throws IOException, ClassNotFoundException {
        assert specifiedNumberOfTimes > 0 || specifiedAmount > 0;
        HashMap<Order, Set<MenuItem>> orderHash = ser.deserializeO();
        Set<Order> ord = orderHash.keySet();
        ArrayList<Order> orders = new ArrayList<>();
        orders.addAll(ord);
        ArrayList<Integer> customersID = new ArrayList<>();
        orders.stream().forEach(o -> {
            customersID.add(o.getCustomerID());
        });
        int[] f = new int[customersID.size()];
        for (int i = 0; i < customersID.size(); i++) {
            f[i] = Collections.frequency(customersID, customersID.get(i));
        }
        for (int i = 0; i < customersID.size() - 1; i++) {
            for (int j = i + 1; j < customersID.size(); j++) {
                if (customersID.get(i).equals(customersID.get(j))) {
                    f[j] = 0;
                }
            }
        }
        for (int i = 0; i < f.length; i++) {
            if (f[i] >= specifiedNumberOfTimes) {
                for (Order o : orders) {
                    int price = 0;
                    if (o.getCustomerID() == orders.get(i).getCustomerID()) {
                        Set<MenuItem> o1 = orderHash.get(o);
                        for (MenuItem m : o1) {
                            price += m.priceall();
                        }
                        if (price >= specifiedAmount) {
                            System.out.println(o.getCustomerID() + " " + price);
                            break;
                        }
                    }
                }
            }
        }
        assert !customersID.isEmpty();
        assert formed();
    }


    /**
     * Produsele care au fost comandate intr-o anumita zi si frecventa acestora
     * @param specifiedDay ziua
     * @pre specifiedDay != 0
     * @post produsele care respecta criteriile
     */
    @Override
    public void generateReport4(int specifiedDay) throws IOException, ClassNotFoundException {
        assert specifiedDay > 0;
        HashMap<Order, Set<MenuItem>> orderHash = ser.deserializeO();
        Set<Order> orders = orderHash.keySet();
        ArrayList<MenuItem> itemArrayList = new ArrayList<>();
        orders = orders.stream()
                .filter(order -> order.getOrderDate().getDate() == specifiedDay)
                .collect(Collectors.toSet());
        orders.stream().forEach(o -> {
            itemArrayList.addAll(orderHash.get(o));
        });
        int[] ints = new int[itemArrayList.size()];
        for (int j = 0; j < itemArrayList.size(); j++) {
            ints[j] = Collections.frequency(itemArrayList, itemArrayList.get(j));
        }
        for (int i = 0; i < itemArrayList.size() - 1; i++) {
            for (int k = i + 1; k < itemArrayList.size(); k++) {
                if (itemArrayList.get(i).equals(itemArrayList.get(k))) {
                    ints[k] = 0;
                }
            }
        }
        for (int i = 0; i < ints.length; i++) {
            if (ints[i] != 0) {
                System.out.println(ints[i] + " " + itemArrayList.get(i));
            }
        }
        assert !itemArrayList.isEmpty();
        assert formed();
    }


    /**
     * Cautarea produselor din meniu dupa un anumite atribute
     * @param title titlul dupa care se cauta
     * @param rating rating-ul dupa care se cauta
     * @param calories caloriile dupa care se cauta
     * @param protein proteinele dupa care se cauta
     * @param fat grasimile dupa care se cauta
     * @param sodium sodiul dupa care se cauta
     * @param price pretul dupa care se cauta
     * @return setul de produse gasite
     * @pre unul din parametrii valid
     * @post setul de produse cautate
     */
    @Override
    public Set<MenuItem> search(String title, double rating, int calories, int protein, int fat, int sodium, int price) throws IOException, ClassNotFoundException {
        assert !title.isBlank() || rating!=-1 || calories!=-1 || protein!=-1 || fat!=-1 || sodium!=-1 || price!=-1;
        Set<MenuItem> menuItemSet=ser.deserializeP();
        if(!title.isBlank()){
            menuItemSet= menuItemSet.stream()
                    .filter(menuItem -> menuItem.getTitle().toLowerCase().contains(title.toLowerCase()))
                    .collect(Collectors.toCollection(HashSet::new));
        }
        if(rating!=-1){
            menuItemSet= menuItemSet.stream()
                    .filter(menuItem -> menuItem.getRating() == rating)
                    .collect(Collectors.toCollection(HashSet::new));
        }
        if(calories!=-1){
            menuItemSet= menuItemSet.stream()
                    .filter(menuItem -> menuItem.getCalories()==calories)
                    .collect(Collectors.toCollection(HashSet::new));
        }
        if(protein!=-1){
            menuItemSet= menuItemSet.stream()
                    .filter(menuItem -> menuItem.getProtein() == protein)
                    .collect(Collectors.toCollection(HashSet::new));
        }
        if(fat!=-1){
            menuItemSet= menuItemSet.stream()
                    .filter(menuItem -> menuItem.getFat()==fat)
                    .collect(Collectors.toCollection(HashSet::new));
        }
        if(sodium!=-1){
            menuItemSet= menuItemSet.stream()
                    .filter(menuItem -> menuItem.getSodium()==sodium)
                    .collect(Collectors.toCollection(HashSet::new));
        }
        if(price!=-1){
            menuItemSet= menuItemSet.stream()
                    .filter(menuItem -> menuItem.getPrice()==price)
                    .collect(Collectors.toCollection(HashSet::new));
        }

        assert !menuItemSet.isEmpty();
        assert formed();
        return menuItemSet;
    }

    /**
     * Creeaza o comanda din produsele selectate
     * @param products produsele dorite de client
     * @param clientID ID-ul clientului
     * @pre products != null, clientID > 0
     * @post comanda plasata
     */
    @Override
    public void createOrder(Set<MenuItem> products, int clientID) throws IOException, ClassNotFoundException {
        assert !products.isEmpty() || clientID >0;
        HashMap<Order, Set<MenuItem>> customerOrder=new HashMap<>();
        try{
           customerOrder= ser.deserializeO();
        }catch (EOFException e){
            if(customerOrder.isEmpty()){
                customerOrder=new HashMap<>();
            }
        }
        Order order=new Order(clientID);
        customerOrder.put(order,products);
        bill.createBill(customerOrder,order);
        ser.serializeO(customerOrder);
        setChanged();
        notifyObservers(order.toString());
        assert !customerOrder.containsKey(order);
        assert formed();
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> n = new ConcurrentHashMap<>();
        return t -> n.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    public boolean isComposite(String name) throws IOException, ClassNotFoundException {
        for(MenuItem menuItem:ser.deserializeP()){
            if(menuItem.getTitle().equals(name)){
                if(menuItem instanceof CompositeProduct)
                    return true;
            }
        }
        return false;
    }


    public String[][] getProductsForTableFromSet(Set<MenuItem> originalList) {
        String[][] data = new String[originalList.size()][7];
        ArrayList<MenuItem> prd = new ArrayList<>();
        prd.addAll(originalList);
        return getForTables(data, prd);
    }

    private String[][] getForTables(String[][] data, ArrayList<MenuItem> prod) {
        for (int j = 0; j < prod.size(); j++) {
            data[j][0] = prod.get(j).getTitle();
            data[j][1] = String.valueOf(prod.get(j).getRating());
            data[j][2] = String.valueOf(prod.get(j).getCalories());
            data[j][3] = String.valueOf(prod.get(j).getProtein());
            data[j][4] = String.valueOf(prod.get(j).getFat());
            data[j][5] = String.valueOf(prod.get(j).getSodium());
            data[j][6] = String.valueOf(prod.get(j).getPrice());
        }
        //System.out.println(prod.size());
        return data;
    }

    public String[][] getProductsForTable() throws IOException, ClassNotFoundException {
        String[][] data = new String[ser.deserializeP().size()][7];
        ArrayList<MenuItem> prd = new ArrayList<>();
        prd.addAll(ser.deserializeP());
        return getForTables(data, prd);
    }

    public Serializator getSer() {
        return ser;
    }
}
