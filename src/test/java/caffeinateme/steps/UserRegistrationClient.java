package caffeinateme.steps;

public class UserRegistrationClient {
    private long customerId = 1;
    public void registerUsers(Customer customer) {
        customer.hasACustomerId(customerId++);
    }
}
