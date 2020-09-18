package br.com.zup.bootcamp.proposta.repository

import br.com.zup.bootcamp.proposta.repository.request.FinancialAnalysisRequest
import br.com.zup.bootcamp.proposta.repository.response.FinancialAnalysisResponse
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient

@Service
class FinancialAnalysisRepositoryImpl : FinancialAnalysisRepository {

    override fun analyseClientFinancialInfomation(
        documentNumber: String,
        name: String,
        proposalId: String
    ): FinancialAnalysisResponse? {

        val fromValue = BodyInserters.fromValue(FinancialAnalysisRequest(documentNumber, name, proposalId))

        return WebClient
            .create("http://localhost:9999")
            .post()
            .uri("/api/solicitacao")
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
            .body(fromValue)
            .retrieve().bodyToMono(FinancialAnalysisResponse::class.java).block()
    }
}