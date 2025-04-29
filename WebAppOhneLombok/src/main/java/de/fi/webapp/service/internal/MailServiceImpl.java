package de.fi.webapp.service.internal;

public class MailServiceImpl {

    private String smtp;
    private String username;
    private String password;

    public MailServiceImpl(final String smtp, final String username, final String password) {
        this.smtp = smtp;
        this.username = username;
        this.password = password;
    }

    public String getSmtp() {
        return smtp;
    }

    public void setSmtp(final String smtp) {
        this.smtp = smtp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void send(String message){
        System.out.println("Mail service endet :" + message + " an " + smtp);
    }
}
