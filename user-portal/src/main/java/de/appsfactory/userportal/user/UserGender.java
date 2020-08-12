package de.appsfactory.userportal.user;

import de.appsfactory.userportal.error.exception.InvalidParameterException;
import lombok.Getter;
import lombok.NonNull;
import lombok.Value;
import org.apache.commons.lang3.StringUtils;

@Value
public class UserGender {

    @Getter
    @NonNull
    private final String gender;

    public static UserGender from(@NonNull String value) {
        if (isAValidGender(value)) {
            return new UserGender(value);
        } else {
            throw new InvalidParameterException(User.class, "gender", value);
        }
    }

    private static boolean isAValidGender(@NonNull String gender) {
        return StringUtils.isNotEmpty(gender) && (
                Gender.MALE.name.equalsIgnoreCase(gender) ||
                        Gender.FEMALE.name.equalsIgnoreCase(gender) ||
                        Gender.UNKNOWN.name.equalsIgnoreCase(gender));
    }

    public enum Gender {
        MALE("Male"),
        FEMALE("Female"),
        UNKNOWN("Unknown");

        private final String name;

        Gender(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }
}
