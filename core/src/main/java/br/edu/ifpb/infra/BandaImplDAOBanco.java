package br.edu.ifpb.infra;

import br.edu.ifpb.intefaces.BandaInterface;
import br.edu.ifpb.model.Banda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import javax.annotation.Resource;

/**
 *
 * @author kaique
 */
@Stateless
@Remote(BandaInterface.class)
public class BandaImplDAOBanco implements BandaInterface{
    
    @Resource(lookup = "java:app/jdbc/sessionbeans")
    private DataSource dataSource;

    @Override
    public boolean salvar(Banda banda) {
        try (Connection conn = dataSource.getConnection()){
            PreparedStatement createStatement = conn.prepareStatement(
                    "INSERT INTO Banda VALUES (?,?);"
            );
            createStatement.setString(1, banda.getLocalOrigem());
            createStatement.setString(2, banda.getNomeFantasia());
            createStatement.executeUpdate();
            createStatement.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean excluir(int id) {
        try(Connection conn = dataSource.getConnection()) {
            Statement createStatement = conn.createStatement();
            ResultSet result = createStatement.executeQuery("DELETE CASCADE FROM Banda WHERE id = ?;"+id);
            createStatement.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean atualizar(Banda banda) {
        try (Connection conn = dataSource.getConnection()){
            PreparedStatement createStatement = conn.prepareStatement("UPDATE Banda SET localDeorigem = ?, nomeFantasia = ? WHERE id = ?");
            createStatement.setString(1, banda.getLocalOrigem());
            createStatement.setString(2, banda.getNomeFantasia());
            createStatement.setInt(3, banda.getId());
            createStatement.executeUpdate();
            createStatement.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Banda> listarBandas() {
        List<Banda> lista = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()){
            Statement createStatement = conn.createStatement();
            ResultSet result = createStatement.executeQuery("SELECT * FROM Banda;");
            iterarComBandas(result, lista);
            createStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
    
    private void iterarComBandas(ResultSet result, List<Banda> lista) throws SQLException {
        while (result.next()) {
            lista.add(
                    criarBanda(result)
            );
        }
    }
    
    private Banda criarBanda(ResultSet result) throws SQLException {
        String localDeOrigem = result.getString("localDeOrigem");
        String nomeFantasia = result.getString("nomeFantasia");
        String integrantes = result.getString("");
        int id = result.getInt("id");

        return new Banda(id, localDeOrigem, nomeFantasia);

    }   
}