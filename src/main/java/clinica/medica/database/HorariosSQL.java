package clinica.medica.database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
            pstmt.setLong(2, data.getTime());
            rs2 = pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayList<String> horariosDisponiveis = new ArrayList<>();

        try {
            while (rs.next()) {
                System.out.println("teste");
                horariosDisponiveis.add(rs.getString("horario"));
            }
            while (rs2.next()) {
                System.out.println(rs2.getString("horario"));
                if (horariosDisponiveis.contains(rs2.getString("horario"))){
                    horariosDisponiveis.remove(rs2.getString("horario"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connection.desconectar();

        String[] lista = horariosDisponiveis.toArray(new String[horariosDisponiveis.size()]);

        if (horariosDisponiveis.size() == 0) {
            String[] ret = new String[1];
            ret[0] = "Não existem horários disponíveis nesse dia.";
            return ret;
        } else {
            return lista;
        }
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
