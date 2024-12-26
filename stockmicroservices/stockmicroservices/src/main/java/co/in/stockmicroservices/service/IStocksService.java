package co.in.stockmicroservices.service;

import java.util.List;

import co.in.stockmicroservices.dto.StocksResponse;
import co.in.stockmicroservices.entity.Stocks;

public interface IStocksService {

	Stocks createStock(Stocks stock);

	List<Stocks> getAllStocks();

	Stocks getStockById(int stockId);

	Stocks updateStock(int stockId, Stocks stockDetails);

	void deleteStockById(int stockId);

	StocksResponse getStocksExchange(int stockId, int exchangeId);
}