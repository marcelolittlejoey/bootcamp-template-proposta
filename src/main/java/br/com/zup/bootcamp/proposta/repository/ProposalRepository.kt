package br.com.zup.bootcamp.proposta.repository

import br.com.zup.bootcamp.proposta.entity.ProposalEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProposalRepository : CrudRepository<ProposalEntity, String>