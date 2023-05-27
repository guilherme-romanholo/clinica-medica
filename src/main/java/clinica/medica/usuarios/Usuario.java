package clinica.medica.usuarios;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Usuario {
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    
    public Usuario(ResultSet rs){
        try{
        this.nome = rs.getString("nome");
        this.cpf = rs.getNString("CPF");
        this.email = rs.getString("email");
        this.senha = rs.getString("senha");
        }catch(SQLException e){
            System.out.println("Não foi possível encontrar o usuário, tente novamente!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
    }
}
