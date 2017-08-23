<%@ page import="com.opencode.test.entity.Rating" %>
<%@ page import="java.util.List" %>
<%@ page import="com.opencode.test.entity.History" %>
<h2>History</h2>
<table>
    <thead>
        <tr>
            <th>#</th>
            <th>Score</th>
            <th>Date</th>
        </tr>
    </thead>
    <tbody>
        <%
            List<History> historyList = (List<History>) request.getAttribute("histories");
            int i = 0;
            for (History history : historyList) {
        %>
        <tr>
            <td><%=++i%></td>
            <td><%=history.getScore()%></td>
            <td><%=history.getDate().toString()%></td>
        </tr>
        <%
            }
        %>
    </tbody>
</table>
