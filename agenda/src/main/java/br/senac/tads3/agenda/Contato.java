/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads3.agenda;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 * @author PC-vitrio
 */
public class Contato {
    
    private int id;
    private String nome;
    private Date strDataNasc;
    private String email;
    private String telefone; 
    
    public Contato(String nome, String strDataNasc, String email,String telefone) throws Exception {
        
        if(nome.equals("") || nome == null) {
            throw new Exception("Nome inválido");
        }
        
        if(strDataNasc.equals("") || strDataNasc == null) {
            throw new Exception("data inválida");
        }
        
        if(email.equals("") || email == null) {
            throw new Exception("Email inválido");
        }
        
        if(telefone.equals("") || telefone == null) {
            throw new Exception("Telefone inválido");
        }
        
        this.nome = nome;
        this.strDataNasc = converteData(strDataNasc);
        this.email = email;
        this.telefone = telefone;
    }
    public Contato(){}
    
    
    //get
    public String getNome() {
        return this.nome;
    }
    public Date getData() {
        return this.strDataNasc;
    }
    public String getEmail() {
        return this.email;
    }
    public String getTelefone() {
        return this.telefone;
    }    
    public int getId() {
        return this.id;
    }
    
    
    //set
    public void setId(int id) {
        this.id = id;
    }
    
    public void setDate(Date date) {
        this.strDataNasc = date;
    }
    
    public void setDate(String date) throws Exception {
        if(date.equals("") || date == null) {
            throw new Exception("Data inválida");
        }
        this.strDataNasc = converteData(date);
    }
    
    public void setNome(String nome) throws Exception {
        if(nome.equals("") || nome == null) {
            throw new Exception("nome inválido");
        }
        this.nome = nome;
    }
    
    public void setEmail(String email) throws Exception {
        if(email.equals("") || email == null) {
            throw new Exception("email inválido");
        }
        this.email = email;
    }
    
    public void setTelefone(String telefone) throws Exception {
        if(telefone.equals("") || telefone == null) {
            throw new Exception("telefone inválido");
        }
        this.telefone = telefone;
    }
    
    
    public Date converteData(String strDataNasc) throws Exception {
        DateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        Date dataNasc = null;
        try {
            dataNasc = formatador.parse(strDataNasc);
        } catch (ParseException ex) {
            throw new Exception("Data inválida");
        }
        return dataNasc;
    }
    
}
