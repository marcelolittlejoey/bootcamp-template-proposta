package br.com.zup.bootcamp.proposta.repository.response

import com.fasterxml.jackson.annotation.JsonProperty

data class FinancialAnalysisResponse(
    @JsonProperty("nome")
    val name: String?,
    @JsonProperty("resultadoSolicitacao")
    val analyseResult: String,
    @JsonProperty("documento")
    val documentNumber: String,
    @JsonProperty("idProposta")
    val proposalId: String
)