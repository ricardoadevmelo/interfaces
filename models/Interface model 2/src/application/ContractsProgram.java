package application;

import entities.Contract;
import entities.Installment;
import services.ContractService;
import services.PaypalService;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ContractsProgram {
    public static void main(String[] args) throws ParseException {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Among the details of the contract:: ");
        System.out.print("Number: ");
        int contractNumber = sc.nextInt();
        System.out.print("Date (dd/MM/yyyy): ");
        LocalDate contractDate = LocalDate.parse(sc.next(), fmt);
        System.out.print("Contract value: ");
        double contractValue = sc.nextDouble();

        Contract contract = new Contract(contractNumber, contractDate, contractValue);

        System.out.print("Enter the number of installments: ");
        int installments = sc.nextInt();

        ContractService contractService = new ContractService(new PaypalService());

        contractService.processContract(contract, installments);

        System.out.println("Installments: ");

        for (Installment installment : contract.getInstallments()) {
            System.out.println(installment);
        }

        sc.close();
    }
}