/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roundrobin;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author raldney
 */
public class Processador {

    private List<Processo> filaPronto, filaAguardandoIO, filaFinalizada;
    private int quantumInicial;
    private int quantum = 0;
    private boolean iniciouProcessos = false;

    Processador(int quantum) {
        this.quantumInicial = quantum;
        filaPronto = new ArrayList<Processo>();
        filaFinalizada = new ArrayList<Processo>();
    }

    public boolean isIniciado() {
        return iniciouProcessos;
    }

    public void adicionarProcesso(Processo p) {
        if (!iniciouProcessos) {
            iniciouProcessos = true;
        }
        filaPronto.add(p);
        System.out.print(" O processo " + p.getNome() + " foi adicionado a fila");
        ordenarFila();

    }

    public void ordenarFila() {
        QuickSort.quickSort(filaPronto, 0, filaPronto.size() - 1, false);
    }

    public void imprimirFila() {
        verificarQuantum();
        verificarIo();

        int i = 0;
        System.out.print("\nFila: ");
        while (i <= filaPronto.size() - 1) {
            System.out.print(filaPronto.get(i).getNome() + "(" + filaPronto.get(i).getDuracao() + ") ");
            i++;
        }

    }

    public void processar() {
        filaPronto.get(0).processar();
        System.out.println("\nCPU: " + filaPronto.get(0).getNome());
        if (!filaPronto.get(0).isAtivo()) {
            //filaFinalizada.add(filaPronto.get(0));
            filaPronto.remove(0);
        }
        quantum++;

    }

    public void verificarQuantum() {
        if (quantum == quantumInicial) {
            System.out.print("\nFim de Quantum : " + filaPronto.get(0).getNome());
            fimDaFila(filaPronto.get(0));
            quantum = 0;
        }
    }

    public void verificarIo() {
        if (filaPronto.get(0).isIo()) {
            System.out.print("\nOperação de I/O : " + filaPronto.get(0).getNome());
            fimDaFila(filaPronto.get(0));
        }
    }

    public void fimDaFila(Processo p) {
        filaPronto.remove(0);
        filaPronto.add(p);
    }

    public boolean processosFinalizados() {
        if (!iniciouProcessos) {
            return false;
        }
        if (!filaPronto.isEmpty()) {
            return false;
        }
        return true;
    }
}
