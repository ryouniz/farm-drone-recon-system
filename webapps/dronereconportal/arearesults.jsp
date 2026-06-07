<%@ page import="com.dronerecon.ws.AreaGridTile" %>
<%@ page import="com.dronerecon.ws.DBManager" %>
<%@ page import="java.util.ArrayList" %>

<html>
    <body>
        <%
        String areaID = request.getParameter("area_id");

        DBManager oDBManager = new DBManager();

        oDBManager.DBLocation = System.getProperty("catalina.base") + "\\webapps\\dronereconportal\\db\\" + oDBManager.DBLocation;
        
        ArrayList<AreaGridTile> tiles = oDBManager.readAreaGridTiles(areaID);

        int highestRval = 0;
        int highestredX = 0;
        int highestredY = 0;

        int highestGval = 0;
        int highestgreenX = 0;
        int highestgreenY = 0;

        for (int i = 0; i < tiles.size(); i++){
            
            AreaGridTile tile = tiles.get(i);

            if (tile.r > highestRval){
                highestRval = tile.r;
                highestredX = tile.x;
                highestredY = tile.y;
            }
            
            if (tile.g > highestGval){
                highestGval = tile.g;
                highestgreenX = tile.x;
                highestgreenY = tile.y;
            }
        }
        
        out.println("Highest R at x=" + highestredX + " y=" + highestredY + "<br>");
        out.println("Highest G at x=" + highestgreenX + " y=" + highestgreenY);    
        %> 

    </body>
</html>