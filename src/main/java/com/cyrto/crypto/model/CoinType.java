package com.cyrto.crypto.model;

public enum CoinType {
	
	Litecoin("Litecoin"),
	Ethereum("Ethereum"),
	CryptoCoin("CryptoCoin");  
	
	private String type;
	
	
	
	CoinType(String type){
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	public String toString() {
		return type;
	}
	
}