package br.com.zup.bootcamp.proposta.controller

import br.com.zup.bootcamp.proposta.exception.InvalidFieldException
import br.com.zup.bootcamp.proposta.model.v1.FieldErrorMessage
import br.com.zup.bootcamp.proposta.model.v1.InvalidFieldMessage
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionController {

    private val logger = LoggerFactory.getLogger(ExceptionController::class.java)

    @ExceptionHandler(InvalidFieldException::class)
    fun handleInvalidFieldException(e: InvalidFieldException): ResponseEntity<InvalidFieldMessage> {
        val errorMessage = InvalidFieldMessage(
            "invalid_fields",
            "Invalid fields.",
            FieldErrorMessage(e.fieldName, e.message)
        )
        logger.warn("ExceptionController -> handleRequiredFieldException -> ${e.message}", e)
        return ResponseEntity(errorMessage, HttpStatus.PRECONDITION_FAILED)
    }
}
