/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb;

import entity.Company;
import entity.Groups;
import entity.Users;
import entity.Package;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Local;

@Local
public interface crudejbLocal {
    
//    ====>Users
    public void addUsers(String username,String email,String password,String gender,String photo,Date dob,BigInteger phoneno,String address);
    public void updateUsers(String username,String email,String password,String gender,String photo,Date dob,BigInteger phoneno,String address);
    public void removeUsers(String username);
    
    public Collection<Users> getAllUsers();
    
//    ====>Groups
    public void addGroups(String groupname,String username);
    public void updateGroups(int groupid,String groupname,String username);
    public void removeGroups(int groupid);
    
    public Collection<Groups> getAllGroups();
    public Collection<Groups> getUsersByGroupName(String groupname);
    public Collection<Groups> getGroupNameByUser(String username);
    
    
//    ====>Company
    public void addCompany(String cname,String website,String city);
    public void updateCompany(int cid,String cname,String website,String city);
    public void removeCompany(int cid);
    
    public Collection<Company> getAllCompany();
    
//    ====>Package
    public void addPackage(String pname,String destination,String description,Date startdate,Date enddate,int price,String transportationtype,String photos,int cid);
    public void updatePackage(int pid,String pname,String destination,String description,Date startdate,Date enddate,int price,String transportationtype,String photos,int cid);
    public void removePackage(int pid);
    
    public Collection<Package> getAllPackage();
    public Collection<Package> getPackageByDestination(String destination);
    public Collection<Package> getPackageByPrice(int price);
    public Collection<Package> getPackageByTransportationType(String transportationtype);
    public Collection<Package> getPackageByCompany(int cid);
  
}
