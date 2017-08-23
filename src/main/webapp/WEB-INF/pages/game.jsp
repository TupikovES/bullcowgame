<%@ page import="com.opencode.test.entity.Game" %>
<h2>Game</h2>

<%
    Game game = (Game) session.getAttribute("game");
    if (!game.isEndGame()) {
%>
    <div class="gameField">
        <form action="/game/check" method="post">
            numbers<br>
            <input type="number" maxlength="4" minlength="4" max="9999" min="0000" name="playerNumber">
            <input type="submit" value="check">
        </form>
    </div>
<%
    } else {
%>
    <div class="endGame">FINISHED!!! Click <a href="/game/play">start</a> for new game</div>
<%  }  %>
<div class="step">
    <ul>
        <%
            for (String step : game.getHistory()) {
        %>

        <li>#${count}<%=step%></li>

        <%  } %>
    </ul>
</div>
