package com.example.demo.DTO;

public class VoluntarioInputDTO {
    private String passaporte;
    private String cpf;
    private String nome;
    private String sobrenome;
    private String data_nascimento;
    private String telefone;
    private String email;
    private String tipo_sanguineo;
    private String profissao;
    private int anos_experiencia;
    private String situacao_saude;
    private String estado;
//    private String estadoCivil;

    public VoluntarioInputDTO() {}


    public VoluntarioInputDTO(String passaporte, String cpf, String nome, String sobrenome, String data_nascimento, String telefone, String email, String tipo_sanguineo, String profissao, int anos_experiencia, String situacao_saude, String estado) {
        this.passaporte = passaporte;
        this.cpf = cpf;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.data_nascimento = data_nascimento;
        this.telefone = telefone;
        this.email = email;
        this.tipo_sanguineo = tipo_sanguineo;
        this.profissao = profissao;
        this.anos_experiencia = anos_experiencia;
        this.situacao_saude = situacao_saude;
        this.estado = estado;
//        this.estadoCivil = estadoCivil;
    }

//    public String getEstadoCivil() {
//        return estadoCivil;
//    }
//
//    public void setEstadoCivil(String estadoCivil) {
//        this.estadoCivil = estadoCivil;
//    }

    public String getPassaporte() {
        return passaporte;
    }

    public void setPassaporte(String passaporte) {
        this.passaporte = passaporte;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(String data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipo_sanguineo() {
        return tipo_sanguineo;
    }

    public void setTipo_sanguineo(String tipo_sanguineo) {
        this.tipo_sanguineo = tipo_sanguineo;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public int getAnos_experiencia() {
        return anos_experiencia;
    }

    public void setAnos_experiencia(int anos_experiencia) {
        this.anos_experiencia = anos_experiencia;
    }

    public String getSituacao_saude() {
        return situacao_saude;
    }

    public void setSituacao_saude(String situacao_saude) {
        this.situacao_saude = situacao_saude;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
