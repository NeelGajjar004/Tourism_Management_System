/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Beans;

import client.RestClient;
import ejb.authBeanLocal;
import entity.Users;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
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
    String errorMsg;
    
    Users selectUser;
    Collection<Users> users;
    GenericType<Collection<Users>> gusers;
    @EJB authBeanLocal aubl;
    
    
    public registerBean() {
        rc = new RestClient();
        users = new ArrayList<>();
        gusers = new GenericType<Collection<Users>>(){};
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

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
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
    
    public void clearFields(){
        username = "";
        email = "";
        password = "";
    }

    public void addUser(){
        try{
            boolean checkUserName = aubl.checkUserName(username);
            boolean checkUserEmail = aubl.checkUserEmail(email);
            if(checkUserName){
                errorMsg = "User Name Already Exists..!";
                FacesContext.getCurrentInstance().addMessage("Error", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "User Name Already Exists..!"));
                clearFields();
            }else if(checkUserEmail){
                    errorMsg = "email address has only one registered user associated..!";
                FacesContext.getCurrentInstance().addMessage("Error", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "email address has only one registered user associated..!"));
                clearFields();
            }else{
                errorMsg="";
                boolean regis = aubl.Register(username, email, password, null, null, null, null, null);
                if(regis){
                    aubl.addGroups("User", username);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Register", "User Register Successfully...!"));
                    FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/login.jsf");
                }
                clearFields();
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
