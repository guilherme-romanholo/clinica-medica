
package clinica.medica.database;

import clinica.medica.documentos.Exame;
import clinica.medica.usuarios.Medico;
import clinica.medica.usuarios.Paciente;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class MedicosSQL {
    
    public static boolean cadastrarNovoExame(String tipo, String cpf, String crm, Date data, String comentario){
        boolean cadastro = false;
        String queryVerificacao = "SELECT * FROM pacientes WHERE cpf = ?";
        String query = "INSERT INTO exames (tipo, paciente, medicoSolicitante, comentario, data) VALUES (?, ?, ?, ?, ?)";
        
        SQLiteConnection connection = new SQLiteConnection();
        connection.conectar();

        try {
            PreparedStatement pstmt = connection.getConn().prepareStatement(queryVerificacao);
            pstmt.setString(1, cpf);  
            ResultSet rs = pstmt.executeQuery();
            if(!rs.next()){
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            PreparedStatement pstmt = connection.getConn().prepareStatement(query);
            pstmt.setString(1, tipo);
            pstmt.setString(2, cpf);
            pstmt.setString(3, crm);
            pstmt.setString(4, comentario);
            pstmt.setDate(5, data);
            pstmt.executeUpdate();
            cadastro = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        connection.desconectar();

        return cadastro;
    }
    
    public static ArrayList<Exame> verificarExames(String crm){
        ArrayList<Exame> exames = new ArrayList();
        String query = "SELECT * FROM exames WHERE medicoSolicitante = ?";

        SQLiteConnection connection = new SQLiteConnection();
        connection.conectar();
        
        try{
            PreparedStatement pstmt = connection.getConn().prepareStatement(query);
            pstmt.setString(1, crm);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                exames.add(new Exame(rs));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        connection.desconectar();

        return exames;
    }
}
