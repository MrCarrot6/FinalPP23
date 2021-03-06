/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pp_finalproject;

import estgconstroi.exceptions.ConstructionSiteManagerException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author Utilizador
 */
public class MenosClass {

    private ConstructionSiteManagerClass Constructions;

    public void PrincipalMenu() throws ConstructionSiteManagerException {
        Scanner Read = new Scanner(System.in);
        int choice;
        do {
            System.out.println("========== MENU PRINCIPAL ==========");
            System.out.println("1 - MENU DE CONSTRUÇÕES");
            System.out.println("2 - MENU DE EVENTOS");
            System.out.println("0 - SAIR");
            System.out.println("====================================");
            System.out.println("Escolha: ");
            choice = Read.nextInt();
        } while (choice < 0 || choice > 2);
        switch (choice) {
            case 0:
                break;
            case 1:
                ConstructionMenu();
            case 2:
                break;

        }
    }

    public void ConstructionMenu() throws ConstructionSiteManagerException {
        Scanner Read = new Scanner(System.in);
        int choice;
        do {
            System.out.println("========== MENU DE CONSTRUÇÕES ==========");
            System.out.println("1 - CRIAR NOVO LOCAR DE CONSTRUÇÃO");
            System.out.println("2 - INFORMAÇÃO SOBRE TODAS AS CONSTRUÇÕES");
            System.out.println("3 - REMOVER CONSTRUÇÃO");
            System.out.println("4 - EDITAR UMA CONSTRUÇÃO");
            System.out.println("0 - VOLTAR AO MENU PRINCIPAL");
            System.out.println("==========================================");
            choice = Read.nextInt();
        } while (choice < 0 || choice > 4);
        switch (choice) {
            case 0:
                PrincipalMenu();
            case 1:
                CreateConstructionSite();
            case 2:
                this.Constructions.showAllConstructionSites();
            case 3:
                 RemoveConstructionMenu();
            case 4:
                break;
        }
    }

    public void CreateConstructionSite() throws ConstructionSiteManagerException {
        String TempName, TempLocation, TempPermit,TempDate;
        ConstructionSiteClass cs;
        Scanner Read = new Scanner(System.in);
        System.out.println("NOME DA CONSTRUÇÃO: ");
        TempName = Read.next();
        System.out.println("LOCALIZAÇÃO: ");
        TempLocation = Read.next();
        System.out.println("PERMIT: ");
        TempPermit = Read.next();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("DATA DE EXPIRAÇÃO (dd/mm/yyyy): ");
        TempDate = Read.next();
        LocalDate localDate = LocalDate.parse(TempDate, formatter);
        cs = new ConstructionSiteClass(TempName, TempLocation, localDate, TempPermit);
        this.Constructions.add(cs);
    }
    public void RemoveConstructionMenu() throws ConstructionSiteManagerException{
        Scanner Read = new Scanner(System.in);
        int Choice;
        do{
        System.out.println("========== LISTA DE COSNTRUÇÕES ==========");
        ListOfTeamsMenu();
        System.out.println("0 - VOLTAR ATRÁS");
        System.out.println("==========================================");
        System.out.println("OPÇÃO: ");
        Choice = Read.nextInt();
        }while(Choice < 0 || Choice > this.Constructions.NumberOfConstructions);
        switch(Choice){
            case 0:
                ConstructionMenu();
            default:
                this.Constructions.remove(Choice);
        }
    }
    public void ListOfTeamsMenu(){
        for(int i = 0;i < this.Constructions.NumberOfConstructions; i++){
            System.out.println((i + 1)+ " - " + this.Constructions.ConstructionSites[i].getName());
        }
    }
}
