package co.in.stockmicroservices;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import co.in.stockmicroservices.entity.Stocks;
import co.in.stockmicroservices.exception.StockNotFoundException;
import co.in.stockmicroservices.repository.StocksRepository;
import co.in.stockmicroservices.service.StocksExchangeClient;
import co.in.stockmicroservices.service.StocksServiceImpl;

@SpringBootTest
class StocksServiceImplTest {

	@Mock
	private StocksRepository stockRepository;

	@Mock
	private StocksExchangeClient stocksExchangeClient;

	@InjectMocks
	private StocksServiceImpl stocksService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testCreateStock() {
		Stocks stock = new Stocks(1, "Capgemini", 1100);
		when(stockRepository.save(stock)).thenReturn(stock);

		Stocks createdStock = stocksService.createStock(stock);

		assertNotNull(createdStock, "Created stock should not be null");
		assertEquals(stock, createdStock, "Stock object should match");
		verify(stockRepository).save(stock);
	}

	@Test
	void testGetAllStocks() {
		List<Stocks> stocks = List.of(new Stocks(1, "Capgemini", 1100), new Stocks(2, "Infosys", 1200));
		when(stockRepository.findAll()).thenReturn(stocks);

		List<Stocks> result = stocksService.getAllStocks();

		assertNotNull(result, "Result should not be null");
		assertEquals(stocks, result, "List of stocks should match");
		verify(stockRepository).findAll();
	}

	@Test
	void testGetStockById() {
		Stocks stock = new Stocks(1, "Capgemini", 1100);
		when(stockRepository.findById(1)).thenReturn(Optional.of(stock));

		Stocks result = stocksService.getStockById(1);

		assertNotNull(result, "Stock should be found");
		assertEquals(stock, result, "Stock object should match");
		verify(stockRepository).findById(1);
	}

	@Test
	void testGetStockById_NotFound() {
		when(stockRepository.findById(1)).thenReturn(Optional.empty());

		Exception exception = assertThrows(StockNotFoundException.class, () -> {
			stocksService.getStockById(1);
		});

		assertEquals("Stock with ID 1 not found", exception.getMessage());
		verify(stockRepository).findById(1);
	}

	@Test
	void testDeleteStockById() {
		Stocks stock = new Stocks(1, "Capgemini", 1100);
		when(stockRepository.findById(1)).thenReturn(Optional.of(stock));
		doNothing().when(stockRepository).delete(stock);

		stocksService.deleteStockById(1);

		verify(stockRepository).findById(1);
		verify(stockRepository).delete(stock);
	}

	@Test
	void testDeleteStockById_NotFound() {
		when(stockRepository.findById(1)).thenReturn(Optional.empty());

		Exception exception = assertThrows(StockNotFoundException.class, () -> {
			stocksService.deleteStockById(1);
		});

		assertEquals("Stock with ID 1 not found", exception.getMessage());
		verify(stockRepository).findById(1);
	}
}

























































//    @Test
//    void testgetStocksExchange() {
//        int stockId = 1;
//        int exchangeId = 101;
//
//        // Mock stock data
//        Stocks stock = new Stocks(stockId, "Capgemini", 1100);
//
//        // Mock exchange response
//        StocksExchangeResponse exchangeResponse = new StocksExchangeResponse();
//        exchangeResponse.setExchangeId(exchangeId);
//        exchangeResponse.setExchangeName("National Stock Exchange");
//
//        // Mock repository and client behavior
//        when(stockRepository.findById(stockId)).thenReturn(Optional.of(stock));
//        when(stocksExchangeClient.getStockExchangeById(exchangeId)).thenReturn(ResponseEntity.ok(exchangeResponse));
//
//        // Invoke service method
//        StocksResponse result = stocksService.getStocksExchange(stockId, exchangeId);
//
//        // Validate response
//        assertNotNull(result, "Result should not be null");
//        assertEquals(stockId, result.getStockId(), "Stock ID should match");
//        assertEquals("Capgemini", result.getCompanyName(), "Company name should match");
//        assertEquals(1100, result.getCompanyPrice(), "Company price should match");
//
//        StocksExchangeResponse actualExchangeResponse = result.getStocksExchangeResponse();
//        assertNotNull(actualExchangeResponse, "StocksExchangeResponse should not be null");
//        assertEquals(exchangeId, actualExchangeResponse.getExchangeId(), "Exchange ID should match");
//        assertEquals("National Stock Exchange", actualExchangeResponse.getExchangeName(), "Exchange name should match");
//
//        // Verify interactions
//        verify(stockRepository).findById(stockId);
//        verify(stocksExchangeClient).getStockExchangeById(exchangeId);
//    }
