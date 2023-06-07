package clinica.medica.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExamesSQL {

    public static ResultSet selectAllFromExames(int id, Connection conn) {
        String query = "SELECT * FROM exames WHERE id = ?";
        ResultSet rs = null;

        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("Exame não foi encontrado.");
        }

        return rs;
    }
}
