package hg.std.shequ.controller;

import hg.std.shequ.mapper.UserMapper;
import hg.std.shequ.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")//当访问首页的时候循环的看所有cookie，
    public String index(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")){//找到cookie=token的cookie
                String token = cookie.getValue();
                User user = userMapper.findByToken(token);//在数据库查是否有cookie这个记录
                if (user != null){
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }
        return "index";
    }
}
