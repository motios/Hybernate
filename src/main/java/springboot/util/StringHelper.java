package springboot.util;

/**
 * Created by We on 21.06.2016.
 */
public  final   class StringHelper {
    public static final  String RESPONSE_REASON_NOT_FOUND="student by studentID(%d) not found.";
    public static final  String RESPONSE_REASON_EXIST="student with studentID(%d) existing in database.";
    public static final  String RESPONSE_REASON_SET_DEFAULT_STUDENTS_OK ="all students except for existing in database successfully added.";
    public static final  String RESPONSE_REASON_STUDENT_CREATED_OK ="student with studentID(%d) created successfully.";
    public static final  String RESPONSE_REASON_STUDENT_DELETE_OK="student with studentID(%d) deleted successfully.";
    public static final  String RESPONSE_REASON_STUDENT_ID_NOT_VALID="studentID(%d) not valid.";
    public static final  String RESPONSE_REASON_MAX_STUDENTS_COUNT="student with studentID(%d) not created because a database is full.";
}
