package com.zy.app.crm.dao;

import com.zy.app.crm.model.User;
import com.zy.app.common.dao.Dao;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;

@Component
public class UserDaoImpl extends Dao implements UserDao {

    private static final String INSERT_NEW_USER = "insert into users(first_name, last_name, address, city, zip) values (?,?,?,?,?)";

    @Override
    public int createUser(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                (connection) -> {
                    PreparedStatement ps = connection.prepareStatement(INSERT_NEW_USER, new String[] {"id"});
                    ps.setString(1, user.getFirstName());
                    ps.setString(2, user.getLastName());
                    ps.setString(3, user.getAddress());
                    ps.setString(4, user.getCity());
                    ps.setString(5, user.getZip());
                    return ps;
                }, keyHolder
        );
        return keyHolder.getKey().intValue();
    }
}
