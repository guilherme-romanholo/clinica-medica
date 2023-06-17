package clinica.medica.database;

import clinica.medica.consultas.Consulta;
import clinica.medica.documentos.Receita;

import java.sql.*;
import java.util.ArrayList;

public class ConsultaSQL {

    public static ArrayList<Consulta> selectAllConsultasFromPaciente(String cpfPaciente) {
        String query = "SELECT * FROM consultas where paciente = ?";
        ArrayList<Consulta> consultas = new ArrayList<>();
        ResultSet rs = null;

        SQLiteConnection connection = new SQLiteConnection();
        connection.conectar();

        try {
            PreparedStatement pstmt = connection.getConn().prepareStatement(query);
            pstmt.setString(1, cpfPaciente);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                consultas.add(new Consulta(rs.getInt("id")));
            }
        } catch (SQLException e) {
            System.out.println("Receita não foi encontrada.");
        }

        return consultas;
    }

    public static ArrayList<Consulta> selectAllConsultasFromMedico(String cpfMedico) {
        String query = "SELECT * FROM consultas where medico = ?";
        ArrayList<Consulta> consultas = new ArrayList<>();
        ResultSet rs = null;

        SQLiteConnection connection = new SQLiteConnection();
        connection.conectar();

        try {
            PreparedStatement pstmt = connection.getConn().prepareStatement(query);
            pstmt.setString(1, cpfMedico);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                consultas.add(new Consulta(rs.getInt("id")));
            }
        } catch (SQLException e) {
            System.out.println("Receita não foi encontrada.");
        }

        return consultas;
    }

    public static ResultSet selectAllFromConsulta(int id, Connection conn) {
        String query = "SELECT * FROM consultas WHERE id = ?";
        ResultSet rs = null;

        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("Consulta não foi encontrada.");
        }

        return rs;
    }

    public static boolean salvarConsulta(Date data, String cpfMedico, String cpfPaciente, String descricao, String horario){
        boolean cadastro = false;
        String query = "INSERT INTO consultas (medico, paciente, data, descricao, realizada, horario) VALUES (?, ?, ?, ?, ?, ?)";
        if(descricao.equals("")){
            return false;
        }
        SQLiteConnection connection = new SQLiteConnection();
        connection.conectar();

        try {
            PreparedStatement pstmt = connection.getConn().prepareStatement(query);
            pstmt.setString(1, cpfMedico);
            pstmt.setString(2, cpfPaciente);
            pstmt.setDate(3, data);
            pstmt.setString(4, descricao);
            pstmt.setBoolean(5, false);
            pstmt.setString(6, horario);
            pstmt.executeUpdate();
            cadastro = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        connection.desconectar();

        return cadastro;
    }
}
