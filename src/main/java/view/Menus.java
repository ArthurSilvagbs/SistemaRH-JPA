package view;

import controller.FuncionarioController;
import model.Funcionario;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
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
                |\s\s         [ 3 ] - ATUALIZAR DADOS DO FUNCIONÁRIO  \s\s|
                |\s\s         [ 4 ] - FOLHA DE PAGAMENTO              \s\s|
                |\s\s         [ 5 ] - DEMITIR FUNCIONÁRIO             \s\s|
                |\s\s         [ 6 ] - SAIR DO PROGRAMA                \s\s|
                
                Selecione um opção:\s""");
            int opcao = lerInteiro();

            switch (opcao) {
                case 1 -> exibirMenuContratacao();
                case 2 -> exibirMenuListarFuncionarios();
                case 3 -> exibirMenuAtualizarDados();
                case 4 -> exibirMenuFolhaDePagamento();
                case 5 -> exibirMenuDemitirFuncionario();
                case 6 -> {
                    System.out.println("Finalizando o programa. Até logo!");
                    System.exit(0);
                }
                default -> System.out.println("Opção Inválida!");
            }

        }
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

        List<Funcionario> lista = fc.verListaFuncionarios();

        if (lista.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
        } else {
            for (Funcionario f : lista) {
                System.out.println(f);
            }
        }
    }

    public static void exibirMenuAtualizarDados() {

        boolean continuar = true;

        do {
            System.out.println("""
                \n
                ======================================================
                ========== ATUALIZAR DADOS DO FUNCIONÁRIO ============
                ======================================================
                *****        Insira os dados necessários         *****
                ======================================================""");

            System.out.print("ID Funcionário: ");
            Long id = lerLong();
            System.out.println(fc.buscarFuncionarioPorId(id));

            int opcaoPesquisa = confirmacaoPesquisa();

            switch (opcaoPesquisa) {
                case 1:

                    Funcionario funcionarioAtt = fc.buscarFuncionarioPorId(id);

                    System.out.print("""
                ======================================================
                *****            Selecione uma opção             *****
                ======================================================
                
                [ 1 ] ATUALIZAR NOME
                [ 2 ] ATUALIZAR CARGO
                [ 3 ] ATUALIZAR SALÁRIO
                
                Selecione uma opção:\s""");

                    int opcao = lerInteiro();

                    switch (opcao) {
                        case 1:

                            String nomeAntigo = funcionarioAtt.getNome();

                            try {
                                System.out.print("Novo nome: ");
                                String nomeNovo = sc.nextLine();
                                funcionarioAtt.setNome(nomeNovo);

                                if (!Objects.equals(nomeAntigo, nomeNovo)) {
                                    fc.atualizarDadosFuncioanrio(funcionarioAtt);
                                    System.out.println("Nome atualizado com sucesso!");
                                    System.out.printf("""
                                            Novos dados do funcionário:
                                            %s""", funcionarioAtt);
                                }
                            } catch (Exception e) {
                                throw new RuntimeException("Erro inesperado" + e);
                            }
                            break;

                        case 2:

                            String cargoAntigo = funcionarioAtt.getCargo();

                            try {
                                System.out.print("Novo cargo: ");
                                String cargoNovo = sc.nextLine();
                                funcionarioAtt.setCargo(cargoNovo);

                                if (!Objects.equals(cargoAntigo, cargoNovo)) {
                                    fc.atualizarDadosFuncioanrio(funcionarioAtt);
                                    System.out.println("Cargo atualizado com sucesso!");
                                    System.out.printf("""
                                            Novos dados do funcionário:
                                            %s""", funcionarioAtt);
                                }
                            } catch (Exception e) {
                                throw new RuntimeException("Erro inesperado" + e);
                            }
                            break;

                        case 3:
                            BigDecimal salarioAntigo = funcionarioAtt.getSalario();

                            try {
                                System.out.print("Novo salário: ");
                                BigDecimal salarioNovo = sc.nextBigDecimal();
                                funcionarioAtt.setSalario(salarioNovo);

                                if (!Objects.equals(salarioAntigo, salarioNovo)) {
                                    fc.atualizarDadosFuncioanrio(funcionarioAtt);
                                    System.out.println("Salárop atualizado com sucesso!");
                                    System.out.printf("""
                                            Novos dados do funcionário:
                                            %s""", funcionarioAtt);
                                }
                            } catch (Exception e) {
                                throw new RuntimeException("Erro inesperado" + e);
                            }
                            break;

                        default:
                            System.out.println("Opção inválida!");

                    }
                    continuar = false;
                    break;

                case 2:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

        } while (continuar);

    }

    public static void exibirMenuFolhaDePagamento() {

        System.out.println("""
                \n
                ======================================================
                ============== FOLHA DE PAGAMENTO TOTAL ==============
                ======================================================""");

       BigDecimal somaSalarios = fc.obterSomatorioSalarios();

        System.out.printf("|| FOLHA DE PAGAMENTO: R$ %.2f ||", somaSalarios);

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
                    break;
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

}