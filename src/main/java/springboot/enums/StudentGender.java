package springboot.enums;

/**
 * Created by We on 21.06.2016.
 * Gender list
 */
public enum StudentGender {
    MALE,FEMALE;

     public static boolean contains(String val) {

        for (StudentGender a : StudentGender.values()) {
            if (a.toString().equalsIgnoreCase(val)) {
                return true;
            }
        }
        return false;
    }

}
