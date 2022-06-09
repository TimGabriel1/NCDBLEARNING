package Util;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

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

    public static String generateUUID(){
        UUID uuid = UUID.randomUUID();
        System.out.println("Generated :" + uuid.toString() );
        Date date = new Date();
        String id = date.toString().substring(4,14)+uuid.toString();
        id = id.replaceAll("\\s","-");
        System.out.println(id);
        return id;
    }

    public static void main(String[] args) {
        generateUUID();
    }
}
