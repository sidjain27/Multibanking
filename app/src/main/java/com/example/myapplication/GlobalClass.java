package com.example.myapplication;
import android.app.Application;

public class GlobalClass extends Application{
	public String UserName;
	public String Password;
	public String Name;

	public String GetName()

	{
		return Name;
	}

	public void SetName(String _sname)
	{
		Name=_sname;

	}

	public String GetUsername()

	{
		return UserName;
	}
	
	public void Setusername(String _susername)
	{
		UserName=_susername;
		
	}
	
	public String GetPassword()
	{

		return Password;
	}
	
	public void Setpassword(String _spassword)
	{
		Password=_spassword;
		
	}
	
	
}
