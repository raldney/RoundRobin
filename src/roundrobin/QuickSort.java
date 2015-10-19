
package roundrobin;

import java.util.List;

/**
 *
 * @author raldney
 */
public class QuickSort {

    public static void quickSort(List<Processo> vetor, int menorValor, int maiorValor, boolean chegada) {
        if (!isValido(vetor, menorValor, maiorValor)) {
            return;
        }

        int meio = menorValor + (maiorValor - menorValor) / 2;
        int pivo = (chegada) ? vetor.get(meio).getChegada() : vetor.get(meio).getDuracao();

        int i = menorValor, j = maiorValor;
        while (i <= j) {
            if (chegada) {
                while (vetor.get(i).getChegada() < pivo) {
                    i++;
                }

                while (vetor.get(j).getChegada() > pivo) {
                    j--;
                }
            } else {

                while (vetor.get(i).getDuracao() < pivo) {
                    i++;
                }

                while (vetor.get(j).getDuracao() > pivo) {
                    j--;
                }
            }

            if (i <= j) {
                trocar(vetor, i, j);
                i++;
                j--;
            }
        }

        if (menorValor < j) {
            quickSort(vetor, menorValor, j, chegada);
        }

        if (maiorValor > i) {
            quickSort(vetor, i, maiorValor, chegada);
        }
    }

    public static void trocar(List<Processo> vetor, int i, int j) {
        Processo temp = vetor.get(i);
        vetor.set(i, vetor.get(j));
        vetor.set(j, temp);
    }

    public static boolean isValido(List<Processo> vetor, int menorValor, int maiorValor) {
        if (vetor.isEmpty()) {
            return false;
        }
        if (menorValor >= maiorValor) {
            return false;
        }
        return true;
    }
}
