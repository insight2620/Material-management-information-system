package store_ma.model;

public class Users {
    private int id;
    private String name;
    private String password;
    private int authority;

    public int getId(){return id;}

    public void setId(int id){this.id=id;}

    public String getName(){return name;}

    public void setName(String name){this.name=name;}

    public String getPassword(){return password;}

    public void setPassword(String password){this.password=password;}

    public int getAuthority(){return authority;}

    public void setAuthority(int authority){this.authority = authority;}
}
