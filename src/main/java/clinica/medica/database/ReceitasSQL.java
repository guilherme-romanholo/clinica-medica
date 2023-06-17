package clinica.medica.database;

import clinica.medica.documentos.Receita;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class ReceitasSQL {

    public static ArrayList<Receita> selectAllReceitasFromPaciente(String cpfPaciente) {
        String query = "SELECT * FROM receitas where paciente = ?";
        ArrayList<Receita> receitas = new ArrayList<>();
        ResultSet rs = null;

        SQLiteConnection connection = new SQLiteConnection();
        connection.conectar();

        try {
            PreparedStatement pstmt = connection.getConn().prepareStatement(query);
            pstmt.setString(1, cpfPaciente);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                receitas.add(new Receita(rs.getInt("id")));
            }
        } catch (SQLException e) {
            System.out.println("Receita não foi encontrada.");
        }

        return receitas;
    }

    public static ArrayList<Receita> selectAllReceitasFromMedico(String cpfMedico) {
        String query = "SELECT * FROM receitas where medico = ?";
        ArrayList<Receita> receitas = new ArrayList<>();
        ResultSet rs = null;

        SQLiteConnection connection = new SQLiteConnection();
        connection.conectar();

        try {
            PreparedStatement pstmt = connection.getConn().prepareStatement(query);
            pstmt.setString(1, cpfMedico);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                receitas.add(new Receita(rs.getInt("id")));
            }
        } catch (SQLException e) {
            System.out.println("Receita não foi encontrada.");
        }

        return receitas;
    }

    public static ResultSet selectAllFromReceitas(int id, Connection conn) {
        String query = "SELECT * FROM receitas WHERE id = ?";
        ResultSet rs = null;

        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("Receita não foi encontrada.");
        }

        return rs;
    }

    public static boolean cadastrarReceita(Receita receita, JPanel panel){
        boolean cadastro = false;

        if(receita.getNomeDoRemedio().matches("[0-9]+")){
            JOptionPane.showMessageDialog(panel, "Não foi possível cadastrar a receita, tente novamente!", "ERRO", JOptionPane.ERROR_MESSAGE);
            return false;
        }else if(receita.getNomeDoRemedio().equals("") || receita.getDetalhes().equals("")){
            JOptionPane.showMessageDialog(panel, "O cadastro de receita não pode conter campos vazios !", "ERRO", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String queryVerificacao = "SELECT * FROM pacientes WHERE cpf = ?";
        String query = "INSERT INTO receitas (nomeDoRemedio, detalhes, medico, paciente, data) VALUES (?, ?, ?, ?, ?)";

        SQLiteConnection connection = new SQLiteConnection();
        connection.conectar();

        try {
            PreparedStatement pstmt = connection.getConn().prepareStatement(queryVerificacao);
            pstmt.setString(1, receita.getPaciente().getCpf());
            ResultSet rs = pstmt.executeQuery();
            if(!rs.next()){
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            PreparedStatement pstmt = connection.getConn().prepareStatement(query);
            pstmt.setString(1, receita.getNomeDoRemedio());
            pstmt.setString(2, receita.getDetalhes());
            pstmt.setString(3, receita.getMedico().getCpf());
            pstmt.setString(4, receita.getPaciente().getCpf());
            pstmt.setDate(5, receita.getDataReceita());
            pstmt.executeUpdate();
            cadastro = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        connection.desconectar();

        return cadastro;
    }
}
