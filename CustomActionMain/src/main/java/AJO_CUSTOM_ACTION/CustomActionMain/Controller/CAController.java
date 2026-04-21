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

        Map<String, Object> data = (Map<String, Object>) request.get("data");

        String name = (String) data.get("name");
        String plan = (String) data.get("plan");
        String city = (String) data.get("city");

        // safer cast for numbers
        int age = ((Number) data.get("age")).intValue();

        String offer = "Standard Benefits";

        if ("C&S".equalsIgnoreCase(plan)) {
            offer = "Premium Upgrade 🚀";
        }

        if (age < 25) {
            offer += " + Youth Discount 🎯";
        }

        String cityMsg = "";
        if ("Pune".equalsIgnoreCase(city)) {
            cityMsg = "Special Pune Offer 💥";
        }

        // ✅ return JSON instead of HTML
        Map<String, Object> response = new HashMap<>();
        response.put("name", name);
        response.put("offer", offer);
        response.put("cityMessage", cityMsg);

        return response;
    }
}
