package com.springboot.microservice.entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Size(min=3, max=3)
@NotNull
public @interface CurrencyTag {

}
