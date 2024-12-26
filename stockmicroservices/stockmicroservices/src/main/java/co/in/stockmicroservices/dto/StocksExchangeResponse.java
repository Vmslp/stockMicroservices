package co.in.stockmicroservices.dto;

public class StocksExchangeResponse {

	private int exchangeId;

	private String exchangeName;
	private String description;

	public int getExchangeId() {
		return exchangeId;
	}

	public void setExchangeId(int exchangeId) {
		this.exchangeId = exchangeId;
	}

	public String getExchangeName() {
		return exchangeName;
	}

	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public StocksExchangeResponse(int exchangeId, String exchangeName, String description) {
		super();
		this.exchangeId = exchangeId;
		this.exchangeName = exchangeName;
		this.description = description;
	}

	public StocksExchangeResponse() {
		super();

	}

	@Override
	public String toString() {
		return "StocksExchange [exchangeId=" + exchangeId + ", exchangeName=" + exchangeName + ", description="
				+ description + "]";
	}
}
