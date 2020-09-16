package br.com.zup.bootcamp.proposta.service.v1

import br.com.zup.bootcamp.proposta.entity.ProposalEntity
import br.com.zup.bootcamp.proposta.model.v1.request.CreateProposalRequest
import br.com.zup.bootcamp.proposta.repository.ProposalRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ProposalServiceImpl(
    private val proposalRepository: ProposalRepository
) : ProposalService {
    override fun createProposal(createProposalRequest: CreateProposalRequest) {

        val proposal = ProposalEntity(
            UUID.randomUUID().toString(),
            createProposalRequest.name!!,
            createProposalRequest.email!!,
            createProposalRequest.address!!,
            createProposalRequest.document!!.type!!,
            createProposalRequest.document.number!!,
            createProposalRequest.salary!!
        )
        proposalRepository.save(proposal)
    }
}