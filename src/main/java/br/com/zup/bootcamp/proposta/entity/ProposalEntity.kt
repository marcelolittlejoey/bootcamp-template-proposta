package br.com.zup.bootcamp.proposta.entity

import java.math.BigDecimal
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "Proposal")
class ProposalEntity(
    @Id
    val id: String,
    val name: String,
    val email: String,
    val address: String,
    val documentType: String,
    val documentNumber: String,
    val salary: BigDecimal,
    var status: String? = null
)

