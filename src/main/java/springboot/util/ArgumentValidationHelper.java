package springboot.util;

/**
 * Created by We on 21.06.2016.
 *
 */
public abstract class ArgumentValidationHelper {

    public static boolean studentIdValidate(long studentId){
        if(String.valueOf(studentId).length()!=9)
            return false;
        return true;
    }
}
