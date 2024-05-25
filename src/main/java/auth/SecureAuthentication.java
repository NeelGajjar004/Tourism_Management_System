/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auth;

import static JsonWebToken.Constants.AUTHORIZATION_HEADER;
import static JsonWebToken.Constants.BEARER;
import JsonWebToken.JWTCredential;
import JsonWebToken.TokenProvider;
import io.jsonwebtoken.ExpiredJwtException;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationException;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStoreHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import record.KeepRecord;

/**
 *
 * @author Admin
 */
@Named
@RequestScoped
public class SecureAuthentication implements HttpAuthenticationMechanism, Serializable {
    
    @Inject
    IdentityStoreHandler handler;
    CredentialValidationResult result;
    AuthenticationStatus status;
    
    @Inject TokenProvider tokenProvider;
//    @Inject LoginBean lb;
    
    

    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest request, HttpServletResponse response, HttpMessageContext ctx) throws AuthenticationException{
//        
//        try{
//            if(request.getRequestURI().contains("logout")){
//                request.logout();
//                KeepRecord.reset();
//                response.sendRedirect("login.jsf");
//                return ctx.doNothing();
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        
//        String token = extractToken(ctx);
//        try{
//            request.setCharacterEncoding("UTF-8");
//            System.out.println("==> Username := " + request.getParameter("username"));
//            
//            if(token == null && request.getParameter("username") != null){
//                String username = request.getParameter("username");
//                String password = request.getParameter("password");
//                
//                System.out.println("=> In Auth - ");
//                
//                Credential credential = new UsernamePasswordCredential(username, new Password(password));
//                result = handler.validate(credential);
//                
//                if(result.getStatus() == CredentialValidationResult.Status.VALID){
//                    KeepRecord.setErrorStatus("");
//                    AuthenticationStatus status = createToken(result,ctx);
//                    status = ctx.notifyContainerAboutLogin(result);
//                    KeepRecord.setUsername(username);
//                    KeepRecord.setPassword(password);
//                    
//                    KeepRecord.setPrincipal(result.getCallerPrincipal());
//                    KeepRecord.setRoles(result.getCallerGroups());
//                    KeepRecord.setCredential(credential);
//                    
//                    if(result.getCallerGroups().contains("Admin")){
//                        request.getRequestDispatcher("Admin/Company/addCompany.jsf").forward(request, response);
//                    }
//                    if(result.getCallerGroups().contains("Supervisor")){
//                        request.getRequestDispatcher("Users/UserMaster.jsf").forward(request, response);
//                    }
//                    
//                    return status;
//                }else{
//                    KeepRecord.setErrorStatus("Either Username or Password is Wrong..!");
//                    response.sendRedirect("login.jsf");
//                    return ctx.doNothing();
//                }
//            }
//                
//            if(KeepRecord.getToken() != null){
//                    ctx.notifyContainerAboutLogin(KeepRecord.getPrincipal(),KeepRecord.getRoles());
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        
//        if(token != null){
//            return validateToken(token,ctx);
//        }else if(ctx.isProtected()){
//            return ctx.responseUnauthorized();
//        }
      return ctx.doNothing();
    }
    
    private String extractToken(HttpMessageContext context) {
        String authorizationHeader = context.getRequest().getHeader(AUTHORIZATION_HEADER);
        if(authorizationHeader != null && authorizationHeader.startsWith(BEARER)){
            String token = authorizationHeader.substring(BEARER.length(),authorizationHeader.length());
            KeepRecord.setToken(token);
            System.out.println("AuthenticationMechenism - In Extract Token Method");
            return token;
        }
        return null;
    }

    private AuthenticationStatus createToken(CredentialValidationResult result, HttpMessageContext context) {
        String jwt = tokenProvider.createToken(result.getCallerPrincipal().getName(), result.getCallerGroups(), false);
        KeepRecord.setToken(jwt);
        context.getResponse().addHeader(AUTHORIZATION_HEADER, BEARER + jwt);
        System.out.println("AuthenticationMechenism - In Create Token Method");
        System.out.println("Token :- " + jwt);
        return context.notifyContainerAboutLogin(result.getCallerPrincipal(), result.getCallerGroups());
    }

    private AuthenticationStatus validateToken(String token, HttpMessageContext context) {
        try{
            if(tokenProvider.validateToken(token)){
                System.out.println("AuthenticationMechenism - In Validate Token Method");
                JWTCredential credential = tokenProvider.getCredential(token);
                return context.notifyContainerAboutLogin(credential.getPrincipal(),credential.getAuthorities());
            }
            return context.responseUnauthorized();
        }catch(ExpiredJwtException eje){
            eje.printStackTrace();
            return context.responseUnauthorized();
        }
    }
    
}
