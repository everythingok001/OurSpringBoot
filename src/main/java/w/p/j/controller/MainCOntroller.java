package w.p.j.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class MainController extends BaseController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }


    @RequestMapping("/userInfo")
    public String userInfo(){
        return "user";
    }

}