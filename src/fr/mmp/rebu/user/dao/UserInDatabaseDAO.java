package fr.mmp.rebu.user.dao;

import fr.mmp.rebu.domain.AbstractDAO;
import fr.mmp.rebu.exception.MappingException;
import fr.mmp.rebu.user.mapper.UserMapper;
import fr.mmp.rebu.user.model.UserInterface;
import fr.mmp.rebu.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserInDatabaseDAO extends AbstractDAO implements UserDAO{

    private final String USER_ID_COLUMN_NAME = "user_id";

    @Override
    public int save(UserInterface user) {
        final String SAVE_USER = """
            INSERT INTO users (user_email, user_username, user_password)
            VALUES (?, ?, ?)
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SAVE_USER, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPassword());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la création de l'utilisateur");
        }

        return -1;
    }


    @Override
    public void update(UserInterface user) {
        final String UPDATE_USER = """
            UPDATE users
            SET user_email = ?, user_username = ?, user_password = ?
            WHERE user_id = ?
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_USER)) {

            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPassword());
            stmt.setInt(4, user.getUserId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de l'utilisateur");
        }
    }

    @Override
    public void delete(int userId) {
        final String DELETE_USER = "DELETE FROM users WHERE user_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_USER)) {

            stmt.setInt(1, userId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de l'utilisateur");
        }
    }

    @Override
    public List<UserInterface> findAll() {
        final String FIND_ALL_USERS = "SELECT * FROM users";
        List<UserInterface> users = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(FIND_ALL_USERS);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                try {
                    users.add(UserMapper.databaseToUser(rs));
                } catch (MappingException me) {
                    System.out.println("Erreur lors du mapping d'un utilisateur (ID : " + rs.getInt("user_id") + ")");
                }
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de tous les utilisateurs");
        }

        return users;
    }

    @Override
    public UserInterface findById(int userId) {
        final String FIND_USER = "SELECT * FROM users WHERE user_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(FIND_USER)) {

            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return UserMapper.databaseToUser(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche de l'utilisateur par ID");
        } catch (MappingException me) {
            System.out.println("Erreur lors du mapping de l'utilisateur");
        }

        return null;
    }

    public UserInterface findByMail(String mail) {
        final String FIND_USER = "SELECT * FROM users WHERE user_email = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(FIND_USER)) {

            stmt.setString(1, mail);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return UserMapper.databaseToUser(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche de l'utilisateur par email");
        } catch (MappingException me) {
            System.out.println("Erreur lors du mapping de l'utilisateur");
        }

        return null;
    }

    @Override
    public UserInterface findByUsername(String username) {
        final String FIND_USER = "SELECT * FROM users WHERE user_username = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(FIND_USER)) {

            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return UserMapper.databaseToUser(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche de l'utilisateur par nom d'utilisateur");
        } catch (MappingException me) {
            System.out.println("Erreur lors du mapping de l'utilisateur");
        }

        return null;
    }
}
