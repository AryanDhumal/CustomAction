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
        // 🔥 NO "data" anymore
        String name = request.get("name") != null ? request.get("name").toString() : "Valued Customer";
        String plan = request.get("plan") != null ? request.get("plan").toString() : "";
        String city = request.get("city") != null ? request.get("city").toString() : "";

        int age = 0;
        Object ageObj = request.get("age");

        if (ageObj instanceof Number) {
            age = ((Number) ageObj).intValue();
        } else if (ageObj instanceof String) {
            age = Integer.parseInt((String) ageObj);
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

        // 🔥 fallback
        response.put("name", "Valued Customer");
        response.put("offer", "Standard Benefits");
        response.put("cityMessage", "Check out our latest offers!");
    }

    return response;
}
}
