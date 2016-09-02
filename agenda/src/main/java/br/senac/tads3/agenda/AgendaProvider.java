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
  // A FUNÇÃO DESTA CLASSE É RELACIONADA AOS VIEW DAS OPÇÕES DO MENU: alterar, incluir, deletar e exibir. 
    private static Scanner entrada;
    private AgendaRepository repository;
    
    public AgendaProvider() {
        this.repository = new AgendaRepository();
        this.entrada = new Scanner(System.in);
    }
// INCLUIR CONTATO NA AGENDA
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
    //ATUALIZAR CONTATO NA AGENDA
    public void atualizar() throws Exception {
        // ELE COMEÇA PEDINDO O E-MAIL , QUE COSTUMA SER UNICO
        System.out.print("Digite o email do contato: ");
        String emailfind = entrada.nextLine();
        Contato agenda = this.repository.getByEmail(emailfind);
        if(agenda == null) {
            //SE ELE NAO LOCALIZAR O EMAIL, NAO DEIXA VOCE SEGUIR COM A ALTERAÇÃO
            throw new Exception("Contato não encontrado !");
        }
        // CASO O EMAIL ESTEJA CORRETO, SERA NECESSARIO INCLUIR OUTROS INFORMAÇÕES, COMO MEIO SE SEGURANÇA PARA CERTIFICAR QUE ESTA EXCLUINDO O CORRETO
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
    // APRESENTA TODOS OS CONTATOS DA LISTA
    public void pesquisa() throws Exception{
        List<Contato> agenda = this.repository.listar();
        if(agenda == null) {
            // CASO NAO TENHA CONTATO APRESENTA O SEGUINTE: 
            throw new Exception("Agenda vazia !");
        }
        // SE NAO ESTIVER VAZIA, APRESENTA OS CONTATOS COM ESTE LAYOUT
        for(Contato contato:agenda) {
            System.out.println("Contato id:  "+contato.getId() + "<======");
            System.out.println("nome :"+ contato.getNome());
            System.out.println("email :"+ contato.getEmail());
            System.out.println("data :"+ contato.getData());
            System.out.println("telefone :"+ contato.getTelefone());
        }
    }
 // APAGA UM CONTATO DA LISTA 
    public void apagar() throws Exception {
        // SERA NECESSARIO INFORMAR O EMAIL A DO CONTATO A SER DELETADO
        System.out.print("Digite o email do contato a ser apagado: ");
        String email = entrada.nextLine();
        
        Contato agenda = this.repository.getByEmail(email);
        repository.apagar(agenda.getId());
    }

    

}
