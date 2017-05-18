package br.com.fourdev.orderfood.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.util.StringUtils;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@NotBlank(message= "O Nome é obrigatório!")
	private String nome;

	@NotBlank(message= "A Descrição é obrigatória!")
	@Size(max=50, min=5, message="A descrição deve estar de 5 a 80 caracteres!")
	private String descricao;
	
	@NotNull(message="A Unidade é obrigatório!")
	@Enumerated(EnumType.STRING)
	private Unidade unidade;

	private String foto;
	
	@Column(name="contenttype")
	private String contentType;

	@NotNull(message="O volume é obrigatório!")
	@NumberFormat(pattern="0.0##")
	private Double volume;
	
	private Boolean ativo;

	@NotNull(message="O valor é obrigatório!")
	@DecimalMin(value="0.01")
	@DecimalMax(value="9999.99", message="O valor não pode ultrapassar R$9.999,99 !")
	@NumberFormat(pattern="#,##0.00")
	private BigDecimal valor;

	@NotNull(message="A quantidade é obrigatória!")
	@Max(value = 9999, message="A quantidade em estoque deve ser menor que 9.999 !")
	@NumberFormat(pattern="#,##0")
	private Integer qtestoque;

	@NotNull(message="A Categoria é obrigatória!")
	@Enumerated(EnumType.STRING)
	private Categoria categoria;
	
	@Transient
	private String url;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Unidade getUnidade() {
		return unidade;
	}
	
	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public String getFoto() {
		return foto;
	}
	
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	public String getContentType() {
		return contentType;
	}
	
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Double getVolume() {
		return volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}
	
	public Boolean getAtivo() {
		return ativo;
	}
	
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Integer getQtestoque() {
		return qtestoque;
	}
	
	public void setQtestoque(Integer qtestoque) {
		this.qtestoque = qtestoque;
	}
	

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public boolean temFoto() {
		return !StringUtils.isEmpty(foto);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Produto other = (Produto) obj;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	
	
}