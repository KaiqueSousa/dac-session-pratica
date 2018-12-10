/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.edu.ifpb.model.Banda;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 *
 * @author Ricardo Job
 */
public class BandasTest {

    @Test
    public void listarBandas() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/dac-cliente",
                    "postgres", "12345"
            );
            Statement createStatement = connection.createStatement();
            ResultSet result = createStatement.executeQuery(
                    "SELECT * FROM Banda;"
            );
            while (result.next()) {
                Banda criarBanda = criarBanda(result);
//                System.out.println(criarBanda);
                assertNotNull("A banda não foi criada ", criarBanda);
            }
        } catch (Exception ex) {
            Logger.getLogger(BandasTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private Banda criarBanda(ResultSet result) throws SQLException {
        String localDeOrigem = result.getString("localDeOrigem");
        String nomeFantasia = result.getString("nomeFantasia");
        int id = result.getInt("id");
        return new Banda(id, localDeOrigem, nomeFantasia);

    }

}
