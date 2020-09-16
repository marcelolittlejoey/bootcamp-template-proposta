package br.com.zup.bootcamp.proposta.exception

class ProposalAlreadyExistException(val type: String, override val message: String) : Exception()
