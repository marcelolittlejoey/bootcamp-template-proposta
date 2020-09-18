package br.com.zup.bootcamp.proposta.repository.request

import com.fasterxml.jackson.annotation.JsonProperty

data class FinancialAnalysisRequest(
    @JsonProperty("nome")
    val name: String?,
    @JsonProperty("documento")
    val documentNumber: String,
    @JsonProperty("idProposta")
    val proposalId: String
)