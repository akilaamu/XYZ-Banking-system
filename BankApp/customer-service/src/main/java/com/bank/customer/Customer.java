@Entity
public class Customer {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private double balance;

    // getters & setters
}

