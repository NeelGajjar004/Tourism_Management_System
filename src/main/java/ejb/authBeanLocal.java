/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb;

import entity.Users;
import java.math.BigInteger;
import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author Admin
 */
@Local
public interface authBeanLocal {

    public boolean checkUserName(String username);
    public boolean checkUserEmail(String email);
    public boolean Register(String username,String email,String password,String gender,String photo,Date dob,BigInteger phoneno,String address);
    public Users Login(String username);
    
    public boolean addGroups(String groupname,String username);
    
}
