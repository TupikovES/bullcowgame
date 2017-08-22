package com.opencode.test.dao;

import com.opencode.test.database.Database;
import com.opencode.test.entity.Game;
import com.opencode.test.entity.History;

import java.sql.PreparedStatement;
import java.sql.SQLException;

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
                    .prepareStatement("INSERT INTO history (user_id, steps, date) VALUES (?, ?, ?)");
            ps.setInt(1, h.getUser_id());
            ps.setInt(2, h.getScore());
            ps.setDate(3, h.getDate());
            i = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return i;
    }


}
