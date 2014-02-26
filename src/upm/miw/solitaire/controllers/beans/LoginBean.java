package upm.miw.solitaire.controllers.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import upm.miw.solitaire.controllers.ejb.LoginEjb;
import upm.miw.solitaire.models.entities.Gender;
import upm.miw.solitaire.models.entities.User;

@ManagedBean
@SessionScoped
public class LoginBean {

    private String password;

    private User user;

    private boolean logged = false;

    public LoginBean() {
        this.user = new User();
    }

    public Gender[] getGenders() {
        return Gender.values();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isLogged() {
        return logged;
    }

    public boolean isAdministrator() {
        return this.user.isAdministrator();
    }

    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        LoginEjb eaE = new LoginEjb();
        String result = "login";

        User usr = eaE.login(this.getUser().getNick(), password);
        if (usr != null) {
            this.setUser(usr);
            Logger.getLogger(LoginBean.class).info("entrar correcto: " + usr.getNick());
            this.logged = true;
            result = "home";
        } else {
            context.addMessage("form", new FacesMessage("Usuario o contraseña incorrectos"));
        }
        return result;
    }

    public String register() {
        FacesContext context = FacesContext.getCurrentInstance();
        LoginEjb eaE = new LoginEjb();
        String result = "register";

        if (!password.equals(user.getPassword())) {
            context.addMessage("form:Password", new FacesMessage("Las claves no coinciden"));
        } else if (!eaE.register(user)) {
            context.addMessage("form", new FacesMessage("Nick ocupado"));
        } else {
            Logger.getLogger(LoginBean.class).info("registrar correcto: " + user.getNick());
            result = "login";
        }
        return result;
    }
}
