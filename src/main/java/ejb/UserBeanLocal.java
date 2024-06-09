/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb;

import entity.Booking;
import entity.Users;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author Admin
 */
@Local
public interface UserBeanLocal {
    
    public boolean updateProfile(String username,String email,String password,String gender,String photo,Date dob,BigInteger phoneno,String address);
    public boolean removeProfile(String username);
    
    public Users MyProfile(String username);
    
    
    public boolean addBooking(String username,Integer pid,Date bookingdate,Date traveldate,Integer numbersoftravelers,BigInteger totalamount,String status,Integer aid);
    public boolean updateBooking(Integer bid,String username,Integer pid,Date bookingdate,Date traveldate,Integer numbersoftravelers,BigInteger totalamount,String status,Integer aid);
    public boolean removeBooking(Integer bid);
    
    public Collection<Booking> getBookingByUser(String username);
    
    public boolean addFeedback(String username,Integer bid,Integer rating,String review,Date date);
    
    
    
}
