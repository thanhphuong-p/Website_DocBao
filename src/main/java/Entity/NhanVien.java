package Entity;

import java.util.Date;

public class NhanVien {
String Id;
String Password;
String Fullname;
Date Birthday;
boolean Gender;
String Mobile;
String Email;
boolean Role;
public String getId() {
	return Id;
}
public void setId(String id) {
	Id = id;
}
public String getPassword() {
	return Password;
}
public void setPassword(String password) {
	Password = password;
}
public String getFullname() {
	return Fullname;
}
public void setFullname(String fullname) {
	Fullname = fullname;
}
public Date getBirthday() {
	return Birthday;
}
public void setBirthday(Date birthday) {
	Birthday = birthday;
}
public boolean isGender() {
	return Gender;
}
public void setGender(boolean gender) {
	Gender = gender;
}
public String getMobile() {
	return Mobile;
}
public void setMobile(String mobile) {
	Mobile = mobile;
}
public String getEmail() {
	return Email;
}
public void setEmail(String email) {
	Email = email;
}
public boolean isRole() {
	return Role;
}
public void setRole(boolean role) {
	Role = role;
}


}

