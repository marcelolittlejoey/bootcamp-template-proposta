package br.com.zup.bootcamp.proposta.service.v1

import br.com.zup.bootcamp.proposta.model.v1.request.CreateProposalRequest
import org.springframework.stereotype.Service

@Service
interface ProposalService {

    fun createProposal(createProposalRequest: CreateProposalRequest)
}