package csc202.card;

import csc202.security.SafeString;
/**
 * This is just a copy and paste of Souleiman's Visa.java i added so i can do more testing
 * @author Justin
 */

public class UnknownCard extends Card{

    public void setVerification(SafeString verification) {
        super.verification = verification;
    }
    public SafeString getVerification() {
        return verification;
    }
    public int getCardType() {
        return Card.UNKNOWN;
    }
}
