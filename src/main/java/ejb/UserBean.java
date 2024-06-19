/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb;

import entity.Accommodation;
import entity.Booking;
import entity.Feedback;
import entity.Users;
import entity.Package;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;

/**
 *
 * @author Admin
 */
@Stateless
public class UserBean implements UserBeanLocal {
    
    @PersistenceContext(unitName = "tmspu")
    EntityManager em;
    
    @Override
    public boolean updateProfile(String username, String email, String password, String gender, String photo, Date dob, BigInteger phoneno, String address) {
        boolean updated = false;
        try{
            Users u = (Users) em.find(Users.class, username);
            u.setEmail(email);
            u.setPassword(password);
            u.setGender(gender);
            u.setPhoto(photo);
            u.setDob(dob);
            u.setPhoneno(phoneno);
            u.setAddress(address);
            em.merge(u);
            updated = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return updated;
    }

    @Override
    public boolean removeProfile(String username) {
        boolean removed = false;
        try{
            Users u = (Users) em.find(Users.class, username);
            em.remove(u);
            removed = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return removed;
    }

    @Override
    public Users MyProfile(String username) {
        try{
            Collection<Users> user = em.createNamedQuery("Users.findByUsername").setParameter("username", username).getResultList();
            for(Users u : user){
                return u;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addBooking(String username, Integer pid, Date bookingdate, Date traveldate, Integer numbersoftravelers, BigInteger totalamount, String status, Integer aid) {
        boolean added = false;
        try{
            Users u = (Users) em.find(Users.class, username);
            Collection<Booking> ubook = u.getBookingCollection();
            
            Package p = (Package) em.find(Package.class, pid);
            Collection<Booking> pbook = p.getBookingCollection();
            
            Accommodation a = (Accommodation) em.find(Accommodation.class, aid);
            Collection<Booking> abook = a.getBookingCollection();

            Booking b = new Booking();
            
            b.setUsername(u);
            b.setPid(p);
            b.setBookingdate(bookingdate);
            b.setTraveldate(p.getStartdate());
            b.setNumbersoftravelers(numbersoftravelers);
            b.setTotalamount(totalamount);
            b.setStatus(status);
            b.setAid(a);
            
            ubook.add(b);
            pbook.add(b);
            abook.add(b);
            u.setBookingCollection(ubook);
            p.setBookingCollection(pbook);
            a.setBookingCollection(abook);
            
            em.persist(b);
            em.merge(u);
            em.merge(p);
            em.merge(a);
            
            
            added = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return added;
    }

    @Override
    public boolean updateBooking(Integer bid, String username, Integer pid, Date bookingdate, Date traveldate, Integer numbersoftravelers, BigInteger totalamount, String status, Integer aid) {
        boolean updated = false;
        try{
            updated = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return updated;
    }

    @Override
    public boolean removeBooking(Integer bid) {
        boolean removed = false;
        try{
            removed = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return removed;
    }

    @Override
    public Collection<Booking> getBookingByUser(String username) {
        try{
            Users u = (Users) em.find(Users.class, username);
            return u.getBookingCollection();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addFeedback(String username, Integer bid, Integer rating, String review, Date date) {
        boolean added = false;
        try{
            Users u = (Users) em.find(Users.class, username);
            Collection<Feedback> ufeed = u.getFeedbackCollection();
            
            Booking b = (Booking) em.find(Booking.class, bid);
            Collection<Feedback> bfeed = b.getFeedbackCollection();
            
            Feedback f = new Feedback();
            f.setUsername(u);
            f.setBid(b);
            f.setRating(rating);
            f.setReview(review);
            f.setDate(date);
            
            ufeed.add(f);
            bfeed.add(f);
            u.setFeedbackCollection(ufeed);
            b.setFeedbackCollection(bfeed);
            
            em.persist(f);
            em.merge(u);
            em.merge(b);
            
            added = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return added;
    }
}
