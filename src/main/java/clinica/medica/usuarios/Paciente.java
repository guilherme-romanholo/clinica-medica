package clinica.medica.usuarios;

import clinica.medica.database.SQLiteConnection;
import clinica.medica.database.UsuariosSQL;
import clinica.medica.documentos.Exame;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

public class Paciente extends Usuario{
    private String endereco;
    private ArrayList<Exame> exames;
    private String doenca;
    private String sexo;
    private int idade;
    private double altura;
    private double peso;
    
    public Paciente(String pacienteCpf){
        super(pacienteCpf);

        SQLiteConnection connection = new SQLiteConnection();
        connection.conectar();

        ResultSet rs = UsuariosSQL.selectAllFromPaciente(pacienteCpf, connection.getConn());

        try {
            this.endereco = rs.getString("endereco");
            this.sexo = rs.getString("sexo");
            this.idade = rs.getInt("idade");
            this.altura = rs.getDouble("altura");
            this.peso = rs.getDouble("peso");
            this.doenca = rs.getString("doenca");
            this.exames = null;
        } catch (SQLException e) {
            System.out.println("Não foi possível instanciar o paciente.");
        }

        connection.desconectar();
    }
    
}
