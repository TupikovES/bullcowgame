package com.opencode.test.dao;

import com.opencode.test.database.Database;
import com.opencode.test.entity.Game;
import com.opencode.test.entity.History;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by x3mib on 22.08.2017.
 */
public class HistoryDao {
    private static HistoryDao ourInstance = new HistoryDao();

    public static HistoryDao getInstance() {
        return ourInstance;
    }

    private Database db;

    private HistoryDao() {
        db = Database.getInstance();
    }

    public int saveHistory(History h) {
        int i = -1;
        try {
            PreparedStatement ps = db.getConnection()
                    .prepareStatement("INSERT INTO history (user_id, score, date) VALUES (?, ?, ?)");
            ps.setInt(1, h.getUser_id());
            ps.setInt(2, h.getScore());
            ps.setDate(3, h.getDate());
            i = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return i;
    }

    public List<History> getHistoryUser(int id) {
        List<History> historyList = new ArrayList<>();
        try {
            PreparedStatement ps = db.getConnection()
                    .prepareStatement("SELECT * FROM history WHERE user_id = ? LIMIT 10");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                historyList.add(
                    new History(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("score"),
                        rs.getDate("date")
                    )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return historyList;
    }


}
