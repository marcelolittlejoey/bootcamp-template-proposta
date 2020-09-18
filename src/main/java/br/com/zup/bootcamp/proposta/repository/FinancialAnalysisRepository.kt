package br.com.zup.bootcamp.proposta.repository

import br.com.zup.bootcamp.proposta.repository.response.FinancialAnalysisResponse

interface FinancialAnalysisRepository {

   fun analyseClientFinancialInfomation(documentNumber: String, name: String, proposalId: String): FinancialAnalysisResponse?
}