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
public class Processo {

    private String nome;
    private int pid;
    private int duracao, chegada;
    private int processado = 0;
    private StatusProcesso status;
    private List<Integer> io;

    Processo(String nome, int duracao, int chegada) {
        Random rand = new Random();
        setNome(nome);
        setDuracao(duracao);
        setChegada(chegada);
        setPid(rand.nextInt(4));
        setStatus(StatusProcesso.ATIVO);
        gerarIO(rand);

    }

    private void gerarIO(Random r) {
        io = new ArrayList<Integer>();
        int tempo = 0;
        for (int i = 1; i <= r.nextInt(4); i++) {
                tempo = (duracao-1) / i;
            io.add(tempo);
        }
    }

//    private boolean ioValido(int tempo) {
//        if (io.isEmpty()) {
//            return true;
//        }
//        int i = 0;
//        while (i <= io.size() - 1) {
//            if (io.get(i) == tempo) {
//                return false;
//            }
//        }
//        return true;
//    }

    public String getNome() {
        return nome;
    }

    private void setNome(String nome) {
        this.nome = nome;
    }

    public int getPid() {
        return pid;
    }

    private void setPid(int pid) {
        this.pid = pid;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public int getChegada() {
        return chegada;
    }

    private void setChegada(int chegada) {
        this.chegada = chegada;
    }

    public boolean isAtivo() {
        if (status == StatusProcesso.FINALIZADO) {
            return false;
        }
        return true;
    }

    private void setStatus(StatusProcesso status) {
        this.status = status;
    }

    public boolean isIo() {
        if (io.isEmpty()) {
            return false;
        }
        if (io.get(0) != processado) {
            return false;
        }
        return true;
    }

    public String imprimirIo() {
        int i = 0;
        String saida = "";
        while (i <= io.size() - 1) {
            saida = saida + io.get(i) + ",";
            i++;
        }
        return saida;
    }

    private void setIo(List<Integer> io) {
        this.io = io;
    }

    public void processar() {
        this.duracao -= 1;
        this.processado += 1;
        if (duracao == 0) {
            this.status = StatusProcesso.FINALIZADO;
        }
    }

}
