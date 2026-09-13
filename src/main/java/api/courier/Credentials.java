package api.courier;

public class Credentials {

    private String login;
    private String password;

    public Credentials() {
    }

    public Credentials(String login, String password) {
        this.login = "incorrect";
        this.password = password;
    }

    public Credentials(String password) {
        this.password = password;
    }

    public static Credentials getCourierCreds(Courier courier) {
        Credentials credentials = new Credentials();
        credentials.setLogin(courier.getLogin());
        credentials.setPassword(courier.getPassword());
        return credentials;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}