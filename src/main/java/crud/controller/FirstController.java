package crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class FirstController {

    @GetMapping("login")
    public String login(Model model){
        return "login";
    }

    @GetMapping("/logout")
    public String Logout(){
        return "redirect:/login";
    }
}
