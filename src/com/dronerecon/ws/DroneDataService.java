package com.dronerecon.ws;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.security.SecureRandom;

/**
 *
 * @author Raksa
 */
public class DroneDataService extends HttpServlet{


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json;charset=utf-8");
        response.addHeader("Access-Control-Allow-Origin", "*");

        PrintWriter out = response.getWriter();

        String areaID = request.getParameter("area_id");
        String tileX = request.getParameter("tilex");
        String tileY = request.getParameter("tiley");
        String totalCols = request.getParameter("totalcols");
        String totalRows = request.getParameter("totalrows");
        String r = request.getParameter("r");
        String g = request.getParameter("g");

        // Call Portal
        try {

            URL url = new URL("http://127.0.0.1:8080/dronereconportal/PortalDBService?area_id=" + areaID + "&tilex=" + tileX + "&tiley=" + tileY + "&r=" + r + "&g=" + g);

            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

       
        String sDirection = "right";

        int x = Integer.parseInt(tileX);
        int y = Integer.parseInt(tileY);
        int cols = Integer.parseInt(totalCols);
        int rows = Integer.parseInt(totalRows);


        if (y % 2 == 0){
            if (x < cols - 1){
                x++;
                sDirection = "right";
            }
            else{
                y++;
                sDirection = "left";
            }
        }
        else if (x > 0){
            x--;
            sDirection = "left";
        }
        else{
            y++;
            sDirection = "right";
        }

        if (y >= rows){
            sDirection = "stop";
        }

        String sReturnJson = "{\"area_id\":\"" + areaID + "\", \"nextTileX\":\"" + x + "\", \"nextTileY\":\"" + y + "\", \"direction\":\"" + sDirection + "\"}";

        out.println(sReturnJson);

    }
}

