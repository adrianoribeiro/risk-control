package br.com.riskcontrol.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * Negative credit limit exception.
 * @author Adriano
 *
 */
@ResponseStatus(value=HttpStatus.CONFLICT, reason="Negative credit limit exception")
public class NegativeCreditLimitException extends RuntimeException {

	private static final long serialVersionUID = -6967720480492433626L;

}
