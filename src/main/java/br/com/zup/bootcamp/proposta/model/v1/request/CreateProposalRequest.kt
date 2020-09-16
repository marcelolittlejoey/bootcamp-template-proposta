package br.com.zup.bootcamp.proposta.model.v1.request

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.validation.annotation.Validated
import java.math.BigDecimal
import javax.validation.Valid
import javax.validation.constraints.Email

data class CreateProposalRequest(
    @JsonProperty("nome")
    val name: String?,
    val email: String?,
    @JsonProperty("endereco")
    val address: String?,
    @JsonProperty("documento")
    val document: Document?,
    @JsonProperty("salario")
    val salary: BigDecimal?
)

data class Document(
    @JsonProperty("tipo")
    val type: String?,
    @JsonProperty("numero")
    val number: String?
) {
    fun isCpf(): Boolean {
        return "CPF" == type!!.toUpperCase()
    }
}