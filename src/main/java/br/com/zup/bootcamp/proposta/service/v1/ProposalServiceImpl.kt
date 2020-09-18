package br.com.zup.bootcamp.proposta.service.v1

import br.com.zup.bootcamp.proposta.entity.ProposalEntity
import br.com.zup.bootcamp.proposta.exception.ProposalAlreadyExistException
import br.com.zup.bootcamp.proposta.model.v1.request.CreateProposalRequest
import br.com.zup.bootcamp.proposta.repository.FinancialAnalysisRepository
import br.com.zup.bootcamp.proposta.repository.ProposalRepository
import br.com.zup.bootcamp.proposta.repository.response.FinancialAnalysisResponse
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ProposalServiceImpl(
    private val proposalRepository: ProposalRepository,
    private val financialAnalysisRepository: FinancialAnalysisRepository
) : ProposalService {
    override fun createProposal(createProposalRequest: CreateProposalRequest) {

        verifyIfProposalAlreadyExist(createProposalRequest)
        val proposalEntity = mountProposalEntity(createProposalRequest)
        proposalRepository.save(proposalEntity)

        val analyseResultResponse = analyseDocument(proposalEntity)

        proposalEntity.status = analyseResultResponse!!.analyseResult
        proposalRepository.save(proposalEntity)
    }

    private fun verifyIfProposalAlreadyExist(createProposalRequest: CreateProposalRequest) {
        if (proposalRepository.findByDocumentNumber(createProposalRequest.document!!.number!!).isPresent) {
            throw ProposalAlreadyExistException(
                "proposta_ja_enviada", "Ja existe uma proposta com esse documento."
            )
        }
    }

    private fun analyseDocument(proposalEntity: ProposalEntity): FinancialAnalysisResponse? {
        return financialAnalysisRepository.analyseClientFinancialInfomation(
            documentNumber = proposalEntity.documentNumber,
            name = proposalEntity.name,
            proposalId = proposalEntity.id)
    }

    private fun mountProposalEntity(createProposalRequest: CreateProposalRequest): ProposalEntity {
        return ProposalEntity(UUID.randomUUID().toString(), createProposalRequest.name!!, createProposalRequest.email!!, createProposalRequest.address!!, createProposalRequest.document!!.type!!, createProposalRequest.document.number!!, createProposalRequest.salary!!)
    }
}