package clinica.medica.database;

import clinica.medica.gui.LoginUI;
import clinica.medica.usuarios.Medico;
import clinica.medica.usuarios.Paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe para realizar a consulta das informações referentes aos
 * usuarios no banco de dados.
 */
public class UsuariosSQL {
    /**
     * Método para leitura dos dados, referentes à classe Usuario,
     * presentes no banco de dados.
     * @param userCpf Cpf do usuario procurado no banco de dados.
     * @param conn Conexão com o banco de dados.
     * @return ResultSet com as informações do usuario
     */
    public static ResultSet selectAllFromUsuario(String userCpf, Connection conn) {
        String query = "SELECT * FROM usuarios WHERE cpf = ?";
        ResultSet rs = null;

        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, userCpf);
            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("Usuário não foi encontrado.");
        }

        return rs;
    }

    /**
     * Método para a leitura dos dados, referentes à classe Paciente,
     * presentes no banco de dados.
     * @param pacienteCpf Cpf do paciente procurado no banco de dados.
     * @param conn Conexão com o banco de dados.
     * @return ResultSet com as informações do paciente.
     */
    public static ResultSet selectAllFromPaciente(String pacienteCpf, Connection conn) {
        String query = "SELECT * FROM pacientes WHERE cpf = ?";
        ResultSet rs = null;

        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, pacienteCpf);
            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("Paciente não foi encontrado.");
        }

        return rs;
    }

    /**
     * Método para a leitura dos dados, referentes à classe Medico,
     * presentes no banco de dados.
     * @param medicoCpf Cpf do médico procurado no banco de dados.
     * @param conn Conexão com o banco de dados.
     * @return ResultSet com as informações do médico.
     */
    public static ResultSet selectAllFromMedico(String medicoCpf, Connection conn) {
        String query = "SELECT * FROM medicos WHERE cpf = ?";
        ResultSet rs = null;

        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, medicoCpf);
            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("Médico não foi encontrado.");
        }

        return rs;
    }
    
    public static boolean cadastroMedico(String nome, String cpf, String email, String senha, String areaAtuacao, String crm){
        boolean cadastro = false;
        //verifica validade dos dados digitados
        if(nome.matches("[0-9]+") || cpf.matches("[A-Z]+") || areaAtuacao.matches("[0-9]+") || crm.matches("[A-Z]+")){
            return false;
        }else if(nome.equals("") || cpf.equals("") || email.equals("") || senha.equals("") || areaAtuacao.equals("") || crm.equals("")){
            return false;
        }
        String query = "INSERT INTO usuarios (nome, cpf, email, senha) VALUES (?, ?, ?, ?)";
        String queryMedico = "INSERT INTO medicos (cpf, areaAtuacao, crm) VALUES (?, ?, ?)";
        
        SQLiteConnection connection = new SQLiteConnection();
        connection.conectar();
    
        try{
            PreparedStatement pstmt = connection.getConn().prepareStatement(query);
            pstmt.setString(1, nome);
            pstmt.setString(2, cpf);
            pstmt.setString(3, email);
            pstmt.setString(4, senha);
            pstmt.executeUpdate();
            pstmt = connection.getConn().prepareStatement(queryMedico);
            pstmt.setString(1, cpf);
            pstmt.setString(2, areaAtuacao);
            pstmt.setString(3, crm);
            pstmt.executeUpdate();
            cadastro = true;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        connection.desconectar();
        return cadastro;
    }
    
    public static Medico loginMedico(String nome, String senha){
        Medico medicoLogado = null;
        String query = "SELECT * FROM usuarios U INNER JOIN medicos M ON U.cpf = M.cpf WHERE nome = ? and senha = ?";
        ResultSet rs;
        
        SQLiteConnection connection = new SQLiteConnection();
        connection.conectar();
        
        try{
            PreparedStatement pstmt = connection.getConn().prepareStatement(query);
            pstmt.setString(1,nome);
            pstmt.setString(2, senha);
            rs = pstmt.executeQuery();
            if(rs.next()){
                medicoLogado = new Medico(rs.getString("cpf"));
            }
               
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        connection.desconectar();
        return medicoLogado;
    }
    
    public static boolean cadastroPaciente(String nome, String cpf, String email, String senha, String endereco, String sexo, int idade, double altura, double peso){
        boolean cadastro = false;
        //verifica validade dos dados digitados
        if(nome.matches("[0-9]+") || cpf.matches("[A-Z]+") || endereco.matches("[0-9]+") || sexo.matches("[0-9]+")){
            return false;
        }else if(nome.equals("") || cpf.equals("") || email.equals("") || senha.equals("") || endereco.equals("") || sexo.equals("")){
            return false;
        }else if(idade == 0 || peso == 0 || altura == 0){
            return false;
        }
        String query = "INSERT INTO usuarios (nome, cpf, email, senha) VALUES (?, ?, ?, ?)";
        String queryPaciente = "INSERT INTO pacientes (cpf, endereco, sexo, idade, altura, peso) VALUES (?, ?, ?, ?, ?, ?)";
        
        SQLiteConnection connection = new SQLiteConnection();
        connection.conectar();
    
        try{
            PreparedStatement pstmt = connection.getConn().prepareStatement(query);
            pstmt.setString(1, nome);
            pstmt.setString(2, cpf);
            pstmt.setString(3, email);
            pstmt.setString(4, senha);
            pstmt.executeUpdate();
            pstmt = connection.getConn().prepareStatement(queryPaciente);
            pstmt.setString(1, cpf);
            pstmt.setString(2, endereco);
            pstmt.setString(3, sexo);
            pstmt.setInt(4, idade);
            pstmt.setDouble(5,altura);
            pstmt.setDouble(6,peso);
            pstmt.executeUpdate();
            cadastro = true;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        connection.desconectar();
        return cadastro;
    }
    
    public static Paciente loginPaciente(String nome, String senha){
        Paciente pacienteLogado = null;
        String query = "SELECT * FROM usuarios U INNER JOIN pacientes P ON U.cpf = P.cpf WHERE nome = ? and senha = ?";
        ResultSet rs;
        
        SQLiteConnection connection = new SQLiteConnection();
        connection.conectar();
        
        try{
            PreparedStatement pstmt = connection.getConn().prepareStatement(query);
            pstmt.setString(1,nome);
            pstmt.setString(2, senha);
            rs = pstmt.executeQuery();
            if(rs.next()){
                pacienteLogado = new Paciente(rs.getString("cpf"));
            }
               
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        connection.desconectar();
        return pacienteLogado;
    }
}
