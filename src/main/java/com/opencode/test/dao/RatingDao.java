package com.opencode.test.dao;

import com.opencode.test.database.Database;
import com.opencode.test.entity.History;
import com.opencode.test.entity.Rating;
import com.opencode.test.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by x3mib on 22.08.2017.
 */
public class RatingDao {
    private static RatingDao ourInstance = new RatingDao();

    public static RatingDao getInstance() {
        return ourInstance;
    }

    private Database db;
    private UserDao userDao;
    private HistoryDao historyDao;

    private RatingDao() {
        db = Database.getInstance();
        userDao = UserDao.getInstance();
        historyDao = HistoryDao.getInstance();
    }

    public void updateRating(User user) {
        if (isExist(user.getId())) {
            try {
                PreparedStatement ps = db.getConnection()
                        .prepareStatement("UPDATE rating r set r.rating = ? WHERE r.user_id = ?");
                ps.setFloat(1, getRating(user));
                ps.setInt(2, user.getId());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            addRating(user);
        }
    }

    public Rating getRatingByUser(User user) {
        return null;
    }

    public List<Rating> getAll() {
        List<Rating> ratingList = new ArrayList<>();

        try {
            PreparedStatement ps = db.getConnection()
                    .prepareStatement("SELECT * FROM rating ORDER BY rating ASC");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ratingList.add(
                        new Rating(
                                rs.getInt("id"),
                                rs.getInt("user_id"),
                                userDao.getById(rs.getInt("user_id")).getLogin(),
                                rs.getFloat("rating")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ratingList;
    }

    private int addRating(User user) {
        int i = -1;
        try {
            PreparedStatement ps = db.getConnection()
                    .prepareStatement("INSERT INTO rating (user_id, rating) VALUES (?, ?)");
            ps.setInt(1, user.getId());
            ps.setFloat(2, getRating(user));
            i = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    private boolean isExist(int user_id) {
        try {
            PreparedStatement ps = db.getConnection()
                    .prepareStatement("SELECT * FROM rating WHERE user_id = ?");
            ps.setInt(1, user_id);
            ResultSet rs = ps.executeQuery();
            if (!rs.isBeforeFirst()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    private float getRating(User user) {
        List<History> historyList = historyDao.getHistoryUser(user.getId());
        float rating = 0f;
        int allScore = 0;
        for (History h : historyList) {
            allScore += h.getScore();
        }
        rating = (float) allScore/historyList.size();
        return rating;
    }
}
