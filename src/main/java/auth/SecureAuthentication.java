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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationException;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.CredentialValidationResult.Status;
import javax.security.enterprise.identitystore.IdentityStoreHandler;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import record.KeepRecord;

/**
 *
 * @author Admin
 */
@Named
@RequestScoped
//public class SecureAuthentication implements HttpAuthenticationMechanism, Serializable {
public class SecureAuthentication implements HttpAuthenticationMechanism {
    
    @Inject
    IdentityStoreHandler handler;
    CredentialValidationResult result;
//    AuthenticationStatus status;
    
    @Inject
    TokenProvider tokenProvider;
    String token;
    
//    @Inject LoginBean lb;
    
    

    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest request, HttpServletResponse response, HttpMessageContext ctx) throws AuthenticationException{
        
        try{
//            request.setCharacterEncoding("UTF-8");
            if(request.getRequestURI().contains("logout")){
                request.logout();
                KeepRecord.reset();
                request.getSession().removeAttribute("TOKEN");
                clearTokenFromCookie(request, response);
                System.out.println("Logout Successfully");
                response.sendRedirect("/TourismAdvisor/login.jsf");
                return ctx.doNothing();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        if(KeepRecord.getToken() != null){
            token = KeepRecord.getToken();
        }
        
//        String token = extractToken(ctx);

        try{
            request.setCharacterEncoding("UTF-8");
//            System.out.println("==> Username := " + request.getParameter("username"));

            
            
            if(token == null && request.getParameter("username") != null){
                
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                
                
                
                System.out.println("=> In Auth - ");
                
                Credential credential = new UsernamePasswordCredential(username, new Password(password));
                result = handler.validate(credential);
                
//                    KeepRecord.setErrorStatus(null);
                if(result.getStatus() == Status.VALID){
//                    KeepRecord.setErrorStatus(null);
                    AuthenticationStatus status = createToken(result,ctx);

                    status = ctx.notifyContainerAboutLogin(result);

                    KeepRecord.setUsername(username);
                    KeepRecord.setPassword(password);
                    KeepRecord.setPrincipal(result.getCallerPrincipal());
                    KeepRecord.setRoles(result.getCallerGroups());
                    KeepRecord.setCredential(credential);
                    
                    if(result.getCallerGroups().contains("Admin")){
                        response.sendRedirect("/TourismAdvisor/Admin/adminDashboard.jsf");
                    }
                    if(result.getCallerGroups().contains("User")){
                        response.sendRedirect("/TourismAdvisor/Users/home.jsf");
                    }
                    return status;
                }else{
                    if(request.getParameter("username").isEmpty() || request.getParameter("password").isEmpty()){
                        KeepRecord.setErrorStatus("All Fields are Required..!");
                    }else{
                        KeepRecord.setErrorStatus("Either Username or Password is Wrong..!");
                    }
                    response.sendRedirect("/TourismAdvisor/login.jsf");
                    return ctx.doNothing();
                }
            }
             
//            if(request.getSession().getAttribute("TOKEN") != null && ((request.getRequestURI().contains("login")) || request.getRequestURI().equals("/TA/"))){
            if(request.getSession().getAttribute("TOKEN") != null && request.getRequestURI().contains("login")){
                System.out.println(result.getCallerGroups().contains("Admin") + "    :   " + result.getCallerGroups().toString());
                if(result.getCallerGroups().contains("Admin")){
                    response.sendRedirect("/TourismAdvisor/Admin/adminDashboard.jsf");
                }
                if(result.getCallerGroups().contains("User")){
                    response.sendRedirect("/TourismAdvisor/Users/home.jsf");
                }
            }
        }catch(Exception e){
            System.out.println("Error occured :-  " + e.getMessage());
            e.printStackTrace();
        }
        
        if(KeepRecord.getToken() != null){
                    ctx.notifyContainerAboutLogin(KeepRecord.getPrincipal(),KeepRecord.getRoles());
            }
        
        if(token != null){
            return validateToken(token,ctx);
        }else if(ctx.isProtected()){
            return ctx.responseUnauthorized();
        }
      return ctx.doNothing();
    }
    
    private String extractToken(HttpMessageContext context) {
        
//        ========>       Extract Token From Authorization Header       <========

//        String authorizationHeader = context.getRequest().getHeader(AUTHORIZATION_HEADER);
//        if(authorizationHeader != null && authorizationHeader.startsWith(BEARER)){
//            String token = authorizationHeader.substring(BEARER.length(),authorizationHeader.length());
//            KeepRecord.setToken(token);
//            System.out.println("AuthenticationMechenism - In Extract Token Method");
//            return token;
//        }
//        return null;

//        ========>       Extract Token From Session        <========
    
        return context.getRequest().getSession().getAttribute("TOKEN").toString();

    }

    private AuthenticationStatus createToken(CredentialValidationResult result, HttpMessageContext context) {
        String jwt = tokenProvider.createToken(result.getCallerPrincipal().getName(), result.getCallerGroups(), false);
        KeepRecord.setToken(jwt);
        System.out.println("AuthenticationMechenism - In Create Token Method");
        
//        ========>       Store JWT In Cookie and Session        <========

        Cookie cookie = new Cookie("TOKEN",BEARER + jwt);
        cookie.setPath("/TourismAdvisor");
        context.getResponse().addCookie(cookie);
        context.getRequest().getSession().setAttribute("TOKEN", BEARER + jwt);
        System.out.println("Token :- " + jwt);
        
//        ========>       Store JWT in Authorization Header        <========

//        context.getResponse().addHeader(AUTHORIZATION_HEADER, BEARER + jwt);
        
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
            System.out.println("=========: Token Expired Exception Occured :=========");
            eje.printStackTrace();
            return context.responseUnauthorized();
        }
    }
    
    public void clearTokenFromCookie(HttpServletRequest request,HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie c : cookies){
                if(c.getName().equals("TOKEN")){
                    c.setValue(null);
                    c.setMaxAge(0);
                    c.setPath("/TA");
                    response.addCookie(c);
                }
            }
        }
    }
    
    public boolean isCookieExist(HttpMessageContext context){
        Cookie[] cookies = context.getRequest().getCookies();
        if(cookies != null){
            for(Cookie c : cookies){
                if(c.getName().equals("TOKEN")){
                    return true;
                }
            }
        }
        return false;
    }
}
