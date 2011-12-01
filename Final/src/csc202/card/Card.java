package csc202.card;

import csc202.security.SafeString;

import java.util.Date;

/**
 * User: Souleiman Ayoub
 * Date: 11/30/11
 * Time: 6:14 PM
 */
public abstract class Card implements Part {
    public static final int VISA = 0;
    public static final int MASTERCARD = 1;
    public static final int AMERICAN_EXPRESS = 2;
    public static final int DISCOVERY = 3;
    public static final int UNKNOWN = -1;

    protected Name name;
    protected SafeString credit;
    protected SafeString verification;
    protected Date expiration;


    /**
     * Set the name of the owners Card.
     *
     * @param name owner of the card
     * @author Souleiman Ayoub
     */
    public void setName(Name name) {
        this.name = name;
    }

    /**
     * @return owner of the cards name.
     * @author Souleiman Ayoub
     */
    public Name getName() {
        return name;
    }

    /**
     * Set the credit information, in an Encrypted String
     *
     * @param credit Encrypted credit information
     * @author Souleiman Ayoub
     */
    public void setCredit(SafeString credit) {
        this.credit = credit;
    }

    /**
     * @return an Encrypted Credit info.
     * @author Souleiman Ayoub
     */
    public SafeString getCredit() {
        return credit;
    }

    /**
     * @return A String with only the last 4 digit of the card.
     * @author Souleiman Ayoub
     */
    public String getVisibleCredit() {
        char[] chars = credit.getSequence();
        int len = chars.length - 4;

        for(int i = 0; i < len; i++)
            chars[i] = '*';
        return new String(chars);
    }

    /**
     * sets the Expiration Date of the Card
     *
     * @param expiration date of expiration
     * @author Souleiman Ayoub
     */
    public void setExpirationDate(Date expiration) {
        this.expiration = expiration;
    }

    /**
     * @return Date of Expiration
     * @author Souleiman Ayoub
     */
    public Date getExpirationDate() {
        return expiration;
    }

    /**
     * @return Type of Card
     *         {0 = VISA, 1 = Mastercard, 2 = American Express 3 = Discovery -1 = Unknown}
     * @author Souleiman Ayoub
     */
    public int getCardType() {
        return Card.UNKNOWN;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;

        Card card = (Card) o;

        if (credit != null ? !credit.equals(card.credit) : card.credit != null) return false;
        if (expiration != null ? !expiration.equals(card.expiration) : card.expiration != null) return false;
        if (name != null ? !name.equals(card.name) : card.name != null) return false;
        if (verification != null ? !verification.equals(card.verification) : card.verification != null) return false;

        return true;
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     * <p/>
     * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
     * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
     * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
     * <tt>y.compareTo(x)</tt> throws an exception.)
     * <p/>
     * <p>The implementor must also ensure that the relation is transitive:
     * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
     * <tt>x.compareTo(z)&gt;0</tt>.
     * <p/>
     * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
     * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
     * all <tt>z</tt>.
     * <p/>
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
     * class that implements the <tt>Comparable</tt> interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     * <p/>
     * <p>In the foregoing description, the notation
     * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
     * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
     * <tt>0</tt>, or <tt>1</tt> according to whether the value of
     * <i>expression</i> is negative, zero or positive.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     *         is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    public int compareTo(Part o) {
        return 0;
    }

    @Override
    public String toString() {
        return "Card{" +
                "name=" + name +
                ", credit=" + credit +
                ", verification=" + verification +
                ", expiration=" + expiration +
                '}';
    }

    public static void main(String[] args) {
        Card card = new Visa();
        card.setCredit(new SafeString("123456789"));
        System.out.println(card.getVisibleCredit());
    }
}