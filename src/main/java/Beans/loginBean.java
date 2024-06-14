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
import java.util.Date;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
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
    
    Users selectedUser;
    Users userinfo;
    
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Users getUserinfo() {
        try{
            return rc.Login(Users.class, KeepRecord.getUsername());
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void setUserinfo(Users userinfo) {
        this.userinfo = userinfo;
    }
    
    public Users getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(Users selectedUser) {
        this.selectedUser = selectedUser;
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
