package br.com.zup.bootcamp.proposta.model.v1

data class InvalidFieldMessage(val type: String, val message: String, val data: FieldErrorMessage)

data class FieldErrorMessage(val field: String, val error: String?)