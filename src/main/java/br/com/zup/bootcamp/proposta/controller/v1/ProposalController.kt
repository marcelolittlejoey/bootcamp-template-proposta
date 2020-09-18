package br.com.zup.bootcamp.proposta.controller.v1

import br.com.caelum.stella.validation.CNPJValidator
import br.com.caelum.stella.validation.CPFValidator
import br.com.caelum.stella.validation.InvalidStateException
import br.com.zup.bootcamp.proposta.exception.InvalidFieldException
import br.com.zup.bootcamp.proposta.model.v1.request.CreateProposalRequest
import br.com.zup.bootcamp.proposta.model.v1.request.Document
import br.com.zup.bootcamp.proposta.service.v1.ProposalService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ProposalController(private val proposalService: ProposalService) {

    private val logger=LoggerFactory.getLogger(ProposalController::class.java)

    @PostMapping("proposta/v1/propostas")
    fun createProposta(
        @RequestBody createProposalRequest: CreateProposalRequest
    ): ResponseEntity<Void> {
        logger.info("ProposalController-> creating proposal")
        validateRequest(createProposalRequest)
        proposalService.createProposal(createProposalRequest)
        return ResponseEntity(HttpStatus.CREATED)
    }

    private fun validateRequest(createProposalRequest: CreateProposalRequest) {
        validateName(createProposalRequest)
        validateEmail(createProposalRequest)
        validateSalary(createProposalRequest)
        validateAddress(createProposalRequest)
        validateDocument(createProposalRequest.document)
    }

    private fun validateDocument(document: Document?) {
        document ?: throw InvalidFieldException(
            "documento", "Documento é obrigatório."
        )

        if (document.number.isNullOrBlank()) {
            throw InvalidFieldException(
                "documento.numero", "Número do documento é obrigatório."
            )
        }

        if (document.type.isNullOrBlank()) {
            throw InvalidFieldException(
                "documento.tipo", "Tipo do documento é obrigatório."
            )
        }
        validateDocumentNumber(document)
    }

    private fun validateDocumentNumber(document: Document) {
        try {
            if (document.isCpf()) {
                val validator = CPFValidator()
                validator.assertValid(document.number)
            } else {
                val validator = CNPJValidator()
                validator.assertValid(document.number)
            }
        }
        catch (ex: InvalidStateException) {
            throw InvalidFieldException(
                "documento.numero", "Número do documento inválido."
            )
        }

    }

    private fun validateAddress(createProposalRequest: CreateProposalRequest) {
        if (createProposalRequest.address.isNullOrBlank()) {
            throw InvalidFieldException(
                "Endereço", "Endereço é obrigatório."
            )
        }
    }

    private fun validateSalary(createProposalRequest: CreateProposalRequest) {
        createProposalRequest.salary ?: throw InvalidFieldException(
            "salario", "Salario é obrigatório."
        )
    }

    private fun validateEmail(createProposalRequest: CreateProposalRequest) {
        if (createProposalRequest.email.isNullOrBlank()) {
            throw InvalidFieldException(
                "email", "Email é obrigatório."
            )
        }
    }

    private fun validateName(createProposalRequest: CreateProposalRequest) {
        if (createProposalRequest.name.isNullOrBlank()) {
            throw InvalidFieldException(
                "nome", "Nome é obrigatório."
            )
        }
    }
}