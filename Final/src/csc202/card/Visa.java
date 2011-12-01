package csc202.card;

import csc202.security.SafeString;

/**
 * User: Souleiman Ayoub
 * Date: 11/30/11
 * Time: 6:28 PM
 */
public class Visa extends Card{

    /**
     * Sets the verification number
     *
     * @param verification encrypted Verification number.
     * @author Souleiman Ayoub
     */
    public void setVerification(SafeString verification) {
        super.verification = verification;
    }

    /**
     * @return an Encrypted Verification String
     * @author Souleiman Ayoub
     */
    public SafeString getVerification() {
        return verification;
    }

    /**
     * @return Type of Card
     *         {0 = VISA, 1 = Mastercard, 2 = American Express 3 = Discovery -1 = Unknown}
     * @author Souleiman Ayoub
     */
    public int getCardType() {
        return Card.VISA;
    }
}
