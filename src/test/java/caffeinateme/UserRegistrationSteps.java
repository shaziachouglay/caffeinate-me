package caffeinateme;

import caffeinateme.steps.Customer;
import caffeinateme.steps.UserRegistrationClient;
import io.cucumber.java.en.Given;
import net.thucydides.core.annotations.Steps;

public class UserRegistrationSteps {
    @Steps(shared = true)
    Customer customer;
    @Steps
    UserRegistrationClient userRegistration;

    @Given("^(.*) has a Caffeinate-Me account")
    public void cathyHasACaffeinateMeAccount(String userName) {
        userRegistration.registerUsers(customer);
        customer.isCalled(userName);
    }
}
