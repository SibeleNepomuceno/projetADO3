/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads3.agenda;

import java.util.Scanner;

/**
 *
 * @author PC-vitrio
 */
public class Main {
    public static void main(String[] args) {
        AgendaProvider instancia = new AgendaProvider();
        Scanner entrada = new Scanner(System.in);

        do {
            System.out.println("***** DIGITE UMA OPÇÃO *****");
            System.out.println("(1) Listar contatos");
            System.out.println("(2) Incluir novo contato");
            System.out.println("(3) Deletar contato");
            System.out.println("(4) Atualizar contato");
            System.out.println("(9) Sair");
            System.out.print("Opção: ");

            String strOpcao = entrada.nextLine();
            int opcao = Integer.parseInt(strOpcao);
            switch (opcao) {
                case 1:
                    try {
                        instancia.pesquisa();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        instancia.incluir();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        instancia.apagar();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        instancia.atualizar();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 9:
                    System.exit(0);
                    break;
                default:
                    System.out.println("OPÇÃO INVÁLIDA");
            }
        } while (true);

    }
}
