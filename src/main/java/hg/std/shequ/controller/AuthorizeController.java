package hg.std.shequ.controller;

import hg.std.shequ.dto.AccessTokendDTO;
import hg.std.shequ.dto.GithubUser;
import hg.std.shequ.mapper.UserMapper;
import hg.std.shequ.model.User;
import hg.std.shequ.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class AuthorizeController {

    //从配置文件中选取value的值
    @Value("${github.client.id}")
    private String clienId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirect_uri;

    @Autowired
    private GitHubProvider gitHubProvider;

    @Autowired(required = false)
    private UserMapper userMapper;

    @GetMapping("/callback")
    public  String callback(@RequestParam(name="code") String code,
                            @RequestParam(name="state") String state,
                            HttpServletRequest request) {
        AccessTokendDTO accessTokendDTO = new AccessTokendDTO();
        accessTokendDTO.setClient_id(clienId);
        accessTokendDTO.setClinet_sectet(clientSecret);
        accessTokendDTO.setCode(code);
        accessTokendDTO.setRedirect_uri(redirect_uri);
        accessTokendDTO.setState(state);
        String accessToken = gitHubProvider.getAccessToken(accessTokendDTO);
        GithubUser githubUser = gitHubProvider.getUser(accessToken);
        if (githubUser != null) {
            //登录成功，写cookie和session
            User user = new User();
            user.setToken(UUID.randomUUID().toString());
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
            request.getSession().setAttribute("user", githubUser);
            return "redirect:/";
        } else {
            return "redirect:/";
        }
    }
}
