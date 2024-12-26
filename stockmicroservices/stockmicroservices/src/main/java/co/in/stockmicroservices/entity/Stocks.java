package co.in.stockmicroservices.entity;

import co.in.stockmicroservices.dto.StocksExchangeResponse;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "stocks")
public class Stocks {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int stockId;
	private String companyName;
	private int companyPrice;

	@Transient
	private StocksExchangeResponse stocksExchangeResponse;

	public int getStockId() {
		return stockId;
	}

	public void setStockId(int stockId) {
		this.stockId = stockId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getCompanyPrice() {
		return companyPrice;
	}

	public void setCompanyPrice(int companyPrice) {
		this.companyPrice = companyPrice;
	}

	public StocksExchangeResponse getStocksExchangeResponse() {
		return stocksExchangeResponse;
	}

	public void setStocksExchangeResponse(StocksExchangeResponse stocksExchangeResponse) {
		this.stocksExchangeResponse = stocksExchangeResponse;
	}

	public Stocks(int stockId, String companyName, int companyPrice) {
		super();
		this.stockId = stockId;
		this.companyName = companyName;
		this.companyPrice = companyPrice;
		this.stocksExchangeResponse = stocksExchangeResponse;
	}

	public Stocks() {
		super();
	}

	@Override
	public String toString() {
		return "StocksResponse [stockId=" + stockId + ", companyName=" + companyName + ", companyPrice=" + companyPrice
				+ ", stocksExchangeResponse=" + stocksExchangeResponse + "]";
	}

}
