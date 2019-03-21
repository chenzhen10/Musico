package by.epam.kimbar.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

public class CreatorFullURL {

    private CreatorFullURL(){}

    private static final String EQUALS_SIGN = "=";
    private static final String AMPERSANT_SIGN = "&";
    private static final String QUESTION_SIGN = "?";

    private static final int OFFSET = 0;

    public static String create(HttpServletRequest request) {
        StringBuilder url = new StringBuilder();

        Enumeration<String> paramNames = request.getParameterNames();


        String paramName;
        String paramValue;
        while(paramNames.hasMoreElements()) {
            paramName = paramNames.nextElement();

            paramValue = request.getParameter(paramName);
            url.append(paramName).append(EQUALS_SIGN).append(paramValue).append(AMPERSANT_SIGN);

        }

        url.insert(OFFSET, request.getRequestURL() + QUESTION_SIGN);

        return url.toString();

    }

}
