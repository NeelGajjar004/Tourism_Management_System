package com.mycompany.TourismAdvisor.resources;

import ejb.crudejbLocal;
import entity.Company;
import entity.Groups;
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
    
    @EJB crudejbLocal cel;
    
    //    ====>Users
    @POST
    @Path("addusers/{username}/{email}/{password}/{gender}/{photo}/{dob}/{phoneno}/{address}")
    public void addUsers(@PathParam("username")String username,@PathParam("email")String email,@PathParam("password")String password,@PathParam("gender")String gender,@PathParam("photo")String photo,@PathParam("dob")Date dob,@PathParam("phoneno")BigInteger phoneno,@PathParam("address")String address)
    {
        cel.addUsers(username, email, password, gender, photo, dob, phoneno, address);
    }
    
    @POST
    @Path("updateusers/{username}/{email}/{password}/{gender}/{photo}/{dob}/{phoneno}/{address}")
    public void updateUsers(@PathParam("username")String username,@PathParam("email")String email,@PathParam("password")String password,@PathParam("gender")String gender,@PathParam("photo")String photo,@PathParam("dob")Date dob,@PathParam("phoneno")BigInteger phoneno,@PathParam("address")String address)
    {
        cel.updateUsers(username, email, password, gender, photo, dob, phoneno, address);
    }
    
    @DELETE
    @Path("removeusers/{username}")
    public void removeUsers(@PathParam("username")String username)
    {
        cel.removeUsers(username);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Users> getAllUsers()
    {
        return cel.getAllUsers();
    }
    
//    ====>Groups
    @POST
    @Path("addgroups/{groupname}/{username}")
    public void addGroups(@PathParam("groupname")String groupname,@PathParam("username")String username)
    {
        cel.addGroups(groupname, username);
    }
    
    @POST
    @Path("updategroups/{groupid}/{groupname}/{username}")
    public void updateGroups(@PathParam("groupid")int groupid,@PathParam("groupname")String groupname,@PathParam("username")String username)
    {
        cel.updateGroups(groupid, groupname, username);
    }
    
    @DELETE
    @Path("removegroups/{groupid}")
    public void removeGroups(@PathParam("groupid")int groupid)
    {
        cel.removeGroups(groupid);
    }
    
    @GET
    @Path("allgroups")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Groups> getAllGroups()
    {
        return cel.getAllGroups();
    }
    
    @GET
    @Path("getuserbygroup/{groupname}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Groups> getUsersByGroupName(@PathParam("groupname")String groupname)
    {
        return cel.getUsersByGroupName(groupname);
    }
    
    @GET
    @Path("getgroupbyuser/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Groups> getGroupNameByUser(@PathParam("username")String username)
    {
        return cel.getGroupNameByUser(username);
    }
    
    
//    ====>Company
    @POST
    @Path("addcompany/{cname}/{website}/{city}")
    public void addCompany(@PathParam("cname")String cname,@PathParam("website")String website,@PathParam("city")String city)
    {
        cel.addCompany(cname, website, city);
    }
    
    @POST
    @Path("updatecompany/{cid}/{cname}/{website}/{city}")
    public void updateCompany(@PathParam("cid")int cid,@PathParam("cname")String cname,@PathParam("website")String website,@PathParam("city")String city)
    {
        cel.updateCompany(cid, cname, website, city);
    }
    
    @DELETE
    @Path("removecompany/{cid}")
    public void removeCompany(@PathParam("cid")int cid)
    {
        cel.removeCompany(cid);
    }
    
    @GET
    @Path("allcompany")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Company> getAllCompany()
    {
        return cel.getAllCompany();
    }
    
//    ====>Package
    @POST
    @Path("addpackage/{pname}/{destination}/{description}/{startdate}/{enddate}/{price}/{transportationtype}/{photos}/{cid}")
    public void addPackage(@PathParam("pname")String pname,@PathParam("destination")String destination,@PathParam("description")String description,@PathParam("startdate")Date startdate,@PathParam("enddate")Date enddate,@PathParam("price")int price,@PathParam("transportationtype")String transportationtype,@PathParam("photos")String photos,@PathParam("cid")int cid)
    {
        cel.addPackage(pname, destination, description, startdate, enddate, price, transportationtype, photos, cid);
    }
    
    @POST
    @Path("updatepackage/{pid}/{pname}/{destination}/{description}/{startdate}/{enddate}/{price}/{transportationtype}/{photos}/{cid}")
    public void updatePackage(@PathParam("pid")int pid,@PathParam("pname")String pname,@PathParam("destination")String destination,@PathParam("description")String description,@PathParam("startdate")Date startdate,@PathParam("enddate")Date enddate,@PathParam("price")int price,@PathParam("transportationtype")String transportationtype,@PathParam("photos")String photos,@PathParam("cid")int cid)
    {
        cel.updatePackage(pid, pname, destination, description, startdate, enddate, price, transportationtype, photos, cid);
    }
    
    @DELETE
    @Path("removepackage/{pid}")
    public void removePackage(@PathParam("pid")int pid)
    {
        cel.removePackage(pid);
    }
    
    @GET
    @Path("allpackage")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<entity.Package> getAllPackage()
    {
        return cel.getAllPackage();
    }
    
    @GET
    @Path("getpackbydest/{destination}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<entity.Package> getPackageByDestination(@PathParam("destination")String destination)
    {
        return cel.getPackageByDestination(destination);
    }
    
    @GET
    @Path("getpackbyprice/{price}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<entity.Package> getPackageByPrice(@PathParam("price")int price)
    {
        return cel.getPackageByPrice(price);
    }
    
    @GET
    @Path("getpackbytrantype/{transportationtype}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<entity.Package> getPackageByTransportationType(@PathParam("transportationtype")String transportationtype)
    {
        return cel.getPackageByTransportationType(transportationtype);
    }
    
    @GET
    @Path("getpackbycomp/{cid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<entity.Package> getPackageByCompany(@PathParam("cid")int cid)
    {
        return cel.getPackageByCompany(cid);
    }
    
}
