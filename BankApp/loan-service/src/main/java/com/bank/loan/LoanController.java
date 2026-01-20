@RestController
@RequestMapping("/loans")
public class LoanController {
    private final LoanRepository repo;
    private final NotificationServiceClient notificationClient;

    public LoanController(LoanRepository repo, NotificationServiceClient notificationClient) {
        this.repo = repo;
        this.notificationClient = notificationClient;
    }

    @PostMapping
    public Loan applyLoan(@RequestBody Loan loan) {
        Loan savedLoan = repo.save(loan);

        // Redirect to Notification Service
        notificationClient.sendNotification(
            "Loan approved for customer: " + loan.getCustomerId()
        );
        return savedLoan;
    }
}

