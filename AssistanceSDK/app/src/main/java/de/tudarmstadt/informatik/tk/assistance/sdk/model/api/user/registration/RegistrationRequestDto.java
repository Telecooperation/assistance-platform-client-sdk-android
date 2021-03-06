package de.tudarmstadt.informatik.tk.assistance.sdk.model.api.user.registration;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Wladimir Schmidt on 28.06.2015.
 */
public class RegistrationRequestDto {

    @SerializedName("email")
    @Expose
    private String userEmail;

    @SerializedName("password")
    @Expose
    private String password;

    public RegistrationRequestDto() {
    }

    public RegistrationRequestDto(String userEmail, String password) {
        this.userEmail = userEmail;
        this.password = password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "RegistrationRequestDto{" +
                "userEmail='" + userEmail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
