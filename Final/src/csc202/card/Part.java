package csc202.card;

import csc202.security.SafeString;

import java.util.Date;

/**
 * User: Souleiman Ayoub
 * Date: 11/30/11
 * Time: 5:16 PM
 */
public interface Part extends Comparable<Part>{

    /**
     * Set the name of the owners Card.
     * @param name owner of the card
     * @author Souleiman Ayoub
     */
    public void setName(Name name);

    /**
     * @return owner of the cards name.
     * @author Souleiman Ayoub
     */
    public Name getName();

    /**
     * Set the credit information, in an Encrypted String
     * @param credit Encrypted credit information
     * @author Souleiman Ayoub
     */
    public void setCredit(SafeString credit);

    /**
     * @return an Encrypted Credit info.
     * @author Souleiman Ayoub
     */
    public SafeString getCredit();

    /**
     * @return A String with only the last 4 digit of the card.
     * @author Souleiman Ayoub
     */
    public String getVisibleCredit();

    /**
     * Sets the verification number
     * @param verification encrypted Verification number.
     * @author Souleiman Ayoub
     */
    public void setVerification(SafeString verification);

    /**
     * @return an Encrypted Verification String
     * @author Souleiman Ayoub
     */
    public SafeString getVerification();

    /**
     * sets the Expiration Date of the Card
     * @param expiration date of expiration
     * @author Souleiman Ayoub
     */
    public void setExpirationDate(Date expiration);

    /**
     * @return Date of Expiration
     * @author Souleiman Ayoub
     */
    public Date getExpirationDate();

    /**
     * @return Type of Card
     * {0 = VISA, 1 = Mastercard, 2 = American Express 3 = Discovery -1 = Unknown}
     * @author Souleiman Ayoub
     */
    public int getCardType();
}