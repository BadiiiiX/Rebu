package fr.mmp.rebu.user.mapper;

import fr.mmp.rebu.exception.MappingException;
import fr.mmp.rebu.user.factory.UserFactory;
import fr.mmp.rebu.user.model.User;
import fr.mmp.rebu.user.model.UserInterface;

import java.sql.ResultSet;

public class UserMapper {

    public static UserInterface databaseToUser(ResultSet rs) throws MappingException {

        try {
            return UserFactory.build(
                    rs.getInt("user_id"),
                    rs.getString("user_email"),
                    rs.getString("user_username"),
                    rs.getString("user_password")
            );
        } catch (Exception e) {
            throw new MappingException("Cannot map User", e);
        }

    }

}
