package Serialisation;

public class Personne 
{
private String firstname;
private String secondname;
private String age;
public Personne(String firstname, String secondname, String age) {
	this.firstname = firstname;
	this.secondname = secondname;
	this.age = age;
}
public String getfirstname() {
	return firstname;
}
public void setfirstname(String firstname) {
	this.firstname = firstname;
}
public String getsecondname() {
	return secondname;
}
public void setsecondname(String secondname) {
	this.secondname = secondname;
}
public String getage() {
	return age;
}
public void setage(String age) {
	this.age = age;
}
 
}