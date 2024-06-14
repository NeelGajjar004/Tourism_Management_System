package com.mycompany.TourismAdvisor.resources;

import ejb.AdminBeanLocal;
import ejb.UserBeanLocal;
import ejb.authBeanLocal;
import entity.Accommodation;
import entity.Booking;
import entity.Company;
import entity.Feedback;
import entity.Package;
import entity.Groups;
import entity.Payment;
import entity.Users;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author 
 */
@DeclareRoles({"Admin","User"})
@Path("rest")
public class JakartaEE8Resource {
    
    @EJB AdminBeanLocal abl;
    @EJB UserBeanLocal ubl;
    @EJB authBeanLocal aubl;
    
//   ====>      ----: Authentication APIs :----
    
    @PermitAll
    @GET
    @Path("checkUserName/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean checkUserName(@PathParam("username") String username){
        return aubl.checkUserName(username);
    }
    
    @PermitAll
    @GET
    @Path("checkUserEmail/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean checkUserEmail(@PathParam("email") String email){
        return aubl.checkUserEmail(email);
    }
    
    
    @PermitAll
    @POST
    @Path("Register/{username}/{email}/{password}/{gender}/{photo}/{dob}/{phoneno}/{address}")
    public boolean Register(@PathParam("username") String username,@PathParam("email") String email,@PathParam("password")String password,@PathParam("gender") String gender,@PathParam("photo") String photo,@PathParam("dob") Date dob,@PathParam("phoneno") BigInteger phoneno,@PathParam("address") String address){
        return aubl.Register(username, email, password, gender, photo, dob, phoneno, address);
    }
    
    @RolesAllowed({"Admin","User"})
    @GET
    @Path("Login/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Users Login(@PathParam("username") String username){
        return aubl.Login(username);
    }
    
    @RolesAllowed({"Admin","User"})
    @POST
    @Path("authaddGroups/{groupname}/{username}")
    public boolean authaddGroups(@PathParam("groupname") String groupname,@PathParam("username") String username){
        return aubl.addGroups(groupname, username);
    }
    
    
//   ====>      ----: Admin APIs :----
    
    //    ==> Users
    
    @RolesAllowed("Admin")
    @POST
    @Path("addUser/{username}/{email}/{password}/{gender}/{photo}/{dob}/{phoneno}/{address}")
    public boolean addUser(@PathParam("username") String username,@PathParam("email") String email,@PathParam("password") String password,@PathParam("gender") String gender,@PathParam("photo") String photo,@PathParam("dob") Date dob,@PathParam("phoneno") BigInteger phoneno,@PathParam("address") String address){
        return abl.addUser(username, email, password, gender, photo, dob, phoneno, address);
    }
    
    @RolesAllowed("Admin")
    @POST
    @Path("updateUser/{username}/{email}/{password}/{gender}/{photo}/{dob}/{phoneno}/{address}")
    public boolean updateUser(@PathParam("username") String username,@PathParam("email") String email,@PathParam("password") String password,@PathParam("gender") String gender,@PathParam("photo") String photo,@PathParam("dob") Date dob,@PathParam("phoneno") BigInteger phoneno,@PathParam("address") String address){
        return abl.updateUser(username, email, password, gender, photo, dob, phoneno, address);
    }
    
    @RolesAllowed("Admin")
    @DELETE
    @Path("removeUser/{username}")
    public boolean removeUser(@PathParam("username") String username)
    {
        return abl.removeUser(username);
    }
    
    @RolesAllowed("Admin")
    @GET
    @Path("getAllUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Users> getAllUsers(){
        return abl.getAllUsers();
    }
    
    @RolesAllowed("Admin")
    @GET
    @Path("getUserByusername/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Users getUserByusername(@PathParam("username") String username){
        return abl.getUserByusername(username);
    }
    
//    ==> Groups
    
    @RolesAllowed("Admin")
    @POST
    @Path("addGroups/{groupname}/{username}")
    public boolean addGroups(@PathParam("groupname") String groupname,@PathParam("username") String username){
        return abl.addGroups(groupname, username);
    }
    
    
    @RolesAllowed("Admin")
    @POST
    @Path("updateGroups/{groupid}/{groupname}/{username}")
    public boolean updateGroups(@PathParam("groupid") Integer groupid,@PathParam("groupname") String groupname,@PathParam("username") String username){
        return abl.updateGroups(groupid, groupname, username);
    }
    
    
    @RolesAllowed("Admin")
    @DELETE
    @Path("removeGroups/{groupid}")
    public boolean removeGroups(Integer groupid){
        return abl.removeGroups(groupid);
    }
    
    @RolesAllowed("Admin")
    @GET
    @Path("getAllGroups")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Groups> getAllGroups(){
        return abl.getAllGroups();
    }
    
    @RolesAllowed("Admin")
    @GET
    @Path("getUsersByGroupName/{groupname}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Groups> getUsersByGroupName(@PathParam("groupname") String groupname){
        return abl.getUsersByGroupName(groupname);
    }
    
    @RolesAllowed("Admin")
    @GET
    @Path("getGroupNameByUser/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Groups> getGroupNameByUser(@PathParam("username") String username){
        return abl.getGroupNameByUser(username);
    }
    
    @RolesAllowed("Admin")
    @GET
    @Path("getGroupByID/{groupid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Groups getGroupByID(@PathParam("groupid") Integer groupid){
        return abl.getGroupByID(groupid);
    }
    
    
//    ==> Company
    
    @RolesAllowed("Admin")
    @POST
    @Path("addCompany/{cname}/{website}/{city}")
    public boolean addCompany(@PathParam("cname") String cname,@PathParam("website") String website,@PathParam("city") String city){
        return abl.addCompany(cname, website, city);
    }
    
    @RolesAllowed("Admin")
    @POST
    @Path("updateCompany/{cid}/{cname}/{website}/{city}")
    public boolean updateCompany(@PathParam("cid") Integer cid,@PathParam("cname") String cname,@PathParam("website") String website,@PathParam("city") String city){
        return abl.updateCompany(cid, cname, website, city);
    }
    
    @RolesAllowed("Admin")
    @DELETE
    @Path("removeCompany/{cid}")
    public boolean removeCompany(@PathParam("cid") Integer cid){
        return abl.removeCompany(cid);
    }
    
    @RolesAllowed("Admin")
    @GET
    @Path("getAllCompany")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Company> getAllCompany(){
        return abl.getAllCompany();
    }
    
    @RolesAllowed("Admin")
    @GET
    @Path("getCompanyByName/{cname}")
    @Produces(MediaType.APPLICATION_JSON)
    public Company getCompanyByName(@PathParam("cname") String cname){
        return abl.getCompanyByName(cname);
    }
    
    @RolesAllowed("Admin")
    @GET
    @Path("getCompanyById/{cid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Company getCompanyById(@PathParam("cid") Integer cid){
        return abl.getCompanyById(cid);
    }
    
//    ==> Package
    
    @RolesAllowed("Admin")
    @POST
    @Path("addPackage/{pname}/{destination}/{description}/{startdate}/{enddate}/{price}/{transportationtype}/{photos}/{cid}")
    public boolean addPackage(@PathParam("pname") String pname,@PathParam("destination") String destination,@PathParam("description") String description,@PathParam("startdate") Date startdate,@PathParam("enddate") Date enddate,@PathParam("price") Integer price,@PathParam("transportationtype") String transportationtype,@PathParam("photos") String photos,@PathParam("cid") Integer cid){
        return abl.addPackage(pname, destination, description, startdate, enddate, price, transportationtype, photos, cid);
    }
    
    
    @RolesAllowed("Admin")
    @POST
    @Path("updatePackage/{pid}/{pname}/{destination}/{description}/{startdate}/{enddate}/{price}/{transportationtype}/{photos}/{cid}")
    public boolean updatePackage(@PathParam("pid") Integer pid,@PathParam("pname") String pname,@PathParam("destination") String destination,@PathParam("description") String description,@PathParam("startdate") Date startdate,@PathParam("enddate") Date enddate,@PathParam("price") Integer price,@PathParam("transportationtype") String transportationtype,@PathParam("photos") String photos,@PathParam("cid") Integer cid){
        return abl.updatePackage(pid, pname, destination, description, startdate, enddate, price, transportationtype, photos, cid);
    }
    
    @RolesAllowed("Admin")
    @DELETE
    @Path("removePackage/{pid}")
    public boolean removePackage(@PathParam("pid") Integer pid){
        return abl.removePackage(pid);
    }
    
    @RolesAllowed({"Admin","User"})
    @GET
    @Path("getAllPackage")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Package> getAllPackage(){
        return abl.getAllPackage();
    }
    
    @RolesAllowed("Admin")
    @GET
    @Path("getPackageByDestination/{destination}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Package> getPackageByDestination(@PathParam("destination") String destination){
        return abl.getPackageByDestination(destination);
    }
    
    @RolesAllowed("Admin")
    @GET
    @Path("getPackageByPrice/{price}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Package> getPackageByPrice(@PathParam("price") Integer price){
        return abl.getPackageByPrice(price);
    }
    
    @RolesAllowed("Admin")
    @GET
    @Path("getPackageByTransportationType/{transportationtype}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Package> getPackageByTransportationType(@PathParam("transportationtype") String transportationtype){
        return abl.getPackageByTransportationType(transportationtype);
    }
    
    @RolesAllowed("Admin")
    @GET
    @Path("getPackageByCompany/{cid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Package> getPackageByCompany(@PathParam("cid") Integer cid){
        return abl.getPackageByCompany(cid);
    }
    
    @RolesAllowed("Admin")
    @GET
    @Path("getPackageById/{pid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Package getPackageById(@PathParam("pid") Integer pid){
        return abl.getPackageById(pid);
    }
    
//    ==> Accommodation
    
    @RolesAllowed("Admin")
    @POST
    @Path("addAccommodation/{name}/{country}/{state}/{city}/{address}/{description}/{roomNumber}/{type}/{capacity}/{price}")
    public boolean addAccommodation(@PathParam("name") String name,@PathParam("country") String country,@PathParam("state") String state,@PathParam("city") String city,@PathParam("address") String address,@PathParam("description") String description,@PathParam("roomNumber") String roomNumber,@PathParam("type") String type,@PathParam("capacity") Integer capacity,@PathParam("price") Integer price){
        return abl.addAccommodation(name, country, state, city, address, description, roomNumber, type, capacity, price);
    }
    
    
    @RolesAllowed("Admin")
    @POST
    @Path("updateAccommodation/{aid}/{name}/{country}/{state}/{city}/{address}/{description}/{roomNumber}/{type}/{capacity}/{price}")
    public boolean updateAccommodation(@PathParam("aid") Integer aid,@PathParam("name") String name,@PathParam("country") String country,@PathParam("state") String state,@PathParam("city") String city,@PathParam("address") String address,@PathParam("description") String description,@PathParam("roomNumber") String roomNumber,@PathParam("type") String type,@PathParam("capacity") Integer capacity,@PathParam("price") Integer price){
        return abl.updateAccommodation(aid, name, country, state, city, address, description, roomNumber, type, capacity, price);
    }
    
    @RolesAllowed("Admin")
    @DELETE
    @Path("removeAccommodation/{aid}")
    public boolean removeAccommodation(@PathParam("aid") Integer aid){
        return abl.removeAccommodation(aid);
    }
    
    @RolesAllowed("Admin")
    @GET
    @Path("getAllAccommodation")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Accommodation> getAllAccommodation(){
        return abl.getAllAccommodation();
    }
    
//    ==> Booking
    
    @RolesAllowed("Admin")
    @GET
    @Path("getAllBooking")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Booking> getAllBooking(){
        return abl.getAllBooking();
    }
    
//    ==> Payment

    @RolesAllowed("Admin")
    @GET
    @Path("getAllPayments")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Payment> getAllPayments(){
        return abl.getAllPayments();
    }
    
//    ==> Feedback
    
    @RolesAllowed("Admin")
    @GET
    @Path("getAllFeedback")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Feedback> getAllFeedback(){
        return abl.getAllFeedback();
    }
    
//   ====>      ----: Users APIs :----
    
    @RolesAllowed("User")
    @POST
    @Path("updateProfile/{username}/{email}/{password}/{gender}/{photo}/{dob}/{phoneno}/{address}")
    public boolean updateProfile(@PathParam("username") String username,@PathParam("email") String email,@PathParam("password") String password,@PathParam("gender") String gender,@PathParam("photo") String photo,@PathParam("dob") Date dob,@PathParam("phoneno") BigInteger phoneno,@PathParam("address") String address){
        return ubl.updateProfile(username, email, password, gender, photo, dob, phoneno, address);
    }
    
    @RolesAllowed("User")
    @DELETE
    @Path("removeProfile/{username}")
    public boolean removeProfile(@PathParam("username") String username){
        return ubl.removeProfile(username);
    }
    
    @RolesAllowed("User")
    @GET
    @Path("MyProfile/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Users MyProfile(@PathParam("username") String username){
        return ubl.MyProfile(username);
    }
    
    @RolesAllowed("User")
    @POST
    @Path("addBooking/{username}/{pid}/{bookingdate}/{traveldate}/{numbersoftravelers}/{totalamount}/{status}/{aid}")
    public boolean addBooking(@PathParam("username") String username,@PathParam("pid") Integer pid,@PathParam("bookingdate") Date bookingdate,@PathParam("traveldate") Date traveldate,@PathParam("numbersoftravelers") Integer numbersoftravelers,@PathParam("totalamount") BigInteger totalamount,@PathParam("status") String status,@PathParam("aid") Integer aid){
        return ubl.addBooking(username, pid, bookingdate, traveldate, numbersoftravelers, totalamount, status, aid);
    }
    
    
    @RolesAllowed("User")
    @POST
    @Path("updateBooking/{bid}/{username}/{pid}/{bookingdate}/{traveldate}/{numbersoftravelers}/{totalamount}/{status}/{aid}")
    public boolean updateBooking(@PathParam("bid") Integer bid,@PathParam("username") String username,@PathParam("pid") Integer pid,@PathParam("bookingdate") Date bookingdate,@PathParam("traveldate") Date traveldate,@PathParam("numbersoftravelers") Integer numbersoftravelers,@PathParam("totalamount") BigInteger totalamount,@PathParam("status") String status,@PathParam("aid") Integer aid){
        return ubl.updateBooking(bid, username, pid, bookingdate, traveldate, numbersoftravelers, totalamount, status, aid);
    }
    
    @RolesAllowed("User")
    @DELETE
    @Path("removeBooking/{bid}")
    public boolean removeBooking(@PathParam("bid") Integer bid){
        return ubl.removeBooking(bid);
    }
    
    @RolesAllowed("User")
    @GET
    @Path("getBookingByUser/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Booking> getBookingByUser(@PathParam("username") String username){
        return ubl.getBookingByUser(username);
    }
    
    @RolesAllowed("User")
    @POST
    @Path("addFeedback/{username}/{bid}/{rating}/{review}/{date}")
    public boolean addFeedback(@PathParam("username") String username,@PathParam("bid")Integer bid,@PathParam("rating") Integer rating,@PathParam("review") String review,@PathParam("date") Date date){
        return ubl.addFeedback(username, bid, rating, review, date);
    }
}