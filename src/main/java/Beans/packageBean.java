/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Beans;

import client.RestClient;
import entity.Package;
import entity.Company;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author Admin
 */
@Named(value = "packageBean")
@SessionScoped
public class packageBean implements Serializable {

    RestClient rc;
    Response rs;
    
    int pid;
    String pname;
    String destination;
    String description;
    Date startdate;
    Date enddate;
    int price;
    String transportationtype;
    String photos;
    int cid;
    String ErrorMsg;
    UploadedFile file;

    
    Package selectedpackage;
    Collection<Package> packages;
    GenericType<Collection<Package>> gpackages;
    Company selectedcompany;
    Collection<Company> companies;
    GenericType<Collection<Company>> gcompanies;
    
    public packageBean() {
        rc = new RestClient();
        packages = new ArrayList<>();
        gpackages = new GenericType<Collection<Package>>(){};
        companies = new ArrayList<>();
        gcompanies = new GenericType<Collection<Company>>(){};
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTransportationtype() {
        return transportationtype;
    }

    public void setTransportationtype(String transportationtype) {
        this.transportationtype = transportationtype;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String ErrorMsg) {
        this.ErrorMsg = ErrorMsg;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    public Package getSelectedpackage() {
        return selectedpackage;
    }

    public void setSelectedpackage(Package selectedpackage) {
        this.selectedpackage = selectedpackage;
    }

    public Collection<Package> getPackages() {
        try{
            rs = rc.getAllPackage(Response.class);
            packages = rs.readEntity(gpackages);
            return packages;
        }catch(Exception e){
            System.out.println("PackageBean Error Occured :- " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public void setPackages(Collection<Package> packages) {
        this.packages = packages;
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
            System.out.println("PackageBean Error Occured :- " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public void setCompanies(Collection<Company> companies) {
        this.companies = companies;
    }
    
    private String uploadfile(){
        String fileName = "";
        if(file != null){
            try(InputStream input = file.getInputStream()){
                fileName = file.getFileName();
                OutputStream output = new FileOutputStream("D:/Collega Work/MScIT_Sem-8/Project/TourismAdvisor/src/main/webapp/Admin/public/uploads" + fileName);
                try {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = input.read(buffer)) != -1) {
                        output.write(buffer, 0, bytesRead);
                    }
                } finally {
                    output.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return fileName;
    }
    
    
    public void insertPackage(){
        try{
            photos = uploadfile();
            boolean InPackage = rc.addPackage(boolean.class, pname, destination, description, String.valueOf(startdate), String.valueOf(enddate), String.valueOf(price), transportationtype, photos, String.valueOf(cid));
            if(InPackage){
                FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/Admin/Package/displayPackage.jsf");
            }else{
                ErrorMsg = "[In-Pack] Something Went Wrong..!";
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void updatePackage(){
        try{
            pid = selectedpackage.getPid();
            pname = selectedpackage.getPname();
            destination = selectedpackage.getDestination();
            description = selectedpackage.getDescription();
            startdate = selectedpackage.getStartdate();
            enddate = selectedpackage.getEnddate();
            price = selectedpackage.getPrice();
            transportationtype = selectedpackage.getTransportationtype();
            photos = selectedpackage.getPhotos();
            cid = selectedpackage.getCid().getCid();
//            photos = uploadfile();
            boolean UpPackage = rc.updatePackage(boolean.class, String.valueOf(pid), pname, destination, description, String.valueOf(startdate), String.valueOf(enddate), String.valueOf(price), transportationtype, photos, String.valueOf(cid));
            if(UpPackage){
                FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/Admin/Package/displayPackage.jsf");
            }else{
                ErrorMsg = "[Up-Pack] Something Went Wrong..!";
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void removePackage(){
        try{
            boolean OutPackage = rc.removePackage(boolean.class, String.valueOf(pid));
            if(OutPackage){
                FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/Admin/Package/displayPackage.jsf");
            }else{
                ErrorMsg = "[Del-Pack] Something Went Wrong..!";
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void redirectToAddPackage() {
        try{
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/Admin/Package/addPackage.jsf");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void redirectToDisplayPackage() {
        try{
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/Admin/Package/displayPackage.jsf");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void redirectToUpdatePackage() {
        try{
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/Admin/Package/updatePackage.jsf");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
