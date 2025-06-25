import java.util.Random;
import java.util.Scanner;

public class batalhaNaval_Final {

    public static void tabuleiro(char[][] tabuleiroBarco, char[][] tabuleiroOculto) {// gerando e copulando os tabuleiros
        for (int i = 0; i < tabuleiroBarco.length; i++) {
            for (int j = 0; j < tabuleiroBarco[i].length; j++) {
                tabuleiroBarco[i][j] = '~';
                tabuleiroOculto[i][j] = '~';
            }
        }
    }

    public static void barcos(char[][] tabuleiroBarco, int qtdBarcos) {//gerando os barcos de forma aleatoria
        Random r = new Random();
        int cont = 0;
        while (cont < qtdBarcos) {
            int linha = r.nextInt(tabuleiroBarco.length);
            int coluna = r.nextInt(tabuleiroBarco.length);

            if (tabuleiroBarco[linha][coluna] == '~') {
                tabuleiroBarco[linha][coluna] = 'N'; // gerando ate (qtdBarcos) N
                cont++;
            }
        }
    }

    public static void imprimirOculto(char[][] tabuleiroOculto) {//imprimindo (tabuleiro sem os barcos)
        System.out.println("\nTabuleiro (oculto):");
        imprimirTabuleiro(tabuleiroOculto);
    }

    public static void imprimirTabuleiro(char[][] troca/*troca esta matriz por outra quando gerado*/) {// organizando e imprimindo tabuleiro
        // organizando as colunas e linhas
        System.out.print("  ");
        for (int i = 0; i < troca.length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < troca.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < troca[i].length; j++) {
                System.out.print(troca[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean tiro(char[][] tabuleiroBarco, char[][] tabuleiroOculto, Scanner input) {// funcao de coletar os lugares em que atirei
        System.out.println("\n*-------------*");
        System.out.println("*--- Atire ---*");
        System.out.println("*-------------*\n");

        int linha, coluna;

        while (true) {
            System.out.print("Linha: ");
            linha = input.nextInt();
            System.out.print("Coluna: ");
            coluna = input.nextInt();

            if ((linha >= 0) && (linha < tabuleiroBarco.length)) { //caso digite uma linha fora da matriz
                if ((coluna >= 0) && (coluna < tabuleiroBarco.length)) { //caso digite uma coluna fora da matriz
                    if (tabuleiroOculto[linha][coluna] == '~') { // caso dentro da matriz esteja '~' não foi selecionado este espaço ainda
                        if (tabuleiroBarco[linha][coluna] == 'N') { // caso esteja 'N' acertou
                            System.out.println("\nAcertou!");
                            tabuleiroOculto[linha][coluna] = 'X';
                            return true; // retorna que o local esta correto
                        } else {
                            System.out.println("\nAgua...");
                            tabuleiroOculto[linha][coluna] = 'O';
                            return false;// retorna que o local esta errado
                        }
                    } else {
                        System.out.println("\nVoce ja atirou nessa posicao!");
                    }
                } else {
                    System.out.println("\nPosicao invalida!");
                }
            } else {
                System.out.println("\nPosicao invalida!");
            }
        }
    }

    public static void game(int tamanho, int qtdBarcosInicial, int tentativas, Scanner input) {// rodando o jogo inteiro
        char[][] tabuleiroBarco = new char[tamanho][tamanho]; // criando a matriz do tabuleiro que possui o barco
        char[][] tabuleiroOculto = new char[tamanho][tamanho]; //criando a matriz do tabuleiro onde nao irá aprecer o barco

        tabuleiro(tabuleiroBarco, tabuleiroOculto); //gerando ambos os tabuleiros
        barcos(tabuleiroBarco, qtdBarcosInicial); // gerando os barcos

        int barcosRestantes = qtdBarcosInicial;

        while (barcosRestantes > 0 && tentativas > 0) {
            imprimirOculto(tabuleiroOculto);

            System.out.println("\nTentativas restantes: " + tentativas);// qtd de tentativas restantes
            System.out.println("Barcos restantes: " + barcosRestantes);// qtd de barocs restantes

            boolean acertou = tiro(tabuleiroBarco, tabuleiroOculto, input);

            if (acertou) {//caso o tiro sera TRUE
                barcosRestantes--;
            } // else nao necesario para dar continuidade

            tentativas--;

            if (barcosRestantes == 0) {
                break;
            }
            if (tentativas == 0) {
                break;
            }
        }

        if (barcosRestantes == 0) {
            imprimirOculto(tabuleiroOculto);
            System.out.println("\n====================");
            System.out.println("==  Voce Venceu!  ==");
            System.out.println("====================\n");
        } else {
            imprimirOculto(tabuleiroOculto);
            System.out.println("\n====================");
            System.out.println("==  Voce Perdeu!  ==");
            System.out.println("====================\n");
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("~~~ BATALHA NAVAL ~~~");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");

        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~\n");
        System.out.println("Regras:\n\n1- Para jogar primeiro digite a LINHA e logo apos a COLUNA desejada para atirar.\n2- Existem 3 barcos no tabuleiro.\n3- O jogador possui 10 chances de acertar os barcos.\n4- DERROTA caso as tentativas cheguem ao fim.\n5- VITORIA caso acerte todos os barcos antes de terminarem as tentativas.");
        System.out.println("\nBOA SORTE!!!");
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~\n");

        int continuar = 0;

        do {
            int tamanho = 5; // declarando o tamanho das matrizes
            int qtdBarcos = 3;// qtd de barcos do jogo
            int tentativas = tamanho * 2; // quantidade de chances

            game(tamanho, qtdBarcos, tentativas, input);

            continuar = 0;
            while (continuar != 1) {
                System.out.println("\nDeseja jogar novamente? ");
                System.out.println("1 - Sim");
                System.out.println("2 - Nao\n");

                while (!input.hasNextInt()) {// não permite a entrada de nao inteiros
                    System.out.println("Resposta invalida! Digite 1 ou 2:");
                    System.out.println("\nDeseja jogar novamente?");
                    System.out.println("1 - Sim");
                    System.out.println("2 - Nao\n");
                    input.next();
                }

                continuar = input.nextInt();// coleta o prozimo numero inteiro

                if (continuar == 2) {
                    continuar = 2;
                    break;// finaliza o codigo caso deseje parar
                } else if (continuar == 1) {
                    System.out.println("Reiniciando jogo...");
                    continuar = 1;
                } else {
                    System.out.println("Resposta invalida! Digite 1 ou 2.");
                }
            }

        } while (continuar == 1);

        System.out.println("\nJogo encerrado! Obrigado por participar.");// mensagem final
    }
}
