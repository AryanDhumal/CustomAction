package AJO_CUSTOM_ACTION.CustomActionMain.Controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/GlobalHit")
public class CAController {

    @GetMapping
    public String getResponse() {
        return "I am Working Baby ... !";
    }

    @PostMapping("/Post")
    public String getResponse(@RequestParam String name,
                              @RequestParam String plan) {

        return "<h1>Hello " + name + "</h1>" +
               "<p>Your plan is <b>" + plan + "</b></p>";
    }

   @PostMapping("/personalize")
public Map<String, Object> personalize(@RequestBody Map<String, Object> request) {

    Map<String, Object> response = new HashMap<>();

    try {
        Map<String, Object> data = (Map<String, Object>) request.get("data");

        if (data == null) {
            throw new RuntimeException("Missing data object");
        }

        String name = data.get("name") != null ? data.get("name").toString() : "Valued Customer";
        String plan = data.get("plan") != null ? data.get("plan").toString() : "";
        String city = data.get("city") != null ? data.get("city").toString() : "";

        int age = 0;
        if (data.get("age") instanceof Number) {
            age = ((Number) data.get("age")).intValue();
        }

        String offer = "Standard Benefits";

        if ("C&S".equalsIgnoreCase(plan)) {
            offer = "Premium Upgrade 🚀";
        }

        if (age < 25 && age > 0) {
            offer += " + Youth Discount 🎯";
        }

        String cityMsg;
        if ("Pune".equalsIgnoreCase(city)) {
            cityMsg = "Special Pune Offer 💥";
        } else {
            cityMsg = "Special Offer for our valued customer";
        }

        response.put("name", name);
        response.put("offer", offer);
        response.put("cityMessage", cityMsg);

    } catch (Exception e) {
        // 🔥 fallback (never break AJO)
        response.put("name", "Valued Customer");
        response.put("offer", "Standard Benefits");
        response.put("cityMessage", "Check out our latest offers!");
    }

    return response;
}
}
