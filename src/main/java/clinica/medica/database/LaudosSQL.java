package clinica.medica.database;

import clinica.medica.documentos.Laudo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public static ArrayList<Laudo> verificarLaudos(String cpfMedico){
        ArrayList<Laudo> laudos = new ArrayList();
        String query = "SELECT * FROM laudos WHERE medicoSolicitante = ?";

        SQLiteConnection connection = new SQLiteConnection();
        connection.conectar();

        try{
            PreparedStatement pstmt = connection.getConn().prepareStatement(query);
            pstmt.setString(1, cpfMedico);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                laudos.add(new Laudo(rs.getInt("exame")));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        connection.desconectar();

        return laudos;
    }
}
