package BuisinessLogic;

import java.io.IOException;
import java.util.Set;

public interface IDeliveryServiceProcessing {

    /**
     * Importa setul de produse
     * @pre products.csv exista
     * @post setul de produse importat din products.csv
     */
    void importProducts() throws IOException, ClassNotFoundException;

    /**
     * Adauga un nou produs in meniu
     * @param menuItem produsul care trebuie adaugat
     * @pre menuItem != null
     * @post setul de produse contine menuItem
     */
    void addProduct(MenuItem menuItem) throws IOException, ClassNotFoundException;

    /**
     * Sterge un produs din meniu
     * @param title titlul produsului care trebuie sters
     * @pre title != null
     * @post setul de produse nu mai contine produsul cu titlul dat
     */
    void deleteProduct(String title) throws IOException, ClassNotFoundException;

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
    void modifyProduct(String currentTitle, String newTitle, double rating, int calories, int protein,int fat, int sodium, int price) throws IOException, ClassNotFoundException;

    /**
     * Comenzile plasate intr-un anumit interval de timp
     * @param startHour ora minima din intervalul orar
     * @param endHour ora maxima din intervalul orar
     * @pre startHour != null, endHour != null
     * @post comenzile plasate in intervalul orar dat
     */
    void generateReport1(String startHour, String endHour) throws IOException, ClassNotFoundException;

    /**
     * Produsele comandate de mai multe ori decat numarul specificat
     * @param specifiedNumberOfTimes numarul minim de ori
     * @pre specifiedNumberOfTimes > 0
     * @post produsele comandate de mai multe ori decat specifiedNumberOfTimes
     */
    void generateReport2(int specifiedNumberOfTimes) throws IOException, ClassNotFoundException;

    /**
     * Clientii care au comandat de mai multe ori decat numarul specificat si comanda carora a fost mai mare decat suma specificata
     * @param specifiedNumberOfTimes numarul de ori
     * @param specifiedAmount suma minima
     * @pre specifiedNumberOfTimes > 0, specifiedAmount > 0
     * @post clientii care respecta criteriile
     */
    void generateReport3(int specifiedNumberOfTimes, int specifiedAmount) throws IOException, ClassNotFoundException;

    /**
     * Produsele care au fost comandate intr-o anumita zi impreuna cu frecventa lor
     * @param specifiedDay ziua
     * @pre specifiedDay != 0
     * @post produsele care respecta criteriile
     */
    void generateReport4(int specifiedDay) throws IOException, ClassNotFoundException;

    // Customer

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
    Set<MenuItem> search(String title, double rating, int calories, int protein, int fat, int sodium, int price) throws IOException, ClassNotFoundException;

    /**
     * Crearea unei comenzi
     * @param products produsele dorite de client
     * @param clientID ID-ul clientului
     * @pre products != null, clientID > 0
     * @post comanda plasata
     */
    void createOrder(Set<MenuItem> products,int clientID) throws IOException, ClassNotFoundException;
}

