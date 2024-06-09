package com.mycompany.TourismAdvisor.resources;

import ejb.AdminBeanLocal;
import ejb.UserBeanLocal;
import ejb.authBeanLocal;
import ejb.crudejbLocal;
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
@Path("rest")
public class JakartaEE8Resource {
    
//    @EJB crudejbLocal cel;
    @EJB AdminBeanLocal abl;
    @EJB UserBeanLocal ubl;
    @EJB authBeanLocal aubl;
    
//   ====>      ----: Authentication APIs :----
    
    @GET
    @Path("checkUserName/{username}")
//    @Produces(MediaType.APPLICATION_JSON)
    public boolean checkUserName(@PathParam("username") String username){
        return aubl.checkUserName(username);
    }
    
    @GET
    @Path("checkUserEmail/{email}")
//    @Produces(MediaType.APPLICATION_JSON)
    public boolean checkUserEmail(@PathParam("email") String email){
        return aubl.checkUserEmail(email);
    }
    
    
    @POST
    @Path("Register/{username}/{email}/{password}/{gender}/{photo}/{dob}/{phoneno}/{address}")
    public boolean Register(@PathParam("username") String username,@PathParam("email") String email,@PathParam("password")String password,@PathParam("gender") String gender,@PathParam("photo") String photo,@PathParam("dob") Date dob,@PathParam("phoneno") BigInteger phoneno,@PathParam("address") String address){
        return aubl.Register(username, email, password, gender, photo, dob, phoneno, address);
    }
    
    @GET
    @Path("Login/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Users Login(@PathParam("username") String username){
        return aubl.Login(username);
    }
    
    @POST
    @Path("authaddGroups/{groupname}/{username}")
    public boolean authaddGroups(@PathParam("groupname") String groupname,@PathParam("username") String username){
        return aubl.addGroups(groupname, username);
    }
    
    
//   ====>      ----: Admin APIs :----
    
    //    ==> Users
    
    @POST
    @Path("addUser/{username}/{email}/{password}/{gender}/{photo}/{dob}/{phoneno}/{address}")
    public boolean addUser(@PathParam("username") String username,@PathParam("email") String email,@PathParam("password") String password,@PathParam("gender") String gender,@PathParam("photo") String photo,@PathParam("dob") Date dob,@PathParam("phoneno") BigInteger phoneno,@PathParam("address") String address){
        return abl.addUser(username, email, password, gender, photo, dob, phoneno, address);
    }
    
    @POST
    @Path("updateUser/{username}/{email}/{password}/{gender}/{photo}/{dob}/{phoneno}/{address}")
    public boolean updateUser(@PathParam("username") String username,@PathParam("email") String email,@PathParam("password") String password,@PathParam("gender") String gender,@PathParam("photo") String photo,@PathParam("dob") Date dob,@PathParam("phoneno") BigInteger phoneno,@PathParam("address") String address){
        return abl.updateUser(username, email, password, gender, photo, dob, phoneno, address);
    }
    
    @DELETE
    @Path("removeUser/{username}")
    public boolean removeUser(@PathParam("username") String username)
    {
        return abl.removeUser(username);
    }
    
    @GET
    @Path("getAllUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Users> getAllUsers(){
        return abl.getAllUsers();
    }
    
    @GET
    @Path("getUserByusername/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Users getUserByusername(@PathParam("username") String username){
        return abl.getUserByusername(username);
    }
    
//    ==> Groups
    
    @POST
    @Path("addGroups/{groupname}/{username}")
    public boolean addGroups(@PathParam("groupname") String groupname,@PathParam("username") String username){
        return abl.addGroups(groupname, username);
    }
    
    
    @POST
    @Path("updateGroups/{groupid}/{groupname}/{username}")
    public boolean updateGroups(Integer groupid,String groupname,String username){
        return abl.updateGroups(groupid, groupname, username);
    }
    
    
    @DELETE
    @Path("removeGroups/{groupid}")
    public boolean removeGroups(Integer groupid){
        return abl.removeGroups(groupid);
    }
    
    @GET
    @Path("getAllGroups")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Groups> getAllGroups(){
        return abl.getAllGroups();
    }
    
    @GET
    @Path("getUsersByGroupName/{groupname}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Groups> getUsersByGroupName(@PathParam("groupname") String groupname){
        return abl.getUsersByGroupName(groupname);
    }
    
    @GET
    @Path("getGroupNameByUser/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Groups> getGroupNameByUser(@PathParam("username") String username){
        return abl.getGroupNameByUser(username);
    }
    
    @GET
    @Path("getGroupByID/{groupid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Groups getGroupByID(@PathParam("groupid") Integer groupid){
        return abl.getGroupByID(groupid);
    }
    
    
//    ==> Company
    
    @POST
    @Path("addCompany/{cname}/{website}/{city}")
    public boolean addCompany(@PathParam("cname") String cname,@PathParam("website") String website,@PathParam("city") String city){
        return abl.addCompany(cname, website, city);
    }
    
    @POST
    @Path("updateCompany/{cid}/{cname}/{website}/{city}")
    public boolean updateCompany(@PathParam("cid") Integer cid,@PathParam("cname") String cname,@PathParam("website") String website,@PathParam("city") String city){
        return abl.updateCompany(cid, cname, website, city);
    }
    
    @DELETE
    @Path("removeCompany/{cid}")
    public boolean removeCompany(@PathParam("cid") Integer cid){
        return abl.removeCompany(cid);
    }
    
    @GET
    @Path("getAllCompany")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Company> getAllCompany(){
        return abl.getAllCompany();
    }
    
    @GET
    @Path("getCompanyByName/{cname}")
    @Produces(MediaType.APPLICATION_JSON)
    public Company getCompanyByName(@PathParam("cname") String cname){
        return abl.getCompanyByName(cname);
    }
    
    @GET
    @Path("getCompanyByName/{cid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Company getCompanyById(@PathParam("cid") Integer cid){
        return abl.getCompanyById(cid);
    }
    
//    ==> Package
    
    @POST
    @Path("addPackage/{pname}/{destination}/{description}/{startdate}/{enddate}/{price}/{transportationtype}/{photos}/{cid}")
    public boolean addPackage(@PathParam("pname") String pname,@PathParam("destination") String destination,@PathParam("description") String description,@PathParam("startdate") Date startdate,@PathParam("enddate") Date enddate,@PathParam("price") Integer price,@PathParam("transportationtype") String transportationtype,@PathParam("photos") String photos,@PathParam("cid") Integer cid){
        return abl.addPackage(pname, destination, description, startdate, enddate, price, transportationtype, photos, cid);
    }
    
    
    @POST
    @Path("updatePackage/{pid}/{pname}/{destination}/{description}/{startdate}/{enddate}/{price}/{transportationtype}/{photos}/{cid}")
    public boolean updatePackage(@PathParam("pid") Integer pid,@PathParam("pname") String pname,@PathParam("destination") String destination,@PathParam("description") String description,@PathParam("startdate") Date startdate,@PathParam("enddate") Date enddate,@PathParam("price") Integer price,@PathParam("transportationtype") String transportationtype,@PathParam("photos") String photos,@PathParam("cid") Integer cid){
        return abl.updatePackage(pid, pname, destination, description, startdate, enddate, price, transportationtype, photos, cid);
    }
    
    @DELETE
    @Path("removePackage/{pid}")
    public boolean removePackage(@PathParam("pid") Integer pid){
        return abl.removePackage(pid);
    }
    
    @GET
    @Path("getAllPackage")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Package> getAllPackage(){
        return abl.getAllPackage();
    }
    
    @GET
    @Path("getPackageByDestination/{destination}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Package> getPackageByDestination(@PathParam("destination") String destination){
        return abl.getPackageByDestination(destination);
    }
    
    @GET
    @Path("getPackageByPrice/{price}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Package> getPackageByPrice(@PathParam("price") Integer price){
        return abl.getPackageByPrice(price);
    }
    
    @GET
    @Path("getPackageByTransportationType/{transportationtype}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Package> getPackageByTransportationType(@PathParam("transportationtype") String transportationtype){
        return abl.getPackageByTransportationType(transportationtype);
    }
    
    @GET
    @Path("getPackageByCompany/{cid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Package> getPackageByCompany(@PathParam("cid") Integer cid){
        return abl.getPackageByCompany(cid);
    }
    
    @GET
    @Path("getPackageById/{pid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Package getPackageById(@PathParam("pid") Integer pid){
        return abl.getPackageById(pid);
    }
    
//    ==> Accommodation
    
    @POST
    @Path("addAccommodation/{name}/{country}/{state}/{city}/{address}/{description}/{roomNumber}/{type}/{capacity}/{price}")
    public boolean addAccommodation(@PathParam("name") String name,@PathParam("country") String country,@PathParam("state") String state,@PathParam("city") String city,@PathParam("address") String address,@PathParam("description") String description,@PathParam("roomNumber") String roomNumber,@PathParam("type") String type,@PathParam("capacity") Integer capacity,@PathParam("price") Integer price){
        return abl.addAccommodation(name, country, state, city, address, description, roomNumber, type, capacity, price);
    }
    
    
    @POST
    @Path("updateAccommodation/{aid}/{name}/{country}/{state}/{city}/{address}/{description}/{roomNumber}/{type}/{capacity}/{price}")
    public boolean updateAccommodation(@PathParam("aid") Integer aid,@PathParam("name") String name,@PathParam("country") String country,@PathParam("state") String state,@PathParam("city") String city,@PathParam("address") String address,@PathParam("description") String description,@PathParam("roomNumber") String roomNumber,@PathParam("type") String type,@PathParam("capacity") Integer capacity,@PathParam("price") Integer price){
        return abl.updateAccommodation(aid, name, country, state, city, address, description, roomNumber, type, capacity, price);
    }
    
    @DELETE
    @Path("removeAccommodation/{aid}")
    public boolean removeAccommodation(@PathParam("aid") Integer aid){
        return abl.removeAccommodation(aid);
    }
    
    @GET
    @Path("getAllAccommodation")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Accommodation> getAllAccommodation(){
        return abl.getAllAccommodation();
    }
    
//    ==> Booking
    
    @GET
    @Path("getAllBooking")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Booking> getAllBooking(){
        return abl.getAllBooking();
    }
    
//    ==> Payment

    @GET
    @Path("getAllPayments")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Payment> getAllPayments(){
        return abl.getAllPayments();
    }
    
//    ==> Feedback
    
    @GET
    @Path("getAllFeedback")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Feedback> getAllFeedback(){
        return abl.getAllFeedback();
    }
    
//   ====>      ----: Users APIs :----
    
    @POST
    @Path("updateProfile/{username}/{email}/{password}/{gender}/{photo}/{dob}/{phoneno}/{address}")
    public boolean updateProfile(@PathParam("username") String username,@PathParam("email") String email,@PathParam("password") String password,@PathParam("gender") String gender,@PathParam("photo") String photo,@PathParam("dob") Date dob,@PathParam("phoneno") BigInteger phoneno,@PathParam("address") String address){
        return ubl.updateProfile(username, email, password, gender, photo, dob, phoneno, address);
    }
    
    @DELETE
    @Path("removeProfile/{username}")
    public boolean removeProfile(@PathParam("username") String username){
        return ubl.removeProfile(username);
    }
    
    @GET
    @Path("MyProfile/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Users MyProfile(@PathParam("username") String username){
        return ubl.MyProfile(username);
    }
    
    @POST
    @Path("addBooking/{username}/{pid}/{bookingdate}/{traveldate}/{numbersoftravelers}/{totalamount}/{status}/{aid}")
    public boolean addBooking(@PathParam("username") String username,@PathParam("pid") Integer pid,@PathParam("bookingdate") Date bookingdate,@PathParam("traveldate") Date traveldate,@PathParam("numbersoftravelers") Integer numbersoftravelers,@PathParam("totalamount") BigInteger totalamount,@PathParam("status") String status,@PathParam("aid") Integer aid){
        return ubl.addBooking(username, pid, bookingdate, traveldate, numbersoftravelers, totalamount, status, aid);
    }
    
    
    @POST
    @Path("updateBooking/{bid}/{username}/{pid}/{bookingdate}/{traveldate}/{numbersoftravelers}/{totalamount}/{status}/{aid}")
    public boolean updateBooking(@PathParam("bid") Integer bid,@PathParam("username") String username,@PathParam("pid") Integer pid,@PathParam("bookingdate") Date bookingdate,@PathParam("traveldate") Date traveldate,@PathParam("numbersoftravelers") Integer numbersoftravelers,@PathParam("totalamount") BigInteger totalamount,@PathParam("status") String status,@PathParam("aid") Integer aid){
        return ubl.updateBooking(bid, username, pid, bookingdate, traveldate, numbersoftravelers, totalamount, status, aid);
    }
    
    @DELETE
    @Path("removeBooking/{bid}")
    public boolean removeBooking(@PathParam("bid") Integer bid){
        return ubl.removeBooking(bid);
    }
    
    @GET
    @Path("getBookingByUser/{username}")
    public Collection<Booking> getBookingByUser(@PathParam("username") String username){
        return ubl.getBookingByUser(username);
    }
    
    @POST
    @Path("addFeedback/{username}/{bid}/{rating}/{review}/{date}")
    public boolean addFeedback(String username,Integer bid,Integer rating,String review,Date date){
        return ubl.addFeedback(username, bid, rating, review, date);
    }
    
    
    
    
    
}