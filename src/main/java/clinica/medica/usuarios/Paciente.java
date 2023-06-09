package clinica.medica.usuarios;

import clinica.medica.database.PacientesSQL;
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
            this.setEndereco(rs.getString("endereco"));
            this.setSexo(rs.getString("sexo"));
            this.setIdade(rs.getInt("idade"));
            this.setAltura(rs.getDouble("altura"));
            this.setPeso(rs.getDouble("peso"));
            this.setDoenca(rs.getString("doenca"));
            this.setExames(null);
        } catch (SQLException e) {
            System.out.println("Não foi possível instanciar o paciente.");
        }

        connection.desconectar();
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public ArrayList<Exame> getExames() {
        return exames;
    }

    public void setExames(ArrayList<Exame> exames) {
        this.exames = exames;
    }

    public String getDoenca() {
        return doenca;
    }

    public void setDoenca(String doenca) {
        this.doenca = doenca;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
}
