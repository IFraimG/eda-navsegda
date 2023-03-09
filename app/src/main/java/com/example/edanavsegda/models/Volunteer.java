package com.example.edanavsegda.models;

public class Volunteer implements UserIT {
    public String fullName;
    public String firstName;
    public String secondName;
    private String volunteerID;
    private String location;
    public String password;

    public void createPost() {

    }

    public ModeratePostProduct acceptUserRequest(String userID, PostProduct postProduct) {
        // ...

        return new ModeratePostProduct("", "", "");
    }

    public void rejectPostProduct(PostProduct postProduct) {
        // server request
    }
}
