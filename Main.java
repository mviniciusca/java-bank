public class Main {
    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        
        System.out.println("=== SISTEMA DE GERENCIAMENTO BANCÁRIO ===");
        System.out.println("Por favor, informe seus dados:");
        
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        System.out.print("Sobrenome: ");
        String sobrenome = scanner.nextLine();
        
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        
        // Criar conta bancária com os dados do cliente
        ContaBancaria conta = new ContaBancaria(nome, sobrenome, cpf);
        
        System.out.println("\nConta criada com sucesso!");
        System.out.println("Titular: " + conta.getNomeCompleto());
        
        // Exibir menu de operações
        int opcao;
        do {
            opcao = exibirMenu(scanner);
            
            switch(opcao) {
                case 1:
                    System.out.println("\n=== CONSULTAR SALDO ===");
                    System.out.printf("Saldo atual: R$ %.2f\n", conta.consultarSaldo());
                    break;
                    
                case 2:
                    System.out.println("\n=== REALIZAR DEPÓSITO ===");
                    System.out.print("Informe o valor do depósito: R$ ");
                    double valorDeposito = scanner.nextDouble();
                    scanner.nextLine(); // Limpar buffer
                    
                    if (valorDeposito <= 0) {
                        System.out.println("Valor inválido! O depósito deve ser maior que zero.");
                    } else {
                        conta.depositar(valorDeposito);
                        System.out.printf("Depósito de R$ %.2f realizado com sucesso!\n", valorDeposito);
                        System.out.printf("Novo saldo: R$ %.2f\n", conta.consultarSaldo());
                    }
                    break;
                    
                case 3:
                    System.out.println("\n=== REALIZAR SAQUE ===");
                    System.out.print("Informe o valor do saque: R$ ");
                    double valorSaque = scanner.nextDouble();
                    scanner.nextLine(); // Limpar buffer
                    
                    if (valorSaque <= 0) {
                        System.out.println("Valor inválido! O saque deve ser maior que zero.");
                    } else if (valorSaque > conta.consultarSaldo()) {
                        System.out.println("Saldo insuficiente para realizar o saque!");
                    } else {
                        conta.sacar(valorSaque);
                        System.out.printf("Saque de R$ %.2f realizado com sucesso!\n", valorSaque);
                        System.out.printf("Novo saldo: R$ %.2f\n", conta.consultarSaldo());
                    }
                    break;
                    
                case 4:
                    System.out.println("\nEncerrando o sistema...");
                    System.out.println("Obrigado por utilizar nosso sistema bancário!");
                    break;
                    
                default:
                    System.out.println("\nOpção inválida! Por favor, escolha uma opção válida.");
            }
            
        } while (opcao != 4);
        
        scanner.close();
    }
    
    public static int exibirMenu(java.util.Scanner scanner) {
        System.out.println("\n=== MENU DE OPERAÇÕES ===");
        System.out.println("1 - Consultar Saldo");
        System.out.println("2 - Realizar Depósito");
        System.out.println("3 - Realizar Saque");
        System.out.println("4 - Sair");
        System.out.print("Escolha uma opção: ");
        return scanner.nextInt();
    }
}

class ContaBancaria {
    private Cliente cliente;
    private double saldo;
    
    public ContaBancaria(String nome, String sobrenome, String cpf) {
        this.cliente = new Cliente(nome, sobrenome, cpf);
        this.saldo = 0.0;
    }
    
    public double consultarSaldo() {
        return this.saldo;
    }
    
    public void depositar(double valor) {
        this.saldo += valor;
    }
    
    public void sacar(double valor) {
        this.saldo -= valor;
    }
    
    public String getNomeCompleto() {
        return cliente.getNomeCompleto();
    }
}

class Cliente {
    private String nome;
    private String sobrenome;
    private String cpf;
    
    public Cliente(String nome, String sobrenome, String cpf) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
    }
    
    public String getNomeCompleto() {
        return this.nome + " " + this.sobrenome;
    }
    
    public String getNome() {
        return nome;
    }
    
    public String getSobrenome() {
        return sobrenome;
    }
    
    public String getCpf() {
        return cpf;
    }
}