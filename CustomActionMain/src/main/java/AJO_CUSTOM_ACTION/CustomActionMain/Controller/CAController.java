package AJO_CUSTOM_ACTION.CustomActionMain.Controller;


import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/GlobalHit")


public class CAController {
    @GetMapping
    public String getResponse(){
        return "I am Working Baby ... !";
    }

    @PostMapping("/Post")
    public String getResponse(@RequestParam String name,
                              @RequestParam String plan) {

        return "<h1>Hello " + name + "</h1>" +
                "<p>Your plan is <b>" + plan + "</b></p>";
    }

    @PostMapping("/personalize")
    public String personalize(@RequestBody Map<String, Object> request) {

        Map<String, Object> data = (Map<String, Object>) request.get("data");

        String name = (String) data.get("name");
        String plan = (String) data.get("plan");
        String city = (String) data.get("city");
        int age = (int) data.get("age");

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

        return "<h2>Hello " + name + "</h2>"
                + "<p>Offer: <b>" + offer + "</b></p>"
                + "<p>" + cityMsg + "</p>";
    }

}
