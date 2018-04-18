/* Author: Arnav Goyal and Akshita Sahai
 * Project: DNA Pen
 * Mentor: Prof. Manish K Gupta
 */

public class StickyEnd {

    String domainTop;
    String domainBottom;

    public StickyEnd(String domainTop, String domainBottom) {
        this.domainTop = domainTop;
        this.domainBottom = domainBottom;
    }

    public String getDomainTop() {
        return domainTop;
    }

    public String getDomainBottom() {
        return domainBottom;
    }

    @Override
    public String toString() {
        return this.domainBottom + this.domainTop;
    }
}
