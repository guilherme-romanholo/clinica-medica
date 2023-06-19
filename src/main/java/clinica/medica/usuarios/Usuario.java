package clinica.medica.usuarios;

import clinica.medica.database.SQLiteConnection;
import clinica.medica.database.UsuariosSQL;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Usuario {
    private String nome;
    private String cpf;
    private String email;
    private String senha;

    public Usuario(String userCpf) {
        SQLiteConnection connection = new SQLiteConnection();
        connection.conectar();

        ResultSet rs = UsuariosSQL.selectAllFromUsuario(userCpf, connection.getConn());

        try {
            this.nome = rs.getString("nome");
            this.cpf = rs.getString("cpf");
            this.email = rs.getString("email");
            this.senha = rs.getString("senha");
        } catch (SQLException e) {
            System.out.println("Não foi possível instanciar o usuário.");
        }

        connection.desconectar();
    }

    public String getNome() {
        return this.nome;
    }

    public String getCpf() {
        return this.cpf;
    }
}
