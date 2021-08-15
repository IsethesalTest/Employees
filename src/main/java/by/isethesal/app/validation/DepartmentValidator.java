package by.isethesal.app.validation;


/**
 * {@link  by.isethesal.app.model.entity.Department Department} validator
 */
public class DepartmentValidator {
    private static final String NAME_REGEX = "^[A-zА-я]{2,30}$";

    private DepartmentValidator() {
    }

    public static boolean isNameValid(String name) {
        return ((name != null) && name.matches(NAME_REGEX));
    }
}
