/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads3.agenda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC-vitrio
 */
public class AgendaRepository {
    private final ConexaoBD _con;
    public AgendaRepository() {
       this._con = new ConexaoBD();
    }
    
    public void atualizar(Contato agenda) throws Exception {
        PreparedStatement stmt = null;
        Connection conn = null;

        String sql = "UPDATE TB_CONTATO\n" +
                    "SET NM_CONTATO=?,DT_NASCIMENTO=?, VL_TELEFONE=?, VL_EMAIL=?" +
                    "WHERE ID_CONTATO=?";

        try {
            conn = this._con.obterConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, agenda.getNome());
            stmt.setDate(2, new java.sql.Date(agenda.getData().getTime()));
            stmt.setString(3, agenda.getTelefone());
            stmt.setString(4, agenda.getEmail());
            stmt.setInt(5, agenda.getId());

            // 2) Executar SQL
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Não foi possível executar.1");
        } catch (ClassNotFoundException e) {
            throw new Exception("Não foi possível executar.2");
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar stmt.");
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar conn.");
                }
            }
        }
    }
    
    public void incluir(Contato agenda) throws Exception {        
        PreparedStatement stmt = null;
        Connection conn = null;

        String sql = "INSERT INTO TB_CONTATO (NM_CONTATO, DT_NASCIMENTO, "
                + "VL_TELEFONE, VL_EMAIL, DT_CADASTRO) "
                + "VALUES (?, ?, ?, ?, ?)";

        try {
            conn = this._con.obterConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, agenda.getNome());
            stmt.setDate(2, new java.sql.Date(agenda.getData().getTime()));
            stmt.setString(3, agenda.getTelefone());
            stmt.setString(4, agenda.getEmail());
            stmt.setDate(5, new java.sql.Date(System.currentTimeMillis()));

            // 2) Executar SQL
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Não foi possível executar.1");
        } catch (ClassNotFoundException e) {
            throw new Exception("Não foi possível executar.2");
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar stmt.");
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar conn.");
                }
            }
        }
    }
    
    public Contato getByEmail(String emailFind) throws Exception {
        Statement  stmt = null;
        Connection conn = null;
        Contato agenda = null;

        String sql = "SELECT * FROM TB_CONTATO WHERE VL_EMAIL = '"+emailFind+"'";

        try {
            conn = this._con.obterConexao();
            stmt = conn.createStatement();
            
            // 2) Executar SQL
            ResultSet rs = stmt.executeQuery(sql);
            
            if(rs.next()) {
                agenda = new Contato();
                agenda.setId(rs.getInt("ID_CONTATO"));
                agenda.setNome(rs.getString("NM_CONTATO"));
                agenda.setDate(rs.getDate("DT_NASCIMENTO"));
                agenda.setEmail(rs.getString("VL_EMAIL"));
                agenda.setTelefone(rs.getString("VL_TELEFONE"));
            }

            return agenda;
            
        } catch (SQLException e) {
            throw new Exception("Não foi possível executar.1");
        } catch (ClassNotFoundException e) {
            throw new Exception("Não foi possível executar.2");
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar stmt.");
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar conn.");
                }
            }
        }
    }
    
    public void apagar(int id) throws Exception {
        PreparedStatement stmt = null;
        Connection conn = null;

        String sql = "DELETE FROM TB_CONTATO WHERE ID_CONTATO = ?";

        try {
            conn = this._con.obterConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,id);
            
            // 2) Executar SQL
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Não foi possível executar.1");
        } catch (ClassNotFoundException e) {
            throw new Exception("Não foi possível executar.2");
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar stmt.");
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar conn.");
                }
            }
        }
    }
    
    public List<Contato> listar() throws Exception {
        Statement  stmt = null;
        Connection conn = null;
        Contato contato = null;
        List<Contato> agenda = null;

        String sql = "SELECT * FROM TB_CONTATO";

        try {
            conn = this._con.obterConexao();
            stmt = conn.createStatement();
            
            // 2) Executar SQL
            ResultSet rs = stmt.executeQuery(sql);
            agenda = new ArrayList();
            while(rs.next()) {
                contato = new Contato();
                contato.setId(rs.getInt("ID_CONTATO"));
                contato.setNome(rs.getString("NM_CONTATO"));
                contato.setDate(rs.getDate("DT_NASCIMENTO"));
                contato.setEmail(rs.getString("VL_EMAIL"));
                contato.setTelefone(rs.getString("VL_TELEFONE"));
                agenda.add(contato);
            }

            return agenda;
            
        } catch (SQLException e) {
            throw new Exception("Não foi possível executar.1");
        } catch (ClassNotFoundException e) {
            throw new Exception("Não foi possível executar.2");
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar stmt.");
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar conn.");
                }
            }
        }
    }
    
    
}
