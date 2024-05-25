/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Beans;

import client.RestClient;
import entity.Company;
import java.util.Collection;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Admin
 */
@Named(value = "companyBean")
@RequestScoped
public class companyBean {
    
    RestClient rc;
    Response rs;

    int cid;
    String cname;
    String website;
    String city;
    
    Company company;
    Collection<Company> companys;
    GenericType<Collection<Company>> gcompanys;
    
    
    public companyBean() {
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
    
    
    
    public String ADDCompany(){
        try{
            System.out.println("==========> IN ADD COMAPNY METHOD");
            rc.addCompany(cname, website, city);
            return "displayCompany.jsf";
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
            rc.removeCompany(String.valueOf(cid));
            return "displayCompany.jsf";
        }catch(Exception e){
            e.printStackTrace();
        }
        return "displayCompany.jsf";
    }
    
}
