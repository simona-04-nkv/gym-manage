
package app;


public class Member {
     private int memberid;
    private String name;
    private String phone;
    private String email;
    private String gender;
    private String datajoin;
    
    public Member(int memberid, String name, String phone, String email, String gender, String datajoin) {
        this.memberid = memberid;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.datajoin = datajoin;
        
        
}
    public Member(String name, String phone, String email, String gender, String datajoin) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.datajoin = datajoin;
    }
    //public Member(String name, String phone, String email, String gender, String datajoin) {
     //   this(0, name, phone, email, gender, datajoin);
    //}
    
    public int getId() { return memberid; }
    public void setId(int memberid) { this.memberid = memberid; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getDateJoined() { return datajoin; }
    public void setDateJoined(String datajoin) { this.datajoin = datajoin; }
    
    public Object[] toArray() {
        return new Object[] { memberid, name, phone, email, gender, datajoin };
    }

}
