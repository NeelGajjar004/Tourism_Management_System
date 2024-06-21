/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Beans;

import entity.Package;
import client.RestClient;
import entity.Company;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import org.primefaces.model.ResponsiveOption;

/**
 *
 * @author Admin
 */
@Named(value = "userPackageBean")
@SessionScoped
public class userPackageBean implements Serializable {

    private List<ResponsiveOption> responsiveOptions;
    
    RestClient rc;
    Response rs;
    
    int cid;
    String destination;
    
    Package selectedPackage;
    Collection<Package> packages;
    GenericType<Collection<Package>> gpackages;
    
    Collection<Package> destinationPackages;
    GenericType<Collection<Package>> gdestinationPackages;
    
    Collection<Package> companyPackages;
    GenericType<Collection<Package>> gcompanyPackages;
    
    Company selectedcompany;
    Collection<Company> companies;
    GenericType<Collection<Company>> gcompanies;
    
    
    public userPackageBean() {
        rc = new RestClient();
        packages = new ArrayList<>();
        gpackages = new GenericType<Collection<Package>>(){};
    }
    
    @PostConstruct
    public void init() {
        responsiveOptions = new ArrayList<>();
        responsiveOptions.add(new ResponsiveOption("1024px", 3, 3));
        responsiveOptions.add(new ResponsiveOption("768px", 2, 2));
        responsiveOptions.add(new ResponsiveOption("560px", 1, 1));
    }

    public List<ResponsiveOption> getResponsiveOptions() {
        return responsiveOptions;
    }

    public void setResponsiveOptions(List<ResponsiveOption> responsiveOptions) {
        this.responsiveOptions = responsiveOptions;
    }


    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Package getSelectedPackage() {
        return selectedPackage;
    }

    public void setSelectedPackage(Package selectedPackage) {
        this.selectedPackage = selectedPackage;
    }

    public Collection<Package> getPackages() {
        try{
            rs = rc.getAllPackage(Response.class);
            packages = rs.readEntity(gpackages);
            return packages;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void setPackages(Collection<Package> packages) {
        this.packages = packages;
    }

    public Collection<Package> getDestinationPackages() {
        try{
            if(destination.isEmpty()){
                    rs = rc.getAllPackage(Response.class);
                    packages = rs.readEntity(gpackages);
                    return packages;
            }else{
                rs = rc.getPackageByDestination(Response.class,destination);
                destinationPackages = rs.readEntity(gdestinationPackages);
                return destinationPackages;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void setDestinationPackages(Collection<Package> destinationPackages) {
        this.destinationPackages = destinationPackages;
    }

    public Collection<Package> getCompanyPackages() {
        try{
            rs = rc.getPackageByCompany(Response.class,String.valueOf(cid));
            companyPackages = rs.readEntity(gcompanyPackages);
            return companyPackages;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void setCompanyPackages(Collection<Package> companyPackages) {
        this.companyPackages = companyPackages;
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
            e.printStackTrace();
        }
        return null;
    }

    public void setCompanies(Collection<Company> companies) {
        this.companies = companies;
    }

    public void redirectToPackageBooking(){
        try{
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/Users/Package/bookingPackage.jsf");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
}
