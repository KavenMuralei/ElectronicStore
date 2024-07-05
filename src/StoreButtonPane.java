import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class StoreButtonPane extends Pane {
    private final Button  resetStore;
    private final Button addToCart;
    private final Button removeFromCart;
    private final Button completeSale;

    public Button getResetStore() {
        return resetStore;
    }

    public Button getAddToCart() {
        return addToCart;
    }

    public Button getCompleteSale() {
        return completeSale;
    }

    public Button getRemoveFromCart() {
        return removeFromCart;
    }

    public StoreButtonPane() {
        Pane innerPane = new Pane();

        // Create the buttons
        resetStore = new Button("Reset Store");
        resetStore.relocate(30, 0);
        resetStore.setPrefSize(100,40);

        addToCart = new Button("Add to cart");
        addToCart.relocate(250, 0);
        addToCart.setPrefSize(100,40);

        removeFromCart = new Button("Remove from Cart");
        removeFromCart.relocate(470, 0);
        removeFromCart.setPrefSize(140,40);

        completeSale = new Button("Complete sale");
        completeSale.relocate(610, 0);
        completeSale.setPrefSize(140,40);

        // Add all three buttons to the pane
        innerPane.getChildren().addAll(resetStore, addToCart, removeFromCart,completeSale);

        getChildren().addAll(innerPane);//, titleLabel);
    }
}

