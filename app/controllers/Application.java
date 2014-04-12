package controllers;

import play.*;
import play.mvc.*;

import views.html.*;
import play.mvc.Controller;


public class Application extends Controller {

    public static Result index() {
    	
    	return ok(index.render("Your new application is not ready"));
    }

    public static Result testerPost() {
    	
    	 System.out.println("Request Body is \n"+request().body().asText());
    	String html=request().body().asText();
    	return ok(tester.render(html));//tester.render("abader"));
    }
    
 public static Result testerGet() {
    	
	
	 String html="Request Type is GET_REQUEST. Nothing to show in body";
 	 return  ok(tester.render(html));
	 
    }
 
 private  static String  getHtml(String content)
 {
	String cont="";
	if(content=="GET_REQUEST")
	{
		 cont="You have used a GET Request, For this Tester to work, send what you want in a POST <br> the content will be displayed in the browser"; 
	}
	else
	{
		 cont=content;
	}
	String html= "<html><head>  Request Tester <head><body><h3>You sent this in Request body</h3> <br>"+cont+" </body></html>";
	
 return html;
	
 }
 

}
