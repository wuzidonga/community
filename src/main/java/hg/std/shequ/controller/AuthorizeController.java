package hg.std.shequ.controller;

import hg.std.shequ.dto.AccessTokendDTO;
import hg.std.shequ.dto.GithubUser;
import hg.std.shequ.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/callback")
    public  String callback(@RequestParam(name="code") String code,
                            @RequestParam(name="state") String state){
        AccessTokendDTO accessTokendDTO = new AccessTokendDTO();
        accessTokendDTO.setClient_id(clienId);
        accessTokendDTO.setClinet_sectet(clientSecret);
        accessTokendDTO.setCode(code);
        accessTokendDTO.setRedirect_uri(redirect_uri);
        accessTokendDTO.setState(state);
        String accessToken = gitHubProvider.getAccessToken(accessTokendDTO);
        GithubUser githubUser = gitHubProvider.getUser(accessToken);
        System.out.println(githubUser.getName());
        return  "index";
    }
}
