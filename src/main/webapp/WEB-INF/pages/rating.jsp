<%@ page import="com.opencode.test.entity.Rating" %>
<%@ page import="java.util.List" %>
<h2>Rating</h2>
<table>
    <thead>
        <tr>
            <th>#</th>
            <th>Player</th>
            <th>Rating</th>
        </tr>
    </thead>
    <tbody>
        <%
            List<Rating> ratings = (List<Rating>) request.getAttribute("ratings");
            int i = 0;
            for (Rating rating : ratings) {
        %>
            <td><%=++i%></td>
            <td><%=rating.getUserName()%></td>
            <td><%=rating.getRating()%></td>
    </tbody>
</table>
