package org.revature.entities;

public class User {
    private Integer userId;
    private String username;
    private String password;
    private String birthday;

    private String address;
    private byte[] image;

    public User() {
    }

    public User(Integer userId, String username, String password, String birthday, byte[] image) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.birthday = birthday;
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "User{" +
                "UserId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", birthday='" + birthday + '\'' +
                ", address='" + address + '\'' +
                ", image='" + image + '\'' +
                '}';
    }


}
