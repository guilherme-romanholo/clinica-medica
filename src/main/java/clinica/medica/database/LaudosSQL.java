package clinica.medica.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LaudosSQL {
    public static ResultSet selectAllFromLaudos(int id, Connection conn) {
        String query = "SELECT * FROM laudos WHERE exame = ?";
        ResultSet rs = null;

        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("Laudo não foi encontrado.");
        }

        return rs;
    }
}
