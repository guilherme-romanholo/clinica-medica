package clinica.medica.database;

import clinica.medica.documentos.Exame;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public static ArrayList<Exame> verificarExames(String cpfMedico){
        ArrayList<Exame> exames = new ArrayList();
        String query = "SELECT * FROM exames WHERE medicoSolicitante = ?";

        SQLiteConnection connection = new SQLiteConnection();
        connection.conectar();

        try{
            PreparedStatement pstmt = connection.getConn().prepareStatement(query);
            pstmt.setString(1, cpfMedico);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                exames.add(new Exame(rs.getInt("id")));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        connection.desconectar();

        return exames;
    }
}
