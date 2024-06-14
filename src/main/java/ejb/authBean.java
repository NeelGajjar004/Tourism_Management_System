/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb;

import entity.Groups;
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
public class authBean implements authBeanLocal {
    
    Pbkdf2PasswordHashImpl ph = new Pbkdf2PasswordHashImpl();
    
    @PersistenceContext(unitName = "tmspu")
    EntityManager em;

    @Override
    public boolean checkUserName(String username) {
        boolean exits = false;
        try{
            Object count = em.createNamedQuery("Users.findByUsername").setParameter("username", username).getSingleResult();
            exits = Integer.parseInt(count.toString()) > 0;
        }catch(Exception e){
            e.printStackTrace();
        }
        return exits;
    }
    
    @Override
    public boolean checkUserEmail(String email) {
        boolean exits = false;
        try{
            Object count = em.createNamedQuery("Users.findByEmail").setParameter("email", email).getSingleResult();
            exits = Integer.parseInt(count.toString()) > 0;
        }catch(Exception e){
            e.printStackTrace();
        }
        return exits;
    }

    private String HashPassword(String pwd){
        return ph.generate(pwd.toCharArray());
    }
    
    @Override
    public boolean Register(String username, String email, String password, String gender, String photo, Date dob, BigInteger phoneno, String address) {
        boolean registerd = false;
        try{
            Users u = new Users();
            u.setUsername(username);
            u.setEmail(email);
            u.setPassword(ph.generate(password.toCharArray()));
            u.setGender(gender);
            u.setPhoto(photo);
            u.setDob(dob);
            u.setPhoneno(phoneno);
            u.setAddress(address);
            em.persist(u);
            registerd = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return registerd;
    }

    @Override
    public Users Login(String username) {
        Users u =(Users) em.find(Users.class, username);
        return u;
    }

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
            e.printStackTrace();;
        }
        return added;
    }
}
