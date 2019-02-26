package by.epam.kimbar.util;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public class CreatorFullURL {

    public static String create(HttpServletRequest request) {
        StringBuilder url = new StringBuilder();

        Enumeration<String> paramNames = request.getParameterNames();


        String paramName;
        String paramValue;
        while(paramNames.hasMoreElements()) {
            paramName = paramNames.nextElement();

            paramValue = request.getParameter(paramName);
            url.append(paramName).append("=").append(paramValue).append("&");
        }

        url.insert(0, request.getRequestURL() + "?");

        return url.toString();

    }

}
