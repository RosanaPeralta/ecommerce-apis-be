package com.uade.tpo.grupo3.amancay.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "It has already been created. Please try again.")
public class DuplicateException extends Exception {

}
