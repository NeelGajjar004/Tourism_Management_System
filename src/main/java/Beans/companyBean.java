/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Beans;

import client.RestClient;
import entity.Company;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DialogFrameworkOptions;

/**
 *
 * @author Admin
 */
@Named(value = "companyBean")
@SessionScoped
public class companyBean implements Serializable{
    
    RestClient rc;
    Response rs;

    int cid;
    String cname = "";
    String website = "";
    String city = "";
    String ErrorMsg = "";
    
    Company selectedcompany;
    Collection<Company> companies;
    GenericType<Collection<Company>> gcompanies;
    
    
    public companyBean() {
        rc = new RestClient();
        companies = new ArrayList<>();
        gcompanies = new GenericType<Collection<Company>>(){};
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String ErrorMsg) {
        this.ErrorMsg = ErrorMsg;
    }
    
    public Company getSelectedcompany() {
        return selectedcompany;
    }

    public void setSelectedcompany(Company selectedcompany) {
        this.selectedcompany = selectedcompany;
    }

    public Collection<Company> getCompanies() {
        try{
            rs = rc.getAllCompany(Response.class);
            companies = rs.readEntity(gcompanies);
            return companies;
        }catch(Exception e){
            System.out.println("CompanyBean Error Occured :- " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public void setCompanies(Collection<Company> companies) {
        this.companies = companies;
    }

    
    
    public void insertCompany(){
        try{
            boolean InCompany = rc.addCompany(Boolean.class, cname, website, city);
            if(InCompany){
                FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/Admin/Company/displayCompany.jsf");
            }else{
                ErrorMsg = "[In-Com] Something Went Wrong..!";
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void updateCompany(){
        try{
            cid = selectedcompany.getCid();
            cname = selectedcompany.getCname();
            website = selectedcompany.getWebsite();
            city = selectedcompany.getCity();
            boolean UpCompany = rc.updateCompany(boolean.class, String.valueOf(cid),cname,website,city);
            if(UpCompany){
                FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/Admin/Company/displayCompany.jsf");
            }else{
                ErrorMsg = "[Up-Com] Something Went Wrong..!";
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void deleteCompany(){
        try{
            boolean OutCompany = rc.removeCompany(boolean.class, String.valueOf(cid));
            if(OutCompany){
                FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/Admin/Company/displayCompany.jsf");
            }else{
                ErrorMsg = "[Del-Com] Something Went Wrong..!";
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void redirectToAddCompany() {
        try{
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/Admin/Company/addCompany.jsf");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void redirectToDisplayCompany() {
        try{
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/Admin/Company/displayCompany.jsf");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void redirectToUpdateCompany() {
        try{
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/Admin/Company/updateCompany.jsf");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
