/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads3.agenda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author fernando.tsuda
 */
public class AgendaProvider extends ConexaoBD {
  
    private static Scanner entrada;
    private AgendaRepository repository;
    
    public AgendaProvider() {
        this.repository = new AgendaRepository();
        this.entrada = new Scanner(System.in);
    }

    public void incluir() throws Exception {
        System.out.print("Digite o nome completo do contato: ");
        String nome = entrada.nextLine();

        System.out.print("Digite a data de nascimento no formato dd/mm/aaaa: ");
        String strDataNasc = entrada.nextLine();

        System.out.print("Digite o e-mail");
        String email = entrada.nextLine();

        System.out.print("Digite o telefone no formato 99 99999-9999");
        String telefone = entrada.nextLine();

        Contato agenda = new Contato(nome, strDataNasc, email, telefone);
        repository.incluir(agenda);        
    }
    
    public void atualizar() throws Exception {
        
        System.out.print("Digite o email do contato: ");
        String emailfind = entrada.nextLine();
        Contato agenda = this.repository.getByEmail(emailfind);
        if(agenda == null) {
            throw new Exception("Contato não encontrado !");
        }
        
        System.out.print("Digite o nome completo do contato: ");
        agenda.setNome(entrada.nextLine()); 

        System.out.print("Digite a data de nascimento no formato dd/mm/aaaa: ");
        agenda.setDate(entrada.nextLine());

        System.out.print("Digite o e-mail");
        agenda.setEmail(entrada.nextLine());

        System.out.print("Digite o telefone no formato 99 99999-9999");
        agenda.setTelefone(entrada.nextLine());
        
        repository.atualizar(agenda);        
    }
    
    public void pesquisa() throws Exception{
        List<Contato> agenda = this.repository.listar();
        if(agenda == null) {
            throw new Exception("Agenda vazia !");
        }
        for(Contato contato:agenda) {
            System.out.println("Contato id:  "+contato.getId() + "==============================");
            System.out.println("nome :"+ contato.getNome());
            System.out.println("email :"+ contato.getEmail());
            System.out.println("data :"+ contato.getData());
            System.out.println("telefone :"+ contato.getTelefone());
        }
    }
 
    public void apagar() throws Exception {
        System.out.print("Digite o email do contato a ser apagado: ");
        String email = entrada.nextLine();
        
        Contato agenda = this.repository.getByEmail(email);
        repository.apagar(agenda.getId());
    }

    

}
