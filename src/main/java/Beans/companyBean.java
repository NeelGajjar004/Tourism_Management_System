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
    @NotNull
    String cname;
    @NotNull
    String website;
    @NotNull
    String city;
    
    Company company;
    Collection<Company> companys;
    GenericType<Collection<Company>> gcompanys;
    
    
    public companyBean() {
        rc = new RestClient();
        companys = new ArrayList<>();
        gcompanys = new GenericType<Collection<Company>>(){};
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Collection<Company> getCompanys() {
        try{
            rs = rc.getAllCompany(Response.class);
            companys = rs.readEntity(gcompanys);
            return companys;
        }catch(Exception e){
            e.printStackTrace();
        }
        return companys;
    }

    public void setCompanys(Collection<Company> companys) {
        this.companys = companys;
    }
    
    public void openNew() {
        this.company = new Company();
    }
    
    
    public String CompanyInsert(){
        try{
//            if(cname == "" || website == "" || city == ""){
//                FacesContext.getCurrentInstance().addMessage("message",new FacesMessage(FacesMessage.SEVERITY_ERROR, "Input Validation", "All Fields are Required"));
//            }else{
//                System.out.println("==========> IN ADD COMAPNY METHOD");
//                rc.addCompany(cname, website, city);
//                return "displayCompany.jsf";
//            }
//            System.out.println("==========> IN ADD COMAPNY METHOD");
//            rc.addCompany(cname, website, city);
//            return "displayCompany.jsf";
        }catch(Exception e){
            e.printStackTrace();
        }
        return "addCompany.jsf";
    }
    
    public String updateCompany(){
        try{
            cid = company.getCid();
            cname = company.getCname();
            website = company.getWebsite();
            city = company.getCity();
            rc.updateCompany(String.valueOf(cid), cname, website, city);
            return "displayCompany.jsf";
        }catch(Exception e){
            e.printStackTrace();
        }
        return "displayCompany.jsf";
    }
    
    public String deleteCompany(){
        try{
//            cid = company.getCid();
            rc.removeCompany(String.valueOf(cid));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete Comapny", "Record Deleted Successfully...!"));
//            FacesContext context = FacesContext.getCurrentInstance();
//        try {
//            context.getExternalContext().redirect(context.getExternalContext().getRequestContextPath() + "/displayCompany.jsf");
            return "/displayCompany.jsf";
        }catch(Exception e){
            e.printStackTrace();
        }
//            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete Comapny", "Record Deleted Successfully...!"));
        return "displayCompany.jsf";
    }
    
    public String redirectToaddCompany() {
        return "addCompany.jsf";
    }
    
    public String redirectTodisplayCompany() {
        return "displayCompany.jsf";
    }
    
    public String redirectupdateCompany() {
        return "updateCompany.jsf";
    }
    
}
