package Util;
import javax.servlet.http.HttpServletRequest;

public class ServletUtil {

    public static String getField(HttpServletRequest request, String fieldName, boolean required) throws  Exception {
        String fieldValue = request.getParameter(fieldName);
        if (fieldValue == null || fieldValue.trim().isEmpty()) {
            if (required) {
                throw new Exception("Field" +fieldName +" is required");
            } else {
                fieldValue = null; //Make empty string null so that I don't need to hassle with equals("") afterwards.
            }
        }
        return fieldValue;
    }
}
