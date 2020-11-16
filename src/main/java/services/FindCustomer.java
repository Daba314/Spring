/***
 * @author Daba Dashiev
 * This class represent specific method for customer
 */
package services;
import com.example.demo.exception.NotFoundException;
import com.example.demo.jpa.CustomerRepos;
import com.example.demo.models.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FindCustomer {
    @Autowired
    private CustomerRepos customerRepos;

    /***
     * Get customer with chosen first and last name
     * @param firstName customer first name
     * @param lastName customer last name
     * @return customer with selected first and last name
     */
    @GetMapping("customers/{firstName}/{lastName}")
    public CustomerEntity getCustomerByFirstAndLastName(@PathVariable String firstName, @PathVariable String lastName) {
        CustomerEntity customerEntity = customerRepos.findCustomerEntityByFirstnameAndLastname(firstName,lastName);
        if(customerRepos.existsByFirstnameAndLastname(firstName,lastName)) {
            return customerEntity;
        }else {
            throw new NotFoundException("Customer not found with firstName " + firstName + "and lastName" +lastName);
        }
    }
}
