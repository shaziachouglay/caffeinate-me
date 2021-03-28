package caffeinateme.steps;

import net.serenitybdd.core.steps.ScenarioActor;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

public class Customer extends ScenarioActor {
    @Steps(shared = true)
    CoffeeOrderClient coffeeOrders;
    private long customerId;
    public void hasACustomerId(long customerId) {
        this.customerId = customerId;
    }

    @Step("#actor places an order for {0} {1}")
    public OrderReceipt placesAnOrderFor(int quantity, String product) {
        return coffeeOrders.placeOrder(customerId,quantity,product);
    }

    public void updatesHerETATo(int minutesAway) {
        coffeeOrders.updatesCustomerETA(customerId,minutesAway);
    }
}
