package ru.svg.responces;

import com.google.gson.annotations.SerializedName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "prototype")
public class AuthResponse {
    @SerializedName("status")
    private AuthResponseType authResponseType;
    private String message;

    public AuthResponseType getAuthResponseType() {
        return authResponseType;
    }

    public void setAuthResponseType(AuthResponseType authResponseType) {
        this.authResponseType = authResponseType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public enum AuthResponseType {
        @SerializedName("AUTH")
        AUTH,
        @SerializedName("REGISTERED")
        REGISTERED,
        @SerializedName("NO_AUTH")
        NO_AUTH,
        @SerializedName("NO_REGISTERED")
        NO_REGISTERED
    }
}
