package by.epam.kimbar.util;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

/**
 * This class contains a method  which return url of the request
 */
public class CreatorFullURL {
    /** private empty constructor  don't need to create an entity  */
    private CreatorFullURL(){}

    /** Variables for correct creating url */
    private static final String EQUALS_SIGN = "=";
    private static final String AMPERSANT_SIGN = "&";
    private static final String QUESTION_SIGN = "?";
    private static final int OFFSET = 0;

    /**
     * Method get request and return url
     * @param request
     *          Request based on which the url will be created
     * @return url of the request
     */
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
