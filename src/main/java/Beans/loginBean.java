/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Beans;

import client.RestClient;
import entity.Users;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import org.primefaces.model.file.UploadedFile;
import record.KeepRecord;

/**
 *
 * @author Admin
 */
@Named(value = "loginBean")
@SessionScoped
public class loginBean implements Serializable{

    RestClient rc;
    Response rs;
    
    String username;
    String email;
    String password;
    String gender;
    String photo;
    Date dob;
    BigInteger phoneno;
    String address;
    UploadedFile file;
    
    Users userInfo;
    
    Users selectedUser;
    Collection<Users> users;
    GenericType<Collection<Users>> gusers;
    
    private String errorStatus = "";
    
    public loginBean() {
        errorStatus = KeepRecord.getErrorStatus();
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public BigInteger getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(BigInteger phoneno) {
        this.phoneno = phoneno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public Users getUserInfo() {
        return rc.Login(Users.class, KeepRecord.getUsername());
    }

    public void setUserInfo(Users userInfo) {
        this.userInfo = userInfo;
    }

    public Users getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(Users selectedUser) {
        this.selectedUser = selectedUser;
    }

    public Collection<Users> getUsers() {
        return users;
    }

    public void setUsers(Collection<Users> users) {
        this.users = users;
    }

    public String getErrorStatus() {
        return errorStatus;
    }

    public void setErrorStatus(String errorStatus) {
        this.errorStatus = errorStatus;
    }

    private String uploadfile(){
        String fileName = "";
        if(file != null){
            try(InputStream input = file.getInputStream()){
                fileName = file.getFileName();

                OutputStream output = new FileOutputStream("D:/Collega Work/MScIT_Sem-8/Project/TourismAdvisor/src/main/webapp/public/uploads/" + fileName);
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
    
    public void updateProfile(){
        try{
            boolean upPro;
            String filename = uploadfile();
            
            if(filename.isEmpty()){
                upPro = rc.updateProfile(boolean.class, selectedUser.getUsername(), selectedUser.getEmail(), selectedUser.getPassword(), selectedUser.getGender(),selectedUser.getPhoto(), String.valueOf(selectedUser.getDob()),String.valueOf(selectedUser.getPhoneno()), selectedUser.getAddress());
            }else{
                upPro = rc.updateProfile(boolean.class, selectedUser.getUsername(), selectedUser.getEmail(), selectedUser.getPassword(), selectedUser.getGender(),filename, String.valueOf(selectedUser.getDob()),String.valueOf(selectedUser.getPhoneno()), selectedUser.getAddress());
            }
            if(upPro){
                if(KeepRecord.getRoles().contains("Admin")){
                    FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/Admin/adminDashboard.jsf");
                }else{
                    FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/Users/home.jsf");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void redirectToUpdateProfile(){
        try{
            if(KeepRecord.getRoles().contains("Admin")){
                    FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/Admin/updateProfile.jsf");
                }else{
                    FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/Users/updateProfile.jsf");
                }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
