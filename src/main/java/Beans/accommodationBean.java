/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Beans;

import client.RestClient;
import entity.Accommodation;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Admin
 */
@Named(value = "accommodationBean")
@SessionScoped
public class accommodationBean implements Serializable {

    RestClient rc;
    Response rs;
    
    int aid;
    String name;
    String country;
    String state;
    String city;
    String address;
    String description;
    String roomNumber;
    String type;
    int capacity;
    int price;
    String ErrorMsg;
    
    Accommodation selectedAccommodation;
    Collection<Accommodation> accommodations;
    GenericType<Collection<Accommodation>> gaccommodations;
    
    public accommodationBean() {
        rc = new RestClient();
        accommodations = new ArrayList<>();
        gaccommodations = new GenericType<Collection<Accommodation>>(){};
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String ErrorMsg) {
        this.ErrorMsg = ErrorMsg;
    }

    public Accommodation getSelectedAccommodation() {
        return selectedAccommodation;
    }

    public void setSelectedAccommodation(Accommodation selectedAccommodation) {
        this.selectedAccommodation = selectedAccommodation;
    }
    
    private void clearFields(){
        name="";
        country = "";
        state = "";
        city = "";
        address = "";
        description = "";
        roomNumber = "";
        capacity = 0;
        price = 0;
    }

    public Collection<Accommodation> getAccommodations() {
        try{
            rs = rc.getAllAccommodation(Response.class);
            accommodations = rs.readEntity(gaccommodations);
            return accommodations;
        }catch(Exception e){
            System.out.println("AccommodationBean Error Occured :- " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public void setAccommodations(Collection<Accommodation> accommodations) {
        this.accommodations = accommodations;
    }
    
    public void insertAccommodation(){
        try{
            boolean InAccommodation = rc.addAccommodation(boolean.class, name, country, state, city, address, description, roomNumber, type, String.valueOf(capacity), String.valueOf(price));
            if(InAccommodation){
                FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/Admin/Accommodation/displayAccommodation.jsf");
            clearFields();
            }else{
                ErrorMsg = "[In-Acco] Something Went Wrong..!";
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void updateAccommodation(){
        try{
            aid = selectedAccommodation.getAid();
            name = selectedAccommodation.getName();
            country = selectedAccommodation.getCountry();
            state = selectedAccommodation.getState();
            city = selectedAccommodation.getCity();
            address = selectedAccommodation.getAddress();
            description = selectedAccommodation.getDescription();
            roomNumber = selectedAccommodation.getRoomNumber();
            type = selectedAccommodation.getType();
            capacity = selectedAccommodation.getCapacity();
            price = selectedAccommodation.getPrice();
            boolean UpAccommodation = rc.updateAccommodation(boolean.class, String.valueOf(aid), name, country, state, city, address, description, roomNumber, type,String.valueOf(capacity), String.valueOf(price));
            if(UpAccommodation){
                FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/Admin/Accommodation/displayAccommodation.jsf");
                clearFields();
            }else{
                ErrorMsg = "[Up-Acco] Something Went Wrong..!";
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void removeAccommodation(){
        try{
            boolean OutAccommodation = rc.removeAccommodation(boolean.class, String.valueOf(aid));
            if(OutAccommodation){
                FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/Admin/Accommodation/displayAccommodation.jsf");
            }else{
                ErrorMsg = "[Del-Acco] Something Went Wrong..!";
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void redirectToAddAccommodation() {
        try{
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/Admin/Accommodation/addAccommodation.jsf");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void redirectToDisplayAccommodation() {
        try{
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/Admin/Accommodation/displayAccommodation.jsf");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void redirectToUpdateAccommodation() {
        try{
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/Admin/Accommodation/updateAccommodation.jsf");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
