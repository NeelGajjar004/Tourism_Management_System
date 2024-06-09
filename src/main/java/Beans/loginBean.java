/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Beans;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import record.KeepRecord;

/**
 *
 * @author Admin
 */
@Named(value = "loginBean")
@RequestScoped
public class loginBean {

    private String errorStatus = "";
    
    public loginBean() {
        errorStatus = KeepRecord.getErrorStatus();
    }

    public String getErrorStatus() {
        return errorStatus;
    }

    public void setErrorStatus(String errorStatus) {
        this.errorStatus = errorStatus;
    }
}
