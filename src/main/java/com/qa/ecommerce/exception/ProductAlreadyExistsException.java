package com.qa.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Product Already exists with this id ")
public class ProductAlreadyExistsException extends Exception{

}
