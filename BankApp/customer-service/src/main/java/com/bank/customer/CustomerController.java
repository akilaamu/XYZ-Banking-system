@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerRepository repo;

    public CustomerController(CustomerRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return repo.findAll();
    }

    @PostMapping
    public Customer addCustomer(@RequestBody Customer customer) {
        return repo.save(customer);
    }
}

