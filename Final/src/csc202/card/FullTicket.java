package csc202.card;
/**
 * This is what our data structures will hold. I chose to include name even though name is part of card because there are circumstance that
 * the name on the card and the customer name will not be the same.  Also, if we decide we need addresses than that will be here 2
 * @author Justin Jansen
 * Date: 11/30/11
 */
public class FullTicket {
	private Name name;
	private Card card;
	private Order order;
	
	public FullTicket(Name name, Card card, Order order){
		this.name = name;
		this.card = card;
		this.order = order;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
}
