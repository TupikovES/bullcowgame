package com.opencode.test.dao;

import com.opencode.test.database.Database;
import com.opencode.test.entity.Rating;
import com.opencode.test.entity.User;

import java.sql.PreparedStatement;
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

    private RatingDao() {
        db = Database.getInstance();
    }

    public void updateRating(User user) {
        if (isExist(user.getId())) {

        } else {
            addRating(user);
        }
    }

    public Rating getRatingByUser(User user) {
        return null;
    }

    public List<Rating> getAll() {
        return null;
    }

    private void addRating(User user) {

    }

    private boolean isExist(int user_id) {
        return true;
    }
}
