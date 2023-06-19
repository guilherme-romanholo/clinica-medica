package clinica.medica.database;

import clinica.medica.consultas.Consulta;
import clinica.medica.documentos.Receita;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
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

    public static ArrayList<Consulta> selectAllConsultasFromPaciente(String cpfPaciente, Date data) {
        String query = "SELECT * FROM consultas where paciente = ? AND data = ?";
        ArrayList<Consulta> consultas = new ArrayList<>();
        ResultSet rs = null;

        SQLiteConnection connection = new SQLiteConnection();
        connection.conectar();

        LocalDate localDate = data.toLocalDate();
        String dataFormatada = localDate.getYear() + "-" + localDate.getMonthValue() + "-" + localDate.getDayOfMonth();

        try {
            PreparedStatement pstmt = connection.getConn().prepareStatement(query);
            pstmt.setString(1, cpfPaciente);
            pstmt.setString(2 , dataFormatada);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                consultas.add(new Consulta(rs.getInt("id")));
            }
        } catch (SQLException e) {
            System.out.println("Receita não foi encontrada.");
        }

        return consultas;
    }

    public static ArrayList<Consulta> selectAllConsultasFromMedico(String cpfMedico, Date data) {
        String query = "SELECT * FROM consultas where medico = ? AND data = ?";
        ArrayList<Consulta> consultas = new ArrayList<>();
        ResultSet rs = null;

        SQLiteConnection connection = new SQLiteConnection();
        connection.conectar();

        LocalDate localDate = data.toLocalDate();
        String dataFormatada = localDate.getYear() + "-" + localDate.getMonthValue() + "-" + localDate.getDayOfMonth();

        try {
            PreparedStatement pstmt = connection.getConn().prepareStatement(query);
            pstmt.setString(1, cpfMedico);
            pstmt.setString(2, dataFormatada);
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

    public static boolean salvarConsulta(Date data, String cpfMedico, String cpfPaciente, String descricao, String horario, JPanel painel){
        boolean cadastro = false;
        String query = "INSERT INTO consultas (medico, paciente, data, descricao, realizada, horario, motivoCancelamento) VALUES (?, ?, ?, ?, ?, ?, ?)";
        if(descricao.equals("")){
            JOptionPane.showMessageDialog(painel, "O cadastro de consulta não pode conter campos vazios !", "ERRO", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        SQLiteConnection connection = new SQLiteConnection();
        connection.conectar();

        LocalDate localDate = data.toLocalDate();
        String dataFormatada = localDate.getYear() + "-" + localDate.getMonthValue() + "-" + localDate.getDayOfMonth();

        try {
            PreparedStatement pstmt = connection.getConn().prepareStatement(query);
            pstmt.setString(1, cpfMedico);
            pstmt.setString(2, cpfPaciente);
            pstmt.setString(3, dataFormatada);
            pstmt.setString(4, descricao);
            pstmt.setBoolean(5, false);
            pstmt.setString(6, horario);
            pstmt.setString(7, "");
            pstmt.executeUpdate();
            cadastro = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        connection.desconectar();

        return cadastro;
    }

    public static boolean salvarEncaixe(Date data, String cpfMedico, String cpfPaciente, String descricao,String motivo, String horario, JPanel painel){
        boolean cadastro = false;
        String queryVerificacao = "SELECT * FROM pacientes WHERE cpf = ?";
        String query = "INSERT INTO consultas (medico, paciente, data, descricao, realizada, horario, motivoCancelamento) VALUES (?, ?, ?, ?, ?, ?, ?)";
        if(descricao.equals("") || motivo.equals("")){
            JOptionPane.showMessageDialog(painel, "O cadastro encaixe não pode conter campos vazios !", "ERRO", JOptionPane.ERROR_MESSAGE);
            return false;
        }else if(cpfPaciente.contains("_")){
            JOptionPane.showMessageDialog(painel, "CPF do paciente inválido !", "ERRO", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        SQLiteConnection connection = new SQLiteConnection();
        connection.conectar();

        try {
            PreparedStatement pstmt = connection.getConn().prepareStatement(queryVerificacao);
            pstmt.setString(1, cpfPaciente);
            ResultSet rs = pstmt.executeQuery();
            if(!rs.next()){
                JOptionPane.showMessageDialog(painel, "Esse paciente não existe !", "ERRO", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        LocalDate localDate = data.toLocalDate();
        String dataFormatada = localDate.getYear() + "-" + localDate.getMonthValue() + "-" + localDate.getDayOfMonth();

        try {
            PreparedStatement pstmt = connection.getConn().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, cpfMedico);
            pstmt.setString(2, cpfPaciente);
            pstmt.setString(3, dataFormatada);
            pstmt.setString(4, descricao);
            pstmt.setBoolean(5, false);
            pstmt.setString(6, horario);
            pstmt.setString(7, "");
            int verificacao = pstmt.executeUpdate();
            if(verificacao > 0){
                ResultSet chaveGerada = pstmt.getGeneratedKeys();
                int chavePrimaria = chaveGerada.getInt(1);
                String queryEncaixe = "INSERT INTO encaixes (motivoEmergencia, idConsulta) VALUES (?, ?)";
                PreparedStatement pstmt2 = connection.getConn().prepareStatement(queryEncaixe);
                pstmt2.setString(1, motivo);
                pstmt2.setInt(2, chavePrimaria);
                pstmt2.executeUpdate();
                cadastro = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        connection.desconectar();

        return cadastro;
    }

    public static void atualizaConsulta(int id){
        String query = "UPDATE consultas SET realizada = 1 WHERE id = ?";

        SQLiteConnection connection = new SQLiteConnection();
        connection.conectar();

        try {
            PreparedStatement pstmt = connection.getConn().prepareStatement(query);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        connection.desconectar();
    }

    public static void atualizaConsulta(int id, String motivoCancelamento){
        String query = "UPDATE consultas SET realizada = true, motivoCancelamento = ? WHERE id = ?";

        SQLiteConnection connection = new SQLiteConnection();
        connection.conectar();

        try {
            PreparedStatement pstmt = connection.getConn().prepareStatement(query);
            pstmt.setString(1,motivoCancelamento);
            pstmt.setInt(2,id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        connection.desconectar();
    }
}
