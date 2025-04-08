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
        
        ContaBancaria conta = new ContaBancaria(nome, sobrenome, cpf);
        
        System.out.println("\nConta criada com sucesso! Dados da conta:");
        System.out.println("Titular: " + conta.getNomeCompleto());
        
        int opcao;
        do {
            opcao = exibirMenu(scanner);
            
            switch(opcao) {
                case 1:
                    System.out.println("\n=== CONSULTAR SALDO ===");
                    System.out.printf("Saldo atual: R$ %.2f\n", conta.consultarSaldo());
                    break;
                    
                case 2:
                    System.out.println("\n=== OPERAÇÃO DE DEPÓSITO ===");
                    System.out.print("Digite o valor a ser depositado: R$ ");
                    double valorDeposito = scanner.nextDouble();
                    scanner.nextLine();
                    
                    if (valorDeposito <= 0) {
                        System.out.println("Erro: O valor do depósito precisa ser positivo.");
                    } else {
                        conta.depositar(valorDeposito);
                        System.out.printf("Depósito de R$ %.2f efetuado com êxito!\n", valorDeposito);
                        System.out.printf("Seu saldo atual: R$ %.2f\n", conta.consultarSaldo());
                    }
                    break;
                    
                case 3:
                    System.out.println("\n=== OPERAÇÃO DE SAQUE ===");
                    System.out.print("Digite o valor a ser sacado: R$ ");
                    double valorSaque = scanner.nextDouble();
                    scanner.nextLine();
                    
                    if (valorSaque <= 0) {
                        System.out.println("Erro: O valor do saque precisa ser positivo.");
                    } else if (valorSaque > conta.consultarSaldo()) {
                        System.out.println("Operação não realizada: Saldo insuficiente para este saque.");
                    } else {
                        conta.sacar(valorSaque);
                        System.out.printf("Saque de R$ %.2f efetuado com êxito!\n", valorSaque);
                        System.out.printf("Seu saldo atual: R$ %.2f\n", conta.consultarSaldo());
                    }
                    break;
                    
                case 4:
                    System.out.println("\nFinalizando o aplicativo...");
                    System.out.println("Agradecemos por usar nossos serviços bancários!");
                    break;
                    
                default:
                    System.out.println("\nComando inválido! Selecione uma das opções disponíveis.");
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