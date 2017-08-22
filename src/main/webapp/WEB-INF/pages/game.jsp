<%@ page import="com.opencode.test.entity.Game" %>
<h2>Game</h2>
<div class="gameField">
    <form action="/game/check" method="post">
        numbers<br>
        <input type="number" maxlength="4" minlength="4" max="9999" min="0000">
        <input type="submit" value="check">
    </form>
</div>
<div class="step">
    <ul>
        <%
            Game game = (Game) session.getAttribute("game");
        %>
    </ul>
</div>
