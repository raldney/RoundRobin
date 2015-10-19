/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roundrobin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author raldney
 */
public class RoundRobin {

    static final boolean CHEGADA = true;
    static final int PROCESSOS = 500, QUANTUM = 50;
    static List<Processo> filaProcessos;

    public static void main(String[] args) {

        filaProcessos = new ArrayList<Processo>();
        Processador cpu = new Processador(QUANTUM);

        Random rand = new Random();

        for (int i = 0; i <= PROCESSOS - 1; i++) {
            filaProcessos.add(new Processo("P" + i, rand.nextInt(2 * (PROCESSOS)) + 4, rand.nextInt(2 * (PROCESSOS)) + 4));
        }
        QuickSort.quickSort(filaProcessos, 0, PROCESSOS - 1, CHEGADA);
        imprimirProcessos();
        int tempo = 0;
        do {
            System.out.print("Tempo: " + tempo);

            if (!filaProcessos.isEmpty()) {
                while (!filaProcessos.isEmpty() && filaProcessos.get(0).getChegada() == tempo) {
                    cpu.adicionarProcesso(filaProcessos.get(0));
                    filaProcessos.remove(0);
                }
            }
            if (cpu.isIniciado()) {
                cpu.imprimirFila();
                cpu.processar();
            }

            System.out.println("\n------------");
            tempo++;

        } while (!cpu.processosFinalizados());

    }

    public static void imprimirProcessos() {
        for (int i = 0; i <= (PROCESSOS - 1); i++) {
            System.out.println("O processo " + filaProcessos.get(i).getNome() + " tem a duração de " + filaProcessos.get(i).getDuracao() + " ms e chegada em " + filaProcessos.get(i).getChegada() + " ms I/O em " + filaProcessos.get(i).imprimirIo());
        }
    }

}
