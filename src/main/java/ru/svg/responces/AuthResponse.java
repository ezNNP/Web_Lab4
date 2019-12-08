package ru.svg.responces;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Scope(scopeName = "prototype")
public class AuthResponse {
    @SerializedName("status")
    private AuthResponseType authResponseType;
    private String message;

    public enum AuthResponseType {
        @SerializedName("AUTH")
        AUTH,
        @SerializedName("REGISTERED")
        REGISTERED,
        @SerializedName("NO_AUTH")
        NO_AUTH,
        @SerializedName("NO_REGISTERED")
        NO_REGISTERED,
        @SerializedName("EXIT")
        EXIT,
        @SerializedName("NO_EXIT")
        NO_EXIT
    }
}
