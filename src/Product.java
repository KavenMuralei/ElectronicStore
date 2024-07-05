//Base class for all products the store will sell
public abstract class Product {
    private final double price;
    private int stockQuantity;
    private int soldQuantity;

    public Product(double initPrice, int initQuantity) {
        price = initPrice;
        stockQuantity = initQuantity;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public int getSoldQuantity() {
        return soldQuantity;
    }

    public double getPrice() {
        return price;
    }

    //Returns the total revenue (price * amount) if there are at least amount items in stock
    //Return 0 otherwise (i.e., there is no sale completed)
    public void sellUnits(int amount) {
        if (amount > 0 && stockQuantity+amount >= amount) {
            stockQuantity -= amount;
            soldQuantity += amount;
            System.out.println("SALE WENT THROUGH! " +soldQuantity);
            return;
        }
        System.out.println("SALE DID NOT GO THROUGH!");
    }
    public void increaseStock(int amount){
        stockQuantity+=amount;
    }
    public void decreaseStock(){
        stockQuantity-=1;
    }

}