package hg.std.shequ.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PublishController {

    @GetMapping("/publish")
    public String publish(){
        return  "publish";
    }
    @PostMapping("/publish")
    public String doPublish(){
        return  "publish";
    }
}
