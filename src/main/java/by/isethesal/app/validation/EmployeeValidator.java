package by.isethesal.app.validation;

import java.time.LocalDateTime;

/**
 * {@link by.isethesal.app.model.entity.Employee Employee} validator
 */
public class EmployeeValidator {
    private static final String NAME_REGEX = "^[A-zА-я]{3,30}$";
    private static final String JOB_TITLE_REGEX = "^[A-zА-я]{6,30}$";
    private static final String GENDER_REGEX = "^[A-zА-я0-9]{1,300}$";

    private EmployeeValidator() {
    }

    public static boolean isFirstNameValid(String name) {
        return ((name != null) && name.matches(NAME_REGEX));
    }

    public static boolean isLastNameValid(String lastName) {
        return ((lastName != null) && lastName.matches(NAME_REGEX));
    }

    public static boolean isJobTitleValid(String jobTitle) {
        return ((jobTitle != null) && jobTitle.matches(JOB_TITLE_REGEX));
    }

    public static boolean isBirthDateValid(LocalDateTime date) {
        return date.isBefore(LocalDateTime.now());
    }

    public static boolean isGenderValid(String gender) {
        return ((gender != null) && gender.matches(GENDER_REGEX));
    }
}
