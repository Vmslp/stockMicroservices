package co.in.stockmicroservices.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import co.in.stockmicroservices.dto.StocksExchangeResponse;

@FeignClient(name = "STOCKEXCHANGEMICROSERVICES", url = "http://localhost:8002/stockExchanges")
public interface StocksExchangeClient {

    @GetMapping("/{exchangeId}")
    public ResponseEntity<StocksExchangeResponse> getStockExchangeById(@PathVariable int exchangeId);
}
