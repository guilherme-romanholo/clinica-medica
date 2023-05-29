package clinica.medica.database;

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
}
