package br.com.zup.bootcamp.proposta.exception

class InvalidFieldException(val fieldName: String, override val message: String) : Exception()
