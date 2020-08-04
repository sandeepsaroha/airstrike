package com.airstrike.coinsorter;

import com.airstrike.coinsorter.data.Coins;
import com.airstrike.coinsorter.data.CoinsInput;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class CoinsServiceController {
  @PostMapping(path = {"/api/coinssorter"}, consumes = {"application/json"}, produces = {"application/json"})
  String home(@RequestBody CoinsInput coins) throws JsonGenerationException, JsonMappingException, IOException {
    if (coins == null)
      coins = new CoinsInput(); 
    Double request = Double.valueOf(coins.getCoins().doubleValue() * 100.0D);
    ObjectMapper Obj = new ObjectMapper();
    Coins coinsData = handleRequest(Integer.valueOf(request.intValue()));
    String jsonStr = Obj.writeValueAsString(coinsData);
    return jsonStr;
  }
  
  private Coins handleRequest(Integer coins) {
    int[] availableCoins = { 100, 50, 25, 10, 5, 1 };
    Coins coinsData = new Coins();
    int remainingCoins = coins.intValue();
    while (remainingCoins > 0) {
      for (int i = 0; i < availableCoins.length; i++) {
        int noOfCoins = getCoins(Integer.valueOf(availableCoins[i]), Integer.valueOf(remainingCoins));
        if (noOfCoins > 0 && availableCoins[i] == 100)
          coinsData.setDollar(Integer.valueOf(noOfCoins)); 
        if (noOfCoins > 0 && availableCoins[i] == 50)
          coinsData.setHalfDollar(Integer.valueOf(noOfCoins)); 
        if (noOfCoins > 0 && availableCoins[i] == 25)
          coinsData.setQuarter(Integer.valueOf(noOfCoins)); 
        if (noOfCoins > 0 && availableCoins[i] == 10)
          coinsData.setDime(Integer.valueOf(noOfCoins)); 
        if (noOfCoins > 0 && availableCoins[i] == 5)
          coinsData.setNickel(Integer.valueOf(noOfCoins)); 
        if (noOfCoins > 0 && availableCoins[i] == 1)
          coinsData.setPenny(Integer.valueOf(noOfCoins)); 
        remainingCoins = getCoinsRemaing(Integer.valueOf(availableCoins[i]), Integer.valueOf(remainingCoins));
      } 
    } 
    return coinsData;
  }
  
  int getCoins(Integer coin, Integer request) {
    return request.intValue() / coin.intValue();
  }
  
  int getCoinsRemaing(Integer coin, Integer request) {
    return request.intValue() % coin.intValue();
  }
  
  public static void main(String[] args) {
    SpringApplication.run(CoinsServiceController.class, args);
  }
}
