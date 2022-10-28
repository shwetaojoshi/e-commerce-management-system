package com.qa.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Order Already exists with this id ")
public class OrderAlreadyExistsException extends Exception {

}
