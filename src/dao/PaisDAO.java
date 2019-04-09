package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Pais;

public class PaisDAO {
	
	public int criar(Pais p) {
		String sqlInsert = "INSERT INTO Paises(nome, populacao, area) VALUES (?, ?, ?)";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setString(1, p.getNome());
			stm.setLong(2, p.getPopulacao());
			stm.setDouble(3, p.getArea());
			stm.execute();
			String sqlQuery  = "SELECT LAST_INSERT_ID()";
			try(PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
				ResultSet rs = stm2.executeQuery();) {
				if(rs.next()){
					p.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public void atualizar(Pais p) {
		String sqlUpdate = "UPDATE Paises SET nome=?, populacao=?, area=? WHERE id=?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, p.getNome());
			stm.setLong(2, p.getPopulacao());
			stm.setDouble(3, p.getArea());
			stm.setInt(4, p.getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excluir(int id) {
		String sqlDelete = "DELETE FROM Paises WHERE id = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, id);
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Pais carregar(int id) {
		
		Pais p = new Pais();
		p.setId(id);
		String sqlSelect = "SELECT nome, populacao, area FROM Paises WHERE id = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, p.getId());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					p.setNome(rs.getString("nome"));
					p.setPopulacao(rs.getLong("populacao"));
					p.setArea(rs.getDouble("area"));
				} else {
					p.setId(-1);
					p.setNome(null);
					p.setPopulacao(0);
					p.setArea(0);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return p;
	}

	public Pais comMaisHabitantes(Pais p) {
	Pais comMaisHabitantes = new Pais();
	String sqlSelect = "SELECT id, nome, populacao, area FROM Paises WHERE populacao=(select max(populacao) from paises)";
	// usando o try with resources do Java 7, que fecha o que abriu
	try (Connection conn = ConnectionFactory.obtemConexao();
			PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
		try (ResultSet rs = stm.executeQuery();) {
			if (rs.next()) {
				comMaisHabitantes.setId(rs.getInt("id"));
				comMaisHabitantes.setNome(rs.getString("nome"));
				comMaisHabitantes.setPopulacao(rs.getLong("populacao"));
				comMaisHabitantes.setArea(rs.getDouble("area"));
			} else {
				p.setId(-1);
				p.setNome(null);
				p.setPopulacao(0);
				p.setArea(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} catch (SQLException e1) {
		System.out.print(e1.getStackTrace());
	}
	return comMaisHabitantes;
	

	

}}

//prof.andersonsanches@usjt.br