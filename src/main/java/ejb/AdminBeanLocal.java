/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb;

import entity.Accommodation;
import entity.Booking;
import entity.Company;
import entity.Feedback;
import entity.Groups;
import entity.Users;
import entity.Package;
import entity.Payment;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author Admin
 */
@Local
public interface AdminBeanLocal {
    
//    ==> Users
    
    public boolean addUser(String username,String email,String password,String gender,String photo,Date dob,BigInteger phoneno,String address);
    public boolean updateUser(String username,String email,String password,String gender,String photo,Date dob,BigInteger phoneno,String address);
    public boolean removeUser(String username);
    
    public Collection<Users> getAllUsers();
    public Users getUserByusername(String username);
    
//    ==> Groups
    
    public boolean addGroups(String groupname,String username);
    public boolean updateGroups(Integer groupid,String groupname,String username);
    public boolean removeGroups(Integer groupid);
    
    public Collection<Groups> getAllGroups();
    public Collection<Groups> getUsersByGroupName(String groupname);
    public Collection<Groups> getGroupNameByUser(String username);
    public Groups getGroupByID(Integer groupid);
    
    
//    ==> Company
    
    public boolean addCompany(String cname,String website,String city);
    public boolean updateCompany(Integer cid,String cname,String website,String city);
    public boolean removeCompany(Integer cid);
    
    public Collection<Company> getAllCompany();
    public Company getCompanyByName(String cname);
    public Company getCompanyById(Integer cid);
    
//    ==> Package
    
    public boolean addPackage(String pname,String destination,String description,Date startdate,Date enddate,Integer price,String transportationtype,String photos,Integer cid);
    public boolean updatePackage(Integer pid,String pname,String destination,String description,Date startdate,Date enddate,Integer price,String transportationtype,String photos,Integer cid);
    public boolean removePackage(Integer pid);
    
    public Collection<Package> getAllPackage();
    public Collection<Package> getPackageByDestination(String destination);
    public Collection<Package> getPackageByPrice(Integer price);
    public Collection<Package> getPackageByTransportationType(String transportationtype);
    public Collection<Package> getPackageByCompany(Integer cid);
    public Package getPackageById(Integer pid);
    
//    ==> Accommodation
    
    public boolean addAccommodation(String name, String country, String state, String city,String address, String description, String roomNumber, String type,Integer capacity,Integer price);
    public boolean updateAccommodation(Integer aid,String name, String country, String state, String city,String address, String description, String roomNumber, String type,Integer capacity,Integer price);
    public boolean removeAccommodation(Integer aid);
    
    public Collection<Accommodation> getAllAccommodation();
    
//    ==> Booking
    
    public Collection<Booking> getAllBooking();
    
//    ==> Payment

    public Collection<Payment> getAllPayments();
    
//    ==> Feedback
    
    public Collection<Feedback> getAllFeedback();

}
