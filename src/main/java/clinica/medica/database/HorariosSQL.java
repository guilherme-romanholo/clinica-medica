package clinica.medica.database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HorariosSQL {
    public static String[] selectHorariosDisponiveis(String medicoCpf, Date data) {
        String query = "SELECT * FROM horarios WHERE medico = ?";
        String queryVerificacao = "SELECT * FROM consultas WHERE medico = ? AND data = ?";

        SQLiteConnection connection = new SQLiteConnection();
        connection.conectar();

        ResultSet rs = null;
        ResultSet rs2 = null;

        try {
            PreparedStatement pstmt = connection.getConn().prepareStatement(query);
            pstmt.setString(1, medicoCpf);
            rs = pstmt.executeQuery();
        } catch (SQLException e){
            e.printStackTrace();
        }

        try {
            PreparedStatement pstmt = connection.getConn().prepareStatement(queryVerificacao);
            pstmt.setString(1, medicoCpf);
            pstmt.setDate(2, data);;
            rs2 = pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayList<String> horariosDisponiveis = new ArrayList<>();

        try {
            if(rs2.next()) {
                while (rs2.next()) {
                    if (!(rs2.getString("horario").equals(rs.getString("horario")))) {
                        horariosDisponiveis.add(rs.getString("horario"));
                    }
                }
            }else{
                while(rs.next())
                    horariosDisponiveis.add(rs.getString("horario"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connection.desconectar();

        return horariosDisponiveis.toArray(new String[horariosDisponiveis.size()]);
    }

    public static void salvarHorarios(String horario, String cpfMedico){
        String query = "INSERT INTO horarios (horario, medico) VALUES (?, ?)";

        SQLiteConnection connection = new SQLiteConnection();
        connection.conectar();

        try {
            PreparedStatement pstmt = connection.getConn().prepareStatement(query);
            pstmt.setString(1, horario);
            pstmt.setString(2, cpfMedico);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        connection.desconectar();
    }
}
