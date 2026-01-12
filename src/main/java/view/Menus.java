package view;

import controller.FuncionarioController;
import model.Funcionario;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menus {

    private static final Scanner sc = new Scanner(System.in);
    private static final FuncionarioController fc = new FuncionarioController();

    public static void exibirMenuPrincipal() {

        while (true) {

            System.out.println("""
                \n
                ======================================================
                =============== SISTEMA DE GESTÃO RH =================
                ======================================================
                ***** Selecione uma operação que deseja realizar *****
                ======================================================""");
            System.out.print("""
                |\s\s         [ 1 ] - CONTRATAR FUNCIONÁRIO           \s\s|
                |\s\s         [ 2 ] - VER LISTA DE FUNCIONÁRIOS       \s\s|
                |\s\s         [ 3 ] - REAJUSTAR SALÁRIO               \s\s|
                |\s\s         [ 4 ] - PROMOVER FUNCIONÁRIO            \s\s|
                |\s\s         [ 5 ] - FOLHA DE PAGAMENTO              \s\s|
                |\s\s         [ 6 ] - DEMITIR FUNCIONÁRIO             \s\s|
                |\s\s         [ 7 ] - SAIR DO PROGRAMA                \s\s|
                
                Selecione um opção:\s""");
            int opcao = lerInteiro();

            switch (opcao) {
                case 1 -> exibirMenuContratacao();
                case 2 -> exibirMenuListarFuncionarios();
                //case 3 -> exibirMenuAtualizarSalario();
                //case 4 -> exibirMenuPromocaoDeCargo();
                case 5 -> exibirMenuFolhaDePagamento();
                case 6 -> exibirMenuDemitirFuncionario();
                case 7 -> {
                    System.out.println("Finalizando o programa. Até logo!");
                    System.exit(0);
                }
                default -> System.out.println("Opção Inválida!");
            }

        }
    }

    private static int repetirOperacao() {
        System.out.println("""
                    REPETIR OPERAÇÃO?
                    
                    [ 1 ] SIM
                    [ 2 ] NÃO
                    
                    Selecione uma opção:\s""");
        return lerInteiro();
    }

    private static int confirmacaoPesquisa() {
        System.out.print("""
                Confirma o resultado da pesquisa?
                
                [ 1 ] SIM
                [ 2 ] NÃO
                
                Selecione uma opção:\s""");

        return lerInteiro();
    }

    private static void exibirMenuContratacao() {

        do {
            System.out.println("""
                \n
                ======================================================
                ============ CONTRATAR NOVO FUNCIONÁRIO ==============
                ======================================================
                *****        Insira os dados necessários         *****
                ======================================================""");

            System.out.print("Nome Comleto: ");
            String nome = lerString();
            System.out.print("Cargo: ");
            String cargo = lerString();
            System.out.print("Salário: ");
            BigDecimal salario = lerBigDecimal();

            Funcionario funcionario = new Funcionario(nome, cargo, salario);

            fc.contratarFuncionario(funcionario);

            int repetir = repetirOperacao();

            switch (repetir) {
                case 1:
                    break;
                case 2:
                    System.out.println("Voltando ao menu principal!");
                    return;
                default:
                    System.out.println("Opção inválida!");
                    repetirOperacao();
            }
        } while (true);
    }

    private static void exibirMenuListarFuncionarios() {

        System.out.println("""
                \n
                ======================================================
                =============== LISTA DE FUNCIONÁRIOS ================
                ======================================================""");

        fc.verListaFuncionarios();
    }

    public static void exibirMenuFolhaDePagamento() {

        System.out.println("""
                \n
                ======================================================
                ============== FOLHA DE PAGAMENTO TOTAL ==============
                ======================================================""");

       // System.out.println("R$ " + fc.getFolhaDePagamento());

    }

    public static void exibirMenuDemitirFuncionario() {

        do {
            System.out.println("""
                \n
                ======================================================
                ================ DEMITIR FUNCIONÁRIO =================
                ======================================================
                *****        Insira os dados necessários         *****
                ======================================================""");

            System.out.print("ID Funcionário: ");
            Long id = lerLong();
            System.out.println(fc.buscarFuncionarioPorId(id));

            int opcaoConfirmacao = confirmacaoPesquisa();

            switch (opcaoConfirmacao) {
                case 1:
                    fc.demitirFuncionario(fc.buscarFuncionarioPorId(id));
                case 2:
                    System.out.println("Operação cancelada! Funcionário incorreto.");
                    return;
                default:
                    System.out.println("Opção inválida");
            }
        } while (true);
    }

    private static int lerInteiro() {

        while(true) {
            try {
                int valor = sc.nextInt();
                sc.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                System.out.print("Erro! Digite um valor número válido: ");
                sc.nextLine();
            }
        }
    }

    private static Long lerLong() {

        while(true) {
            try {
                Long valor = sc.nextLong();
                sc.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                System.out.print("Erro! Digite um valor número válido: ");
                sc.nextLine();
            }
        }
    }

    private static BigDecimal lerBigDecimal() {
        while(true) {
            try {
                BigDecimal valor = sc.nextBigDecimal();
                sc.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                System.out.print("Erro! Digite um valor numérico (Ex: 1500,50): ");
                sc.nextLine();
            }
        }
    }

    private static String lerString() {
        while (true) {
            String entrada = sc.nextLine();
            if (!entrada.trim().isEmpty()) {
                return entrada;
            }
            System.out.print("Erro! O campo não pode ficar vazio. Digite novamente: ");
        }
    }

}