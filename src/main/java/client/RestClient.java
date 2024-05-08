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

    public void addGroups(String groupname, String username) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addgroups/{0}/{1}", new Object[]{groupname, username})).request().post(null);
    }

    public void updateGroups(String groupid, String groupname, String username) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updategroups/{0}/{1}/{2}", new Object[]{groupid, groupname, username})).request().post(null);
    }

    public <T> T getAllCompany(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("allcompany");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllUsers(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllGroups(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("allgroups");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getPackageByCompany(Class<T> responseType, String cid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getpackbycomp/{0}", new Object[]{cid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void removePackage(String pid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("removepackage/{0}", new Object[]{pid})).request().delete();
    }

    public <T> T getGroupNameByUser(Class<T> responseType, String username) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getgroupbyuser/{0}", new Object[]{username}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addPackage(String pname, String destination, String description, String startdate, String enddate, String price, String transportationtype, String photos, String cid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addpackage/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}/{8}", new Object[]{pname, destination, description, startdate, enddate, price, transportationtype, photos, cid})).request().post(null);
    }

    public void updatePackage(String pid, String pname, String destination, String description, String startdate, String enddate, String price, String transportationtype, String photos, String cid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updatepackage/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}/{8}/{9}", new Object[]{pid, pname, destination, description, startdate, enddate, price, transportationtype, photos, cid})).request().post(null);
    }

    public void updateCompany(String cid, String cname, String website, String city) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updatecompany/{0}/{1}/{2}/{3}", new Object[]{cid, cname, website, city})).request().post(null);
    }

    public <T> T getPackageByDestination(Class<T> responseType, String destination) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getpackbydest/{0}", new Object[]{destination}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllPackage(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("allpackage");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addUsers(String username, String email, String password, String gender, String photo, String dob, String phoneno, String address) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addusers/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}", new Object[]{username, email, password, gender, photo, dob, phoneno, address})).request().post(null);
    }

    public <T> T getUsersByGroupName(Class<T> responseType, String groupname) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getuserbygroup/{0}", new Object[]{groupname}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void removeCompany(String cid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("removecompany/{0}", new Object[]{cid})).request().delete();
    }

    public void removeGroups(String groupid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("removegroups/{0}", new Object[]{groupid})).request().delete();
    }

    public <T> T getPackageByTransportationType(Class<T> responseType, String transportationtype) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getpackbytrantype/{0}", new Object[]{transportationtype}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getPackageByPrice(Class<T> responseType, String price) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getpackbyprice/{0}", new Object[]{price}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void removeUsers(String username) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("removeusers/{0}", new Object[]{username})).request().delete();
    }

    public void addCompany(String cname, String website, String city) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addcompany/{0}/{1}/{2}", new Object[]{cname, website, city})).request().post(null);
    }

    public void updateUsers(String username, String email, String password, String gender, String photo, String dob, String phoneno, String address) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateusers/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}", new Object[]{username, email, password, gender, photo, dob, phoneno, address})).request().post(null);
    }

    public void close() {
        client.close();
    }
    
}
