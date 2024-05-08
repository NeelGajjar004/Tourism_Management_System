/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb;

import entity.Company;
import entity.Groups;
import entity.Package;
import entity.Users;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Admin
 */
@Stateless
public class crudejb implements crudejbLocal {
    
    @PersistenceContext(unitName = "tmspu")
    EntityManager em;
    

//    ====> USERS
    @Override
    public void addUsers(String username, String email, String password, String gender, String photo, Date dob, BigInteger phoneno, String address) {
        Users u = new Users();
        u.setUsername(username);
        u.setEmail(email);
        u.setPassword(password);
        u.setGender(gender);
        u.setPhoto(photo);
        u.setDob(dob);
        u.setPhoneno(phoneno);
        u.setAddress(address);
        em.persist(u);
    }

    @Override
    public void updateUsers(String username, String email, String password, String gender, String photo, Date dob, BigInteger phoneno, String address) {
        Users u = (Users) em.find(Users.class, username);
        u.setEmail(email);
        u.setPassword(password);
        u.setGender(gender);
        u.setPhoto(photo);
        u.setDob(dob);
        u.setPhoneno(phoneno);
        u.setAddress(address);
        em.merge(u);
    }

    @Override
    public void removeUsers(String username) {
        Users u = (Users) em.find(Users.class, username);
        em.remove(u);
    }

    @Override
    public Collection<Users> getAllUsers() {
        return em.createNamedQuery("Users.findAll").getResultList();
    }

//    ====> GROUPS
    @Override
    public void addGroups(String groupname, String username) {
        Users u = (Users) em.find(Users.class, username);
        Collection<Groups> groups = u.getGroupsCollection();

        Groups g = new Groups();
        g.setGroupname(groupname);
        g.setUsername(u);
        
        groups.add(g);
        u.setGroupsCollection(groups);
        
        em.persist(g);
        em.merge(u);
    }

    @Override
    public void updateGroups(int groupid, String groupname,String username) {
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
    }

    @Override
    public void removeGroups(int groupid) {
        Groups g = (Groups) em.find(Groups.class,groupid);
        em.remove(g);
    }

    @Override
    public Collection<Groups> getAllGroups() {
        return em.createNamedQuery("Groups.findAll").getResultList();
    }

    @Override
    public Collection<Groups> getUsersByGroupName(String groupname) {
        return em.createNamedQuery("Groups.findByGroupname").setParameter("groupname", groupname).getResultList();
    }

    @Override
    public Collection<Groups> getGroupNameByUser(String username) {
        Users u = (Users) em.find(Users.class, username);
        return u.getGroupsCollection();
    }

//    ====> COMPANY
    @Override
    public void addCompany(String cname, String website, String city) {
        Company c = new Company();
        c.setCname(cname);
        c.setWebsite(website);
        c.setCity(city);
        em.persist(c);
    }

    @Override
    public void updateCompany(int cid, String cname, String website, String city) {
        Company c = (Company) em.find(Company.class, cid);
        c.setCname(cname);
        c.setWebsite(website);
        c.setCity(city);
        em.merge(c);
    }

    @Override
    public void removeCompany(int cid) {
        Company c = (Company) em.find(Company.class, cid);
        em.remove(c);
    }

    @Override
    public Collection<Company> getAllCompany() {
        return em.createNamedQuery("Company.findAll").getResultList();
    }

//    ====> PACKAGE
    @Override
    public void addPackage(String pname, String destination, String description, Date startdate, Date enddate, int price, String transportationtype, String photos, int cid) {
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
    }

    @Override
    public void updatePackage(int pid, String pname, String destination, String description, Date startdate, Date enddate, int price, String transportationtype, String photos, int cid) {
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
    }

    @Override
    public void removePackage(int pid) {
        Package p = (Package) em.find(Package.class, pid);
        em.remove(p);
    }

    @Override
    public Collection<Package> getAllPackage() {
        return em.createNamedQuery("Package.findAll").getResultList();
    }

    @Override
    public Collection<Package> getPackageByDestination(String destination) {
        return em.createNamedQuery("Package.findByDestination").setParameter("destination", destination).getResultList();
    }

    @Override
    public Collection<Package> getPackageByPrice(int price) {
        return em.createNamedQuery("Package.findByPrice").setParameter("price", price).getResultList();
    }

    @Override
    public Collection<Package> getPackageByTransportationType(String transportationtype) {
        return em.createNamedQuery("Package.findByTransportationtype").setParameter("transportationtype", transportationtype).getResultList();
    }

    @Override
    public Collection<Package> getPackageByCompany(int cid) {
        Company c = (Company) em.find(Company.class, cid);
        return c.getPackageCollection();
    }

}
