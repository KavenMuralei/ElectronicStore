import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.util.*;
import java.util.stream.Collectors;

public class ElectronicStoreView extends Pane {
    //List views
    private final ListView<Product> stockList;
    private final ListView<String> cartList;
    private final ListView<Product> popularList;
    //text fields
    private final TextField salesField;
    private final TextField revenueField;
    private final TextField saleField;
    private final StoreButtonPane buttonPane;
    private int sales=0;

    ElectronicStore model;
    private final Label label3;
    //get methods

    public int getSales() {
        return sales;
    }



    public ListView<String> getCartList() {
        return cartList;
    }

    public ListView<Product> getStockList() {
        return stockList;
    }
    public ListView<Product> getPopularList() {
        return popularList;
    }

    public TextField getRevenueField() {
        return revenueField;
    }

    public TextField getSaleField() {
        return saleField;
    }
    public TextField getSalesField() {
        return salesField;
    }

    public StoreButtonPane getButtonPane() {
        return buttonPane;
    }
    //other
    public void increaseSales(){
        sales+=1;
    }


    public ElectronicStoreView(ElectronicStore initModel){
        model = initModel;
        // Create the labels
        Label label1 = new Label("Store Summary:");
        label1.relocate(50,20);

        Label label2 = new Label("Store Stock:");
        label2.relocate(280,20);

        label3 = new Label("Current Cart ($0.00):");
        label3.relocate(580,20);

        Label label4 = new Label("# Sales:");
        label4.relocate(20,50);

        Label label5 = new Label("Revenue:");
        label5.relocate(10,85);

        Label label6 = new Label("$ /Sales:");
        label6.relocate(15,120);

        Label label7 = new Label("Most Popular Items:");
        label7.relocate(35,150);
        // Create the TextFields
        salesField = new TextField();
        salesField.relocate(60, 45);
        salesField.setPrefSize(100,30);

        revenueField = new TextField();
        revenueField.relocate(60, 80);
        revenueField.setPrefSize(100,30);

        saleField = new TextField();
        saleField.relocate(60, 115);
        saleField.setPrefSize(100,30);

        // Create the lists
        popularList = new ListView<>();
        popularList.relocate(10, 175);
        popularList.setPrefSize(150,150);

        stockList = new ListView<>();
        stockList.relocate(170, 45);
        stockList.setPrefSize(280,280);

        cartList = new ListView<>();
        cartList.relocate(470, 45);
        cartList.setPrefSize(280,280);
        // Create the buttons
        buttonPane = new StoreButtonPane();
        buttonPane.relocate(0, 330);
        buttonPane.setPrefSize(800,60);

        buttonPane.getAddToCart().setDisable(true);
        buttonPane.getRemoveFromCart().setDisable(true);
        buttonPane.getCompleteSale().setDisable(true);

        // Add all the components to the window
        getChildren().addAll(label1,label2,label3,label4,label5,label6,label7,popularList,stockList,cartList,salesField,revenueField,saleField,buttonPane);
    }
    public void update(){
        //updates the stock
        ObservableList<Product> stocks = FXCollections.observableArrayList(model.getStock());
        stockList.setItems(stocks);
        //Updates the cart
        cartList.setItems(model.visualCreator());
        label3.setText("Current Cart ($" + model.getCartTotal() + "):");
        //updates the top 3 products
        List<Product> top3Products = model.getStock().stream().sorted(Comparator.comparing(Product::getSoldQuantity).reversed()).limit(3).collect(Collectors.toList());
        ObservableList<Product> topProducts = FXCollections.observableArrayList(top3Products);
        popularList.setItems(topProducts);
    }







}
