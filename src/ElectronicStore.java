//Class representing an electronic store
//Has an array of products that represent the items the store can sell

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;

public class ElectronicStore {
    private final String name;
    private final List<Product> stock;
    private final HashMap<Product,Integer> cart;
    private double total=0;
    private double cartTotal =0;

    public ElectronicStore(String initName) {
        name = initName;
        stock = new ArrayList<>();
        cart = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    //Adds a product and returns true if there is space in the array
    //Returns false otherwise
    public void addProduct(Product newProduct){
        if(stock.contains(newProduct)){
            newProduct.increaseStock(1);
        }
        else{
            stock.add(newProduct);
        }
    }
    public void removeStock(Product p){
        if(stock.contains(p) && p.getStockQuantity()>0){
            p.decreaseStock();
        }
        if (p!=null && p.getStockQuantity()==0){
            stock.remove(p);
        }
    }
    public void addToCart(Product p){
        if(cart.containsKey(p) && p!=null){
            cart.put(p, cart.get(p) + 1);
        }
        else if(p!=null){
            cart.put(p,1);
        }

    }
    public void removeCart(Product p){
        if (cart.containsKey(p)){
            cart.put(p, cart.get(p) - 1);
            if(cart.get(p)==0){
                cart.remove(p);
            }
        }
    }
    public List<Product> getStock(){
        return stock;
    }

    public HashMap<Product,Integer> getCart() {
        return cart;
    }
    public double getTotal() {
        return total;
    }

    public void increaseTotal(double amount){
        total+=amount;
    }

    public double getCartTotal() {
        return cartTotal;
    }

    public ObservableList<String> visualCreator(){ //creates the string visual (# x product)
        ObservableList<String> cart = FXCollections.observableArrayList();
        cartTotal = 0;
        for (Map.Entry<Product, Integer> entry : getCart().entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();
            if (product != null && product.toString()!=null) {
                cart.add(quantity + " x " + product);
                cartTotal+= product.getPrice() * quantity;
            }
        }

        return cart;
    }


    public static ElectronicStore createStore() {
        ElectronicStore store1 = new ElectronicStore("Watts Up Electronics");
        Desktop d1 = new Desktop(100, 10, 3.0, 16, false, 250, "Compact");
        Desktop d2 = new Desktop(200, 10, 4.0, 32, true, 500, "Server");
        Laptop l1 = new Laptop(150, 10, 2.5, 16, true, 250, 15);
        Laptop l2 = new Laptop(250, 10, 3.5, 24, true, 500, 16);
        Fridge f1 = new Fridge(500, 10, 250, "White", "Sub Zero", false);
        Fridge f2 = new Fridge(750, 10, 125, "Stainless Steel", "Sub Zero", true);
        ToasterOven t1 = new ToasterOven(25, 10, 50, "Black", "Danby", false);
        ToasterOven t2 = new ToasterOven(75, 10, 50, "Silver", "Toasty", true);
        store1.addProduct(d1);
        store1.addProduct(d2);
        store1.addProduct(l1);
        store1.addProduct(l2);
        store1.addProduct(f1);
        store1.addProduct(f2);
        store1.addProduct(t1);
        store1.addProduct(t2);
        return store1;
    }
} 