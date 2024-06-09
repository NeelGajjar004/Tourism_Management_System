/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Beans;

import client.RestClient;
import entity.Users;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Admin
 */
@Named(value = "registerBean")
@RequestScoped
public class registerBean {

    RestClient rc;
    Response rs;
    
    String username;
    String email;
    String password;
    
    Users selectUser;
    Collection<Users> users;
    GenericType<Collection<Users>> gusers;
    
    
    public registerBean() {
        rc = new RestClient();
        users = new ArrayList<>();
        gusers = new GenericType<Collection<Users>>(){};
    }

    public String UserRegistration(){
        try{
            rc.addUsers(username, email, password, null, null, null, null, null);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Register", "User Register Successfully...!"));
            return "/login.jsf";

        }catch(Exception e){
            e.printStackTrace();
        }
        return "/register.jsf";
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Users getSelectUser() {
        return selectUser;
    }

    public void setSelectUser(Users selectUser) {
        this.selectUser = selectUser;
    }

    public Collection<Users> getUsers() {
        return users;
    }

    public void setUsers(Collection<Users> users) {
        this.users = users;
    }

    
    
}
