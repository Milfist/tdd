package com.milfist.calculator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CalculatorResult {
  private final String operation;
  private final String result;
}
