package br.com.arturtcs.primeiroprojetojsf.entidades;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nome;

	@NotEmpty(message = "Sobrenome deve ser informado.")
	@NotNull(message = "Sobrenome deve ser informado.")
	private String sobrenome;

	private Integer idade;

	@Temporal(TemporalType.DATE)
	private Date dataNascimento = new Date();

	private String sexo;

	private String[] frameworks;

	private Boolean ativo;

	private String login;

	private String senha;

	private String perfilUser;

	private String nivelProgramador;

	private Integer[] linguagens;

	private String cpf;

	private String tituloEleitor;

	private String cep;

	private String logradouro;

	private String complemento;

	private String bairro;

	private String localidade;

	private String uf;

	private String unidade;

	private String ibge;

	private String gia;

	@Transient // nao persiste no banco. Apenas para ajudar a programar
	private Estados estados;

	@ManyToOne
	private Cidades cidades;
	
	//Tipo text grava arquivos em base 64
	@Column(columnDefinition = "text")
	private String fotoIconBase64;
	
	//extensao jpg, png, jpeg
	private String extensao;
	
	//Gravar arquivos no banco de dados
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] fotoIconBase64Original;

	public Pessoa() {
	}

	public Pessoa(Long id, String nome, String sobrenome, Integer idade, Date dataNascimento, String sexo,
			String[] frameworks, Boolean ativo, String login, String senha, String perfilUser, String nivelProgramador,
			Integer[] linguagens, String cpf, String tituloEleitor, String cep, String logradouro, String complemento,
			String bairro, String localidade, String uf, String unidade, String ibge, String gia) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.idade = idade;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.frameworks = frameworks;
		this.ativo = ativo;
		this.login = login;
		this.senha = senha;
		this.perfilUser = perfilUser;
		this.nivelProgramador = nivelProgramador;
		this.linguagens = linguagens;
		this.cpf = cpf;
		this.tituloEleitor = tituloEleitor;
		this.cep = cep;
		this.logradouro = logradouro;
		this.complemento = complemento;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
		this.unidade = unidade;
		this.ibge = ibge;
		this.gia = gia;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String[] getFrameworks() {
		return frameworks;
	}

	public void setFrameworks(String[] frameworks) {
		this.frameworks = frameworks;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getPerfilUser() {
		return perfilUser;
	}

	public void setPerfilUser(String perfilUser) {
		this.perfilUser = perfilUser;
	}

	public String getNivelProgramador() {
		return nivelProgramador;
	}

	public void setNivelProgramador(String nivelProgramador) {
		this.nivelProgramador = nivelProgramador;
	}

	public Integer[] getLinguagens() {
		return linguagens;
	}

	public void setLinguagens(Integer[] linguagens) {
		this.linguagens = linguagens;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTituloEleitor() {
		return tituloEleitor;
	}

	public void setTituloEleitor(String tituloEleitor) {
		this.tituloEleitor = tituloEleitor;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public String getIbge() {
		return ibge;
	}

	public void setIbge(String ibge) {
		this.ibge = ibge;
	}

	public String getGia() {
		return gia;
	}

	public void setGia(String gia) {
		this.gia = gia;
	}

	public Estados getEstados() {
		return estados;
	}

	public void setEstados(Estados estados) {
		this.estados = estados;
	}

	public Cidades getCidades() {
		return cidades;
	}

	public void setCidades(Cidades cidades) {
		this.cidades = cidades;
	}
	
	public String getFotoIconBase64() {
		return fotoIconBase64;
	}

	public void setFotoIconBase64(String fotoIconBase64) {
		this.fotoIconBase64 = fotoIconBase64;
	}

	public String getExtensao() {
		return extensao;
	}

	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}

	public byte[] getFotoIconBase64Original() {
		return fotoIconBase64Original;
	}

	public void setFotoIconBase64Original(byte[] fotoIconBase64Original) {
		this.fotoIconBase64Original = fotoIconBase64Original;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nome=" + nome + ", sobrenome=" + sobrenome + ", idade=" + idade
				+ ", dataNascimento=" + dataNascimento + ", sexo=" + sexo + ", frameworks="
				+ Arrays.toString(frameworks) + ", ativo=" + ativo + ", login=" + login + ", senha=" + senha
				+ ", perfilUser=" + perfilUser + ", nivelProgramador=" + nivelProgramador + ", linguagens="
				+ Arrays.toString(linguagens) + ", cpf=" + cpf + ", tituloEleitor=" + tituloEleitor + ", cep=" + cep
				+ ", logradouro=" + logradouro + ", complemento=" + complemento + ", bairro=" + bairro + ", localidade="
				+ localidade + ", uf=" + uf + ", unidade=" + unidade + ", ibge=" + ibge + ", gia=" + gia + "]";
	}

}
