package com.salesianostriana.dam.AdrianCaballeroTorrebejano.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeeSetting { //ESTO SON DATOS POR DEFECTO, EL USUARIO PUEDE CAMBIARLOS DESDE EL FRONT

	private double basePrice=88, siblingDiscount,  earlyRegistrationDiscount;
    private int age = 12, daysBeforeCourseStartsSetByUser = 9;
   
}

