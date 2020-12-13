package dataManagePackage.Receipt;

public class ReceiptFactory {
	
	public Receipt createNewReceipt(String kind, String id, String date, String amount, String name, String country, String city, String street, String number){
		if (kind==null) {
			return null;
		}else{
			return new Receipt(kind, id, date, amount, name, country, city, street, number);
		}
	}
}
