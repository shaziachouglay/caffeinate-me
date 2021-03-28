package caffeinateme.steps;

import net.thucydides.core.annotations.Steps;

import java.util.ArrayList;
import java.util.List;

public class CoffeeOrderClient {
    @Steps(shared = true)
    ProductCatalog productCatalog;
    List<Order> orders = new ArrayList<>();

    public OrderReceipt placeOrder(long customerId, int quantity, String product) {
        Order order = new Order(customerId,quantity,product);
        orders.add(order);
        return order.getReceipt();
    }

    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    public void updatesCustomerETA(long customerId, int minutesAway) {
        orders.stream().filter(order -> order.getCustomerId() == customerId)
                .forEach(order -> order.updateETATo(minutesAway));
    }
}
