package de.appsfactory.userportal.adapter.web.request;

import de.appsfactory.userportal.common.validator.SelfValidating;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.*;
import java.util.Date;

import static de.appsfactory.userportal.util.DateUtil.LOCAL_DATE_FORMAT;

@Data
@AllArgsConstructor
public class UpdateUserRequest extends SelfValidating<UpdateUserRequest> {
    @NonNull
    @Size(min = 3, max = 50, message = "The first name must be between {min} and {max} characters long")
    private String firstName;

    @NonNull
    @Size(min = 3, max = 50, message = "The last name must be between {min} and {max} characters long")
    private String lastName;

    @NonNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = LOCAL_DATE_FORMAT)
    @Past(message = "Date of birth must be in past")
    private Date dob;

    @NonNull
    @NotBlank(message = "Please provide address")
    private String address1;

    @NonNull
    private String address2;

    @NonNull
    private String address3;

    @NonNull
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "The post code is not valid")
    @Size(min = 2, max = 8, message = "The postcode must be between {min} and {max} characters long")
    private String postcode;

    @NonNull
    @Size(min = 2, max = 50, message = "The city name must be between {min} and {max} characters long")
    private String city;

    @NonNull
    @Size(min = 2, max = 50, message = "The country name must be between {min} and {max} characters long")
    private String countryName;

    @NonNull
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "The country code is wrong")
    @Size(min = 2, max = 4, message = "The country code must be between {min} and {max} characters long")
    private String countryISOCode;

    @NotNull
    @NotBlank(message = "Please provide an email")
    @Email(message = "Please provide a valid email")
    private String email;

    @NonNull
    private String gender;

}
