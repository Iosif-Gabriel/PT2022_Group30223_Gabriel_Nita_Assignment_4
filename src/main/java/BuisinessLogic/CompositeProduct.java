package BuisinessLogic;

import java.util.ArrayList;

public class CompositeProduct extends MenuItem{
    private ArrayList<BaseProduct> compProd=new ArrayList<>();

    public CompositeProduct(String name, ArrayList<BaseProduct> prod){
        this.setTitle(name);
        double rating=0;
        int calor=0,prot=0,fat=0,sodium=0;
        for(BaseProduct p:prod){
            this.compProd.add(p);
            rating=rating+p.getRating();
            calor=calor+p.getCalories();
            prot=prot+p.getProtein();
            fat=fat+p.getFat();
            sodium=sodium+getSodium();
        }
        this.setPrice(this.priceall());
        this.setCalories(calor);
        this.setProtein(prot);
        this.setFat(fat);
        this.setSodium(sodium);
        this.setRating(rating/prod.size());
    }

    @Override
    public int priceall() {
        int price=0;
        for(BaseProduct p:compProd){
            price=price+p.getPrice();
        }
        return price;
    }

    public ArrayList<BaseProduct> getCompProd() {
        return compProd;
    }

    public void setCompProd(ArrayList<BaseProduct> compProd) {
        this.compProd = compProd;
    }

    public String toString(){
        StringBuilder listprod= new StringBuilder("Composite product:" + this.getTitle() + "composed of" + this.compProd.size() + "products:\n");
        for(int i=0;i<compProd.size()-1;i++){
            listprod.append(i + 1).append(compProd.get(i)).append("\n");
        }
        listprod.append(compProd.size()).append(compProd.get(compProd.size() - 1));
        return listprod.toString();
    }
}
