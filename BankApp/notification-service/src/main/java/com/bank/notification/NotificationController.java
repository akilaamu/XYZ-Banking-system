@RestController
@RequestMapping("/notify")
public class NotificationController {
    @PostMapping
    public String send(@RequestBody String message) {
        System.out.println("Notification: " + message);
        return "Sent";
    }
}

