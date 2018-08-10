package markd315.io.configuration;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Home redirection to io api documentation
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String index() {
        System.out.println("io-ui.html");
        return "redirect:io-ui.html";
    }


}
