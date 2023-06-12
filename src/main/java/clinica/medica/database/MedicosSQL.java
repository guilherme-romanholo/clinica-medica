
package clinica.medica.database;

import clinica.medica.documentos.Exame;
import clinica.medica.documentos.Laudo;
import clinica.medica.usuarios.Medico;
import clinica.medica.usuarios.Paciente;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class MedicosSQL {
    
    public static boolean cadastrarNovoExame(String tipo, String cpf, String cpfMedico, Date data, String comentario){
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
            pstmt.setString(3, cpfMedico);
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

    public static boolean cadastrarNovoLaudo(int idExame, String cpf, String cpfMedico, Date data, String conteudo){
        boolean cadastro = false;
        String query = "INSERT INTO laudos (exame, medicoSolicitante, paciente, data, conteudo) VALUES (?, ?, ?, ?, ?)";

        SQLiteConnection connection = new SQLiteConnection();
        connection.conectar();

        try {
            PreparedStatement pstmt = connection.getConn().prepareStatement(query);
            pstmt.setInt(1, idExame);
            pstmt.setString(2, cpfMedico);
            pstmt.setString(3, cpf);
            pstmt.setDate(4, data);
            pstmt.setString(5, conteudo);
            pstmt.executeUpdate();
            cadastro = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        connection.desconectar();

        return cadastro;
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
