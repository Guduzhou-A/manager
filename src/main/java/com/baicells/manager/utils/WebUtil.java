package com.baicells.manager.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class WebUtil {
    public static void writeJsonResponse(HttpServletResponse response, Result result) {

        try {


            // CORS setting
            response.setHeader("Access-Control-Allow-Origin", "*");

            response.setContentType("application/json;charset=utf-8"); //json

            final PrintWriter out = response.getWriter();
            out.print(result);
            out.flush();
        } catch (IOException e) {
            throw new IllegalStateException("Write  error", e);
        }
    }

}
