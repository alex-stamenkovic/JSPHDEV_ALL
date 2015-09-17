<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="javasmartphone.p1u5.model.Automobile"
    import="java.util.List"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>Car configuration result</title>
    </head>
    <body>
        <!-- Say Hi -->
        <h1> Hello! This is the car configuration result</h1>
        
        <%
          Automobile auto = (Automobile)request.getAttribute("auto");
          if(auto == null){ %>
          
          <h1> Filed. Can get the result</h1>
          
        <% } else { %>	
            <table border='1px'>
            
            <tr><td> <%= auto.getMake() %>  <%= auto.getModel() %> </td>
            <td>Base Price</td><td> <%= auto.getBaseprice() %> </td></tr>
            <%
            List<String> optionSetName = auto.getAllOptionSetName();
            for (int i = 0; i < optionSetName.size(); i++) {
                String aSetName = optionSetName.get(i);
                String choice = request.getParameter(aSetName);
            %>    
                <tr><td><%= aSetName %> </td>
                <td><%= choice %> </td>
                <td><%= auto.getOptionChoicePrice(aSetName) %></td>
            <% 
            }
            %>
            <tr><td>Total Price</td><td><td> <%= auto.getTotalPrice() %> </td></tr>
            
            
            </table>
        <% } %>
      
         
    </body>
</html>