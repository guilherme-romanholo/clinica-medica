package clinica.medica.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConnection {
    private Connection conn;

    public SQLiteConnection(){
        this.conn = null;
    }

    public void conectar(String databasePath) {
        try {
            this.conn = DriverManager.getConnection("jdbc:sqlite:" + databasePath);
        } catch (SQLException e) {
            System.out.println("Não foi possível realizar a conexão com o Banco de Dados.");
        }
    }

    public void desconectar() {
        try {
            if (this.conn != null)
                this.conn.close();
        } catch (SQLException e) {
            System.out.println("Não foi possível fechar a conexão com o Banco de Dados.");
        }
    }

    public Connection getConn() {
        return this.conn;
    }
}