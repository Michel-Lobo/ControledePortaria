package models;

import java.util.Date;

/**
 * Created by Michel Lobo on 08/01/2018.
 */

public class Registro {
    private String idRegistro;
    private String idUsuario;
    private String nomeVisitante;
    private String rgCPF;
    private String numeroCartao;
    private String foto;
    private String dataHoraEntrada;
    private String dataHoraSaida;


    public String getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(String idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeVisitante() {
        return nomeVisitante;
    }

    public void setNomeVisitante(String nomeVisitante) {
        this.nomeVisitante = nomeVisitante;
    }

    public String getRgCPF() {
        return rgCPF;
    }

    public void setRgCPF(String rgCPF) {
        this.rgCPF = rgCPF;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDataHoraEntrada() {
        return dataHoraEntrada;
    }

    public void setDataHoraEntrada(String dataHoraEntrada) {
        this.dataHoraEntrada = dataHoraEntrada;
    }

    public String getDataHoraSaida() {
        return dataHoraSaida;
    }

    public void setDataHoraSaida(String dataHoraSaida) {
        this.dataHoraSaida = dataHoraSaida;
    }
}
