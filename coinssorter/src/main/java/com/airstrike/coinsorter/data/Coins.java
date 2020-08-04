package com.airstrike.coinsorter.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Coins {
  Integer dollar = Integer.valueOf(0);
  
  Integer halfDollar = Integer.valueOf(0);
  
  Integer quarter = Integer.valueOf(0);
  
  Integer dime = Integer.valueOf(0);
  
  Integer nickel = Integer.valueOf(0);
  
  Integer penny = Integer.valueOf(0);
  
  @JsonProperty("silver-dollar")
  public Integer getDollar() {
    return this.dollar;
  }
  
  public void setDollar(Integer dollar) {
    this.dollar = dollar;
  }
  
  @JsonProperty("half-dollar")
  public Integer getHalfDollar() {
    return this.halfDollar;
  }
  
  public void setHalfDollar(Integer halfDollar) {
    this.halfDollar = halfDollar;
  }
  
  public Integer getQuarter() {
    return this.quarter;
  }
  
  public void setQuarter(Integer quarter) {
    this.quarter = quarter;
  }
  
  public Integer getDime() {
    return this.dime;
  }
  
  public void setDime(Integer dime) {
    this.dime = dime;
  }
  
  public Integer getNickel() {
    return this.nickel;
  }
  
  public void setNickel(Integer nickel) {
    this.nickel = nickel;
  }
  
  public Integer getPenny() {
    return this.penny;
  }
  
  public void setPenny(Integer penny) {
    this.penny = penny;
  }
}
