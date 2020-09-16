package br.com.zup.bootcamp.proposta.repository

import br.com.zup.bootcamp.proposta.entity.ProposalEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface ProposalRepository : CrudRepository<ProposalEntity, String> {
    fun findByDocumentNumber(documentNumber: String) : Optional<ProposalEntity>
}

