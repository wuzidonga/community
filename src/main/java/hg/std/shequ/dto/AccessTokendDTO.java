package hg.std.shequ.dto;


public class AccessTokendDTO {
    private String client_id;
    private String clinet_sectet;
    private String code;
    private String redirect_uri;
    private String state;

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClinet_sectet() {
        return clinet_sectet;
    }

    public void setClinet_sectet(String clinet_sectet) {
        this.clinet_sectet = clinet_sectet;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public void setRedirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
