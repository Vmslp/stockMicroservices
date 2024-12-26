package co.in.stockmicroservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.in.stockmicroservices.dto.StocksResponse;
import co.in.stockmicroservices.entity.Stocks;
import co.in.stockmicroservices.service.IStocksService;

@RestController
@RequestMapping("${api.stocks.base-path}")
@CrossOrigin("*")
public class StocksController {

	@Autowired
	private IStocksService stockService;

	@PostMapping
	public ResponseEntity<Stocks> createStock(@RequestBody Stocks stock) {
		Stocks savedStock = stockService.createStock(stock);
		return new ResponseEntity<>(savedStock, HttpStatus.CREATED);
	}

	@GetMapping("${api.stocks.getall-path}")
	public ResponseEntity<List<Stocks>> getAllStocks() {
		List<Stocks> stocks = stockService.getAllStocks();
		return new ResponseEntity<>(stocks, HttpStatus.OK);
	}

	@GetMapping("${api.stocks.getById-path}")
	public ResponseEntity<Stocks> getStockById(@PathVariable int stockId) {
		Stocks stock = stockService.getStockById(stockId);
		return new ResponseEntity<>(stock, HttpStatus.OK);
	}

	@PutMapping("${api.stocks.update-path}")
	public ResponseEntity<Stocks> updateStock(@PathVariable int stockId, @RequestBody Stocks stockDetails) {
		Stocks updatedStockObj = stockService.updateStock(stockId, stockDetails);
		return new ResponseEntity<>(updatedStockObj, HttpStatus.OK);
	}

	@DeleteMapping("${api.stocks.delete-path}")
	public ResponseEntity<Void> deleteStockById(@PathVariable int stockId) {
		stockService.deleteStockById(stockId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("${api.stocks.client-path}")
	public ResponseEntity<StocksResponse> getStocksWithExchange(@PathVariable int stockId,
			@PathVariable int exchangeId) {
		StocksResponse response = stockService.getStocksExchange(stockId, exchangeId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
