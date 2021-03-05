package com.inetbabanking.Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver idriver;
	
	public LoginPage(WebDriver rdriver){
		
		idriver = rdriver ;
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(name="uid")
	@CacheLookup
	WebElement txtUserName ;
	
	@FindBy(name="password")
	@CacheLookup
	WebElement txtPassword ;
	
	@FindBy(name="btnLogin")
	@CacheLookup
	WebElement btnLogin ;
	
	
	@FindBy(linkText="Log out")
	@CacheLookup
	WebElement lnlLogout ;
	
	
	
	public void setUserName(String uname){
		
		txtUserName.sendKeys(uname);
		
	}
	
    public void setPassword(String pswrd){
		
		txtPassword.sendKeys(pswrd);
		
	}
	
	public void btnLogin(){
		
		btnLogin.click();
	}
	
     public void clickLogout(){
		
    	 lnlLogout.click();
		
	}
	
	
	
}
