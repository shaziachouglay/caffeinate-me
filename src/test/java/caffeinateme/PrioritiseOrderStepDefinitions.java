package caffeinateme;

import caffeinateme.steps.Barista;
import caffeinateme.steps.Customer;
import caffeinateme.steps.Order;
import caffeinateme.steps.OrderReceipt;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class PrioritiseOrderStepDefinitions {

    @Steps(shared = true)
    Customer customer;

    @Steps(shared = true)
    Barista barry;

    @And("^Sarah is (\\d+) minutes away from the shop")
    public void sarahIsETAMinutesAwayFromTheShop(int minutesAway) {
        customer.updatesHerETATo(minutesAway);
    }
    List<Order> pendingOrders;
    @When("^Barry reviews the pending orders$")
    public void barryReviewsThePendingOrders() {
        pendingOrders = barry.pendingOrders();
    }

    @Then("^Sarah's order should have an urgency of (.*)$")
    public void sarahSOrderShouldHaveAnUrgencyOfUrgency(Urgency urgency) {
        Optional<Order> sarahsOrder = sarahsOrderIn(pendingOrders);

        assertThat(sarahsOrder).isPresent();

        assertThat(sarahsOrder.get().getUrgency()).isEqualTo(urgency);

    }

    private Optional<Order> sarahsOrderIn(List<Order> pendingOrders) {
        OrderReceipt orderReceipt = Serenity.sessionVariableCalled("orderReceipt");
        return pendingOrders.stream()
                .filter(order -> order.equals(Order.matching(orderReceipt)))
                .findFirst();
    }
}
