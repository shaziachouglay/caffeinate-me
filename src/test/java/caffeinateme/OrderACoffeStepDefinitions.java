package caffeinateme;

import caffeinateme.steps.Barista;
import caffeinateme.steps.Customer;
import caffeinateme.steps.Order;
import caffeinateme.steps.OrderReceipt;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;

import static org.assertj.core.api.Assertions.assertThat;


public class OrderACoffeStepDefinitions {
    @Steps(shared = true)
    Customer customer;

    @Steps
    Barista barry;
    OrderReceipt orderReceipt;

    @When("^(?:.*) (?:orders|has ordered) an? (.*)$")
    public void sheOrdersA(String order) {
     orderReceipt = customer.placesAnOrderFor(1,order);
        Serenity.setSessionVariable("orderReceipt").to(orderReceipt);
    }

    @Then("^Barry should recieve the order$")
    public void barryShouldRecieveTheOrder() {
        assertThat(barry.pendingOrders()).contains(Order.matching(orderReceipt));
    }
}
