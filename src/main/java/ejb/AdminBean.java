/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb;

import entity.Accommodation;
import entity.Booking;
import entity.Company;
import entity.Feedback;
import entity.Groups;
import entity.Package;
import entity.Payment;
import entity.Users;
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
public class AdminBean implements AdminBeanLocal {
    
    Pbkdf2PasswordHashImpl hp = new Pbkdf2PasswordHashImpl();
    @PersistenceContext(unitName = "tmspu")
    EntityManager em;

    private String HashPassword(String pwd){
        return hp.generate(pwd.toCharArray());
    }
    
//    ======>   -: Users :-
    
    @Override
    public boolean addUser(String username, String email, String password, String gender, String photo, Date dob, BigInteger phoneno, String address) {
        boolean added = false;
        try{
            Users u = new Users();
            u.setUsername(username);
            u.setEmail(email);
            u.setPassword(hp.generate(password.toCharArray()));
            u.setGender(gender);
            u.setPhoto(photo);
            u.setDob(dob);
            u.setPhoneno(phoneno);
            u.setAddress(address);
            em.persist(u);
            added = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return added;
    }

    @Override
    public boolean updateUser(String username, String email, String password, String gender, String photo, Date dob, BigInteger phoneno, String address) {
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
    public boolean removeUser(String username) {
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
    public Collection<Users> getAllUsers() {
        try{
            return em.createNamedQuery("Groups.findAll").getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Users getUserByusername(String username) {
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
    
//    ======>   -: Groups :-
    
    @Override
    public boolean addGroups(String groupname, String username) {
        boolean added = false;
        try{
            Users u = (Users) em.find(Users.class, username);
            Collection<Groups> groups = u.getGroupsCollection();

            Groups g = new Groups();
            g.setGroupname(groupname);
            g.setUsername(u);

            groups.add(g);
            u.setGroupsCollection(groups);

            em.persist(g);
            em.merge(u);
            added = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return added;
    }

    @Override
    public boolean updateGroups(Integer groupid, String groupname, String username) {
        boolean updated = false;
        try{
            Users u = (Users) em.find(Users.class, username);
            Collection<Groups> groups = u.getGroupsCollection();

            Groups g = (Groups) em.find(Groups.class, groupid);

            if(groups.contains(g)){
                groups.remove(g);
            }

            g.setGroupname(groupname);
            g.setUsername(u);

            groups.add(g);
            u.setGroupsCollection(groups);

            em.persist(g);
            em.merge(u);
            updated = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return updated;
    }

    @Override
    public boolean removeGroups(Integer groupid) {
        boolean removed = false;
        try{
            Groups g = (Groups) em.find(Groups.class,groupid);
            em.remove(g);
            removed = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return removed;
    }

    @Override
    public Collection<Groups> getAllGroups() {
        try{
            return em.createNamedQuery("Groups.findAll").getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<Groups> getUsersByGroupName(String groupname) {
        try{
            return em.createNamedQuery("Groups.findByGroupname").setParameter("groupname", groupname).getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<Groups> getGroupNameByUser(String username) {
        try{
            Users u = (Users) em.find(Users.class, username);
            return u.getGroupsCollection();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public Groups getGroupByID(Integer groupid) {
        try{
            Groups g = (Groups) em.find(Groups.class, groupid);
            return g;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

//    ======>   -: Company :-

    @Override
    public boolean addCompany(String cname, String website, String city) {
        boolean added = false;
        try{
            Company c = new Company();
            c.setCname(cname);
            c.setWebsite(website);
            c.setCity(city);
            em.persist(c);
            added = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return added;
    }

    @Override
    public boolean updateCompany(Integer cid, String cname, String website, String city) {
        boolean updated = false;
        try{
            Company c = (Company) em.find(Company.class, cid);
            c.setCname(cname);
            c.setWebsite(website);
            c.setCity(city);
            em.merge(c);
            updated = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return updated;
    }

    @Override
    public boolean removeCompany(Integer cid) {
        boolean removed = false;
        try{
            Company c = (Company) em.find(Company.class, cid);
            em.remove(c);
            removed = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return removed;
    }

    @Override
    public Collection<Company> getAllCompany() {
        try{
            return em.createNamedQuery("Company.findAll").getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Company getCompanyByName(String cname) {
        try{
            Collection<Company> coms = em.createNamedQuery("Company.findByCname").setParameter("cname", cname).getResultList();
            for(Company c : coms){
                return c;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public Company getCompanyById(Integer cid) {
        try{
            Company c = (Company) em.find(Company.class, cid);
            return c;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
//    ======>   -: Package :-

    @Override
    public boolean addPackage(String pname, String destination, String description, Date startdate, Date enddate, Integer price, String transportationtype, String photos, Integer cid) {
        boolean added = false;
        try{
            Company c = (Company) em.find(Company.class, cid);
            Collection<Package> packages = c.getPackageCollection();

            Package p = new Package();
            p.setPname(pname);
            p.setDestination(destination);
            p.setDescription(description);
            p.setStartdate(startdate);
            p.setEnddate(enddate);
            p.setPrice(price);
            p.setTransportationtype(transportationtype);
            p.setPhotos(photos);
            p.setCid(c);

            packages.add(p);
            c.setPackageCollection(packages);

            em.persist(p);
            em.merge(c);
            added = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return added;
    }

    @Override
    public boolean updatePackage(Integer pid, String pname, String destination, String description, Date startdate, Date enddate, Integer price, String transportationtype, String photos, Integer cid) {
        boolean updated = false;
        try{
            Company c = (Company) em.find(Company.class, cid);
            Collection<Package> packages = c.getPackageCollection();

            Package p = (Package) em.find(Package.class, pid);

            if(packages.contains(p)){
                packages.remove(p);
            }

            p.setPname(pname);
            p.setDestination(destination);
            p.setDescription(description);
            p.setStartdate(startdate);
            p.setEnddate(enddate);
            p.setPrice(price);
            p.setTransportationtype(transportationtype);
            p.setPhotos(photos);
            p.setCid(c);

            packages.add(p);
            c.setPackageCollection(packages);

            em.persist(p);
            em.merge(c);
            updated = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return updated;
    }

    @Override
    public boolean removePackage(Integer pid) {
        boolean removed = false;
        try{
            Package p = (Package) em.find(Package.class, pid);
            em.remove(p);
            removed = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return removed;
    }

    @Override
    public Collection<Package> getAllPackage() {
        try{
            return em.createNamedQuery("Package.findAll").getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<Package> getPackageByDestination(String destination) {
        try{
            return em.createNamedQuery("Package.findByDestination").setParameter("destination", destination).getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<Package> getPackageByPrice(Integer price) {
        try{
            return em.createNamedQuery("Package.findByPrice").setParameter("price", price).getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<Package> getPackageByTransportationType(String transportationtype) {
        try{
            return em.createNamedQuery("Package.findByTransportationtype").setParameter("transportationtype", transportationtype).getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<Package> getPackageByCompany(Integer cid) {
        try{
            Company c = (Company) em.find(Company.class, cid);
            return c.getPackageCollection();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Package getPackageById(Integer pid) {
        try{
            Package p = (Package) em.find(Package.class, pid);
            return p;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

//    ======>   -: Accommodation :-
    
    @Override
    public boolean addAccommodation(String name, String country, String state, String city, String address, String description, String roomNumber, String type, Integer capacity, Integer price) {
        boolean added = false;
        try{
            Accommodation a = new Accommodation();
            a.setName(name);
            a.setCountry(country);
            a.setState(state);
            a.setCity(city);
            a.setAddress(address);
            a.setDescription(description);
            a.setRoomNumber(roomNumber);
            a.setType(type);
            a.setCapacity(capacity);
            a.setPrice(price);
            em.persist(a);
            added = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return added;
    }

    @Override
    public boolean updateAccommodation(Integer aid, String name, String country, String state, String city, String address, String description, String roomNumber, String type, Integer capacity, Integer price) {
        boolean updated = false;
        try{
            Accommodation a = (Accommodation) em.find(Accommodation.class, aid);
            a.setName(name);
            a.setCountry(country);
            a.setState(state);
            a.setCity(city);
            a.setAddress(address);
            a.setDescription(description);
            a.setRoomNumber(roomNumber);
            a.setType(type);
            a.setCapacity(capacity);
            a.setPrice(price);
            em.merge(a);
            updated = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return updated;
    }

    @Override
    public boolean removeAccommodation(Integer aid) {
        boolean removed = false;
        try{
            Accommodation a = (Accommodation) em.find(Accommodation.class, aid);
            em.remove(a);
            removed = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return removed;
    }

    @Override
    public Collection<Accommodation> getAllAccommodation() {
        try{
            return em.createNamedQuery("Accommodation.findAll").getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

//    ======>   -: Booking :-
    
    @Override
    public Collection<Booking> getAllBooking() {
        try{
            return em.createNamedQuery("Booking.findAll").getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

//    ======>   -: Payment :-
    
    @Override
    public Collection<Payment> getAllPayments() {
        try{
            return em.createNamedQuery("Payment.findAll").getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

//    ======>   -: Feedback :-
    
    @Override
    public Collection<Feedback> getAllFeedback() {
        try{
            return em.createNamedQuery("Feedback.findAll").getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
