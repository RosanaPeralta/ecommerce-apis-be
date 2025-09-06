package com.uade.tpo.grupo3.amancay.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "The entered data is ivalid. Please try again.")
public class InvalidParameters extends Exception {

}
