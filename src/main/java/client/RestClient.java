/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/JerseyClient.java to edit this template
 */
package client;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:JakartaEE8Resource [rest]<br>
 * USAGE:
 * <pre>
 *        RestClient client = new RestClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Admin
 */
public class RestClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/TourismAdvisor/resources";

    public RestClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("rest");
    }

    public <T> T getAllCompany(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllCompany");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllGroups(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllGroups");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T removeAccommodation(Class<T> responseType, String aid) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("removeAccommodation/{0}", new Object[]{aid})).request().delete(responseType);
    }

    public <T> T removePackage(Class<T> responseType, String pid) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("removePackage/{0}", new Object[]{pid})).request().delete(responseType);
    }

    public <T> T updateProfile(Class<T> responseType, String username, String email, String password, String gender, String photo, String dob, String phoneno, String address) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("updateProfile/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}", new Object[]{username, email, password, gender, photo, dob, phoneno, address})).request().post(null, responseType);
    }

    public <T> T addFeedback(Class<T> responseType, String username, String bid, String rating, String review, String date) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("addFeedback/{0}/{1}/{2}/{3}/{4}", new Object[]{username, bid, rating, review, date})).request().post(null, responseType);
    }

    public <T> T addUser(Class<T> responseType, String username, String email, String password, String gender, String photo, String dob, String phoneno, String address) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("addUser/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}", new Object[]{username, email, password, gender, photo, dob, phoneno, address})).request().post(null, responseType);
    }

    public <T> T addPackage(Class<T> responseType, String pname, String destination, String description, String startdate, String enddate, String price, String transportationtype, String photos, String cid) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("addPackage/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}/{8}", new Object[]{pname, destination, description, startdate, enddate, price, transportationtype, photos, cid})).request().post(null, responseType);
    }

    public <T> T updatePackage(Class<T> responseType, String pid, String pname, String destination, String description, String startdate, String enddate, String price, String transportationtype, String photos, String cid) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("updatePackage/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}/{8}/{9}", new Object[]{pid, pname, destination, description, startdate, enddate, price, transportationtype, photos, cid})).request().post(null, responseType);
    }

    public <T> T updateCompany(Class<T> responseType, String cid, String cname, String website, String city) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("updateCompany/{0}/{1}/{2}/{3}", new Object[]{cid, cname, website, city})).request().post(null, responseType);
    }

    public <T> T getPackageByDestination(Class<T> responseType, String destination) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getPackageByDestination/{0}", new Object[]{destination}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllPackage(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllPackage");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T updateAccommodation(Class<T> responseType, String aid, String name, String country, String state, String city, String address, String description, String roomNumber, String type, String capacity, String price) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("updateAccommodation/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}/{8}/{9}/{10}", new Object[]{aid, name, country, state, city, address, description, roomNumber, type, capacity, price})).request().post(null, responseType);
    }

    public <T> T getUsersByGroupName(Class<T> responseType, String groupname) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getUsersByGroupName/{0}", new Object[]{groupname}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T removeUser(Class<T> responseType, String username) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("removeUser/{0}", new Object[]{username})).request().delete(responseType);
    }

    public <T> T removeProfile(Class<T> responseType, String username) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("removeProfile/{0}", new Object[]{username})).request().delete(responseType);
    }

    public <T> T getBookingByUser(Class<T> responseType, String username) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getBookingByUser/{0}", new Object[]{username}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T addAccommodation(Class<T> responseType, String name, String country, String state, String city, String address, String description, String roomNumber, String type, String capacity, String price) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("addAccommodation/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}/{8}/{9}", new Object[]{name, country, state, city, address, description, roomNumber, type, capacity, price})).request().post(null, responseType);
    }

    public <T> T getAllFeedback(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllFeedback");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T checkUserName(Class<T> responseType, String username) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("checkUserName/{0}", new Object[]{username}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getGroupByID(Class<T> responseType, String groupid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getGroupByID/{0}", new Object[]{groupid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T checkUserEmail(Class<T> responseType, String email) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("checkUserEmail/{0}", new Object[]{email}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T addGroups(Class<T> responseType, String groupname, String username) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("addGroups/{0}/{1}", new Object[]{groupname, username})).request().post(null, responseType);
    }

    public <T> T updateGroups(Class<T> responseType, String groupid, String groupname, String username) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("updateGroups/{0}/{1}/{2}", new Object[]{groupid, groupname, username})).request().post(null, responseType);
    }

    public <T> T addBooking(Class<T> responseType, String username, String pid, String bookingdate, String traveldate, String numbersoftravelers, String totalamount, String status, String aid) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("addBooking/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}", new Object[]{username, pid, bookingdate, traveldate, numbersoftravelers, totalamount, status, aid})).request().post(null, responseType);
    }

    public <T> T updateBooking(Class<T> responseType, String bid, String username, String pid, String bookingdate, String traveldate, String numbersoftravelers, String totalamount, String status, String aid) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("updateBooking/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}/{8}", new Object[]{bid, username, pid, bookingdate, traveldate, numbersoftravelers, totalamount, status, aid})).request().post(null, responseType);
    }

    public <T> T getAllUsers(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllUsers");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getUserByusername(Class<T> responseType, String username) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getUserByusername/{0}", new Object[]{username}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getPackageByCompany(Class<T> responseType, String cid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getPackageByCompany/{0}", new Object[]{cid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllPayments(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllPayments");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getGroupNameByUser(Class<T> responseType, String username) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getGroupNameByUser/{0}", new Object[]{username}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T updateUser(Class<T> responseType, String username, String email, String password, String gender, String photo, String dob, String phoneno, String address) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("updateUser/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}", new Object[]{username, email, password, gender, photo, dob, phoneno, address})).request().post(null, responseType);
    }

    public <T> T removeBooking(Class<T> responseType, String bid) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("removeBooking/{0}", new Object[]{bid})).request().delete(responseType);
    }

    public <T> T Login(Class<T> responseType, String username) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("Login/{0}", new Object[]{username}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getCompanyByName(Class<T> responseType, String cname) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getCompanyByName/{0}", new Object[]{cname}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllBooking(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllBooking");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllAccommodation(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllAccommodation");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T authaddGroups(Class<T> responseType, String groupname, String username) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("authaddGroups/{0}/{1}", new Object[]{groupname, username})).request().post(null, responseType);
    }

    public <T> T removeCompany(Class<T> responseType, String cid) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("removeCompany/{0}", new Object[]{cid})).request().delete(responseType);
    }

    public <T> T removeGroups(Class<T> responseType, String groupid) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("removeGroups/{0}", new Object[]{groupid})).request().delete(responseType);
    }

    public <T> T getPackageByTransportationType(Class<T> responseType, String transportationtype) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getPackageByTransportationType/{0}", new Object[]{transportationtype}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T Register(Class<T> responseType, String username, String email, String password, String gender, String photo, String dob, String phoneno, String address) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("Register/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}", new Object[]{username, email, password, gender, photo, dob, phoneno, address})).request().post(null, responseType);
    }

    public <T> T getPackageByPrice(Class<T> responseType, String price) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getPackageByPrice/{0}", new Object[]{price}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T MyProfile(Class<T> responseType, String username) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("MyProfile/{0}", new Object[]{username}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T addCompany(Class<T> responseType, String cname, String website, String city) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("addCompany/{0}/{1}/{2}", new Object[]{cname, website, city})).request().post(null, responseType);
    }

    public <T> T getCompanyById(Class<T> responseType, String cid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getCompanyByName/{0}", new Object[]{cid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getPackageById(Class<T> responseType, String pid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getPackageById/{0}", new Object[]{pid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
