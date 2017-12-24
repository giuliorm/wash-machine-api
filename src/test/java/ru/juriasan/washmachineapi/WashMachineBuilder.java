package ru.juriasan.washmachineapi;

import ru.juriasan.washmachineapi.domain.WashMode;
import ru.juriasan.washmachineapi.domain.WashState;

public class WashMachineBuilder {

  private String modelName;
  private boolean isTurnedOn;
  private WashState state;
  private WashMode mode;

  public WashMachineBuilder withModelName(String modelName) {
    this.modelName = modelName;
    return this;
  }

  public WashMachineBuilder withState(WashState state) {
    this.state = state;
    return this;
  }

  public InvoiceBuilder withDiscount(PoundsShillingsPence discount) {
    this.discount = discount;
    return this;
  }

  public Invoice build() {
    return new Invoice(recipient, lines, discount);
  }
}
