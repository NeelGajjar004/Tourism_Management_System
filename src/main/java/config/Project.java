/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

/**
 *
 * @author Admin
 */
@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "jdbc/tms",
        callerQuery = "select password from users where username = ?",
        groupsQuery = "select groupname from groups where username = ?",
        hashAlgorithm = Pbkdf2PasswordHash.class,
        priority = 30
)

//@ApplicationScoped
@SessionScoped
public class Project implements Serializable{
    
}
