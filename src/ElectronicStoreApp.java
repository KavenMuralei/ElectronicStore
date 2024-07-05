import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Map;
import java.util.Objects;

public class ElectronicStoreApp extends Application {
    ElectronicStore model;
    ElectronicStoreView view;
    public void start(Stage primaryStage){
        model = ElectronicStore.createStore();
        view = new ElectronicStoreView(model);
        primaryStage.setTitle("Electronic Store Application - "+model.getName());
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(view, 800,400));
        primaryStage.show();

        //Starting values
        ObservableList<Product> stocks = FXCollections.observableArrayList(model.getStock());
        view.getStockList().setItems(stocks);
        ObservableList<Product> topProducts = FXCollections.observableArrayList(model.getStock().subList(0,3));
        view.getPopularList().setItems(topProducts);
        view.getSalesField().setText("0");
        view.getSaleField().setText("0.00");
        view.getRevenueField().setText("N/A");

        view.getStockList().setOnMouseReleased(e -> {
            view.getButtonPane().getAddToCart().setDisable(false);
            view.getButtonPane().getRemoveFromCart().setDisable(true);
        });
        view.getCartList().setOnMouseReleased(e -> {
            view.getButtonPane().getRemoveFromCart().setDisable(false);
            view.getButtonPane().getAddToCart().setDisable(true);
        });

        view.getButtonPane().getAddToCart().setOnAction(actionEvent -> {
            Product clickedStock = view.getStockList().getSelectionModel().getSelectedItem();
            if (clickedStock != null) {
                model.addToCart(clickedStock);
                model.removeStock(clickedStock);
                view.getButtonPane().getCompleteSale().setDisable(false);
                view.update();
            }
        });
        view.getButtonPane().getRemoveFromCart().setOnAction(actionEvent -> {
            String clickedStock = view.getCartList().getSelectionModel().getSelectedItem();
            if(clickedStock!=null){
                String newClickedStock = clickedStock.substring(clickedStock.indexOf("x") + 2);
                Product productToRemove = null;
                for (Product key : model.getCart().keySet()) {
                    if (Objects.equals(key.toString(), newClickedStock)){
                        productToRemove=key;
                        break;
                    }
                }
                model.removeCart(productToRemove);
                if (!model.getStock().contains(productToRemove)){
                    assert productToRemove != null;
                    productToRemove.increaseStock(1);
                }
                model.addProduct(productToRemove);

                if (model.getCart().isEmpty()){
                    view.getButtonPane().getCompleteSale().setDisable(true);
                    view.getButtonPane().getRemoveFromCart().setDisable(true);
                }
                view.update();
            }
        });
        view.getButtonPane().getCompleteSale().setOnAction(actionEvent -> {
            //sells the units
            view.increaseSales();
            for (Map.Entry<Product, Integer> entry : model.getCart().entrySet()) {
                Product product = entry.getKey();
                Integer quantity = entry.getValue();
                product.sellUnits(quantity);
            }
            model.increaseTotal(model.getCartTotal());
            view.getSalesField().setText(String.valueOf(view.getSales()));
            view.getRevenueField().setText(String.valueOf(model.getTotal()));
            view.getSaleField().setText(String.valueOf(model.getTotal()/view.getSales()));
            model.getCart().clear();
            view.getButtonPane().getCompleteSale().setDisable(true);
            view.update();
        });

        view.getButtonPane().getResetStore().setOnAction(actionEvent -> {
            primaryStage.close();
            Stage newStage = new Stage();
            start(newStage);
        });

    }
    public static void main(String[] args) {
        launch(args);
    }
}
