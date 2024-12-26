package co.in.stockmicroservices.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.in.stockmicroservices.dto.StocksResponse;
import co.in.stockmicroservices.entity.Stocks;
import co.in.stockmicroservices.exception.StockNotFoundException;
import co.in.stockmicroservices.repository.StocksRepository;

@Service
public class StocksServiceImpl implements IStocksService {

	@Autowired
	private StocksRepository stockRepository;

	@Autowired
	private StocksExchangeClient stocksExchangeClient;

	@Override
	public Stocks createStock(Stocks stock) {
		return stockRepository.save(stock);
	}

	@Override
	public List<Stocks> getAllStocks() {
		return stockRepository.findAll();
	}

	@Override
	public Stocks getStockById(int stockId) {
		return stockRepository.findById(stockId)
				.orElseThrow(() -> new StockNotFoundException("Stock with ID " + stockId + " not found"));
	}

	@Override
	public Stocks updateStock(int stockId, Stocks stockDetails) {
		Stocks existingStock = stockRepository.findById(stockId)
				.orElseThrow(() -> new StockNotFoundException("Stock with ID " + stockId + " not found"));
		existingStock.setCompanyName(stockDetails.getCompanyName());
		existingStock.setCompanyPrice(stockDetails.getCompanyPrice());
		return stockRepository.save(existingStock);
	}

	@Override
	public void deleteStockById(int stockId) {
		Stocks stock = stockRepository.findById(stockId)
				.orElseThrow(() -> new StockNotFoundException("Stock with ID " + stockId + " not found"));
		stockRepository.delete(stock);
	}

	@Override
	public StocksResponse getStocksExchange(int stockId, int exchangeId) {
		Stocks stock = stockRepository.findById(stockId)
				.orElseThrow(() -> new StockNotFoundException("Stock with ID " + stockId + " not found"));

		StocksResponse response = new StocksResponse();
		response.setStockId(stock.getStockId());
		response.setCompanyName(stock.getCompanyName());
		response.setCompanyPrice(stock.getCompanyPrice());
		response.setStocksExchangeResponse(stocksExchangeClient.getStockExchangeById(exchangeId).getBody());
		return response;
	}
}

























































//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import co.in.stockmicroservices.dto.StocksExchangeResponse;
//import co.in.stockmicroservices.dto.StocksResponse;
//import co.in.stockmicroservices.entity.Stocks;
//import co.in.stockmicroservices.exception.StockNotFoundException;
//import co.in.stockmicroservices.repository.StocksRepository;
//
//@Service
//public class StocksServiceImpl implements IStocksService {
//
//	@Autowired
//	private StocksRepository stockRepository;
//
//	@Autowired
//	private StocksExchangeClient StocksExchangeClient;
//
//	@Override
//	public Stocks createStock(Stocks stock) {
//		return stockRepository.save(stock);
//	}
//
//	@Override
//	public List<Stocks> getAllStocks() {
//		return stockRepository.findAll();
//	}
//
//	@Override
//	public Stocks getStockById(int stockId) {
//	    return stockRepository.findById(stockId)
//	            .orElseThrow(() -> new StockNotFoundException("Stock with ID " + stockId + " not found"));
//	}
//
//	@Override
//	public Stocks updateStock(int stockId, Stocks stockDetails) {
//		Stocks existingStock = stockRepository.findById(stockId)
//				.orElseThrow(() -> new StockNotFoundException("Stock with ID " + stockId + " not found"));
//		existingStock.setCompanyName(stockDetails.getCompanyName());
//		existingStock.setCompanyPrice(stockDetails.getCompanyPrice());
//		return stockRepository.save(existingStock);
//	}
//
//	@Override
//	public void deleteStockById(int stockId) {
//		Stocks stock = stockRepository.findById(stockId)
//				.orElseThrow(() -> new StockNotFoundException("Stock with ID " + stockId + " not found"));
//		stockRepository.delete(stock);
//	}
//
//
//
//	@Override
//	public StocksResponse getStocksExchange(int stockId, int exchangeId) {
//		Stocks stocksObj = stockRepository.findById(stockId).get();
//
//		StocksResponse stocksResponseObj = new StocksResponse();
//		stocksResponseObj.setStockId(stocksObj.getStockId());
//		stocksResponseObj.setCompanyName(stocksObj.getCompanyName());
//		stocksResponseObj.setCompanyPrice(stocksObj.getCompanyPrice());
//
//		StocksExchangeResponse stocksexchangeresponseObj = StocksExchangeClient.getStockExchangeById(exchangeId)
//				.getBody();
//		stocksResponseObj.setStocksExchangeResponse(stocksexchangeresponseObj);
//		return stocksResponseObj;
//
//	}
//
//}
