/**
 * Created with IntelliJ IDEA.
 * User: Foram
 * Date: 7/28/13
 * Time: 2:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class Domains {
    public int x;
    public int y;
    public String domainSeqOne;
    public String domainSeqTwo;
    public String domainSeqThree;
    public String domainSeqFour;
    public String CompleteDomainSeq;

    public Domains(int x, int y, String domainSeqOne,String domainSeqTwo, String domainSeqThree,String domainSeqFour) {
        this.x = x;
        this.y = y;
        this.domainSeqOne = domainSeqOne;
        this.domainSeqTwo = domainSeqTwo;
        this.domainSeqThree = domainSeqThree;
        this.domainSeqFour = domainSeqFour;
    }

    public int getxCoordinate() {
        return x;
    }

    public int getyCoordinate() {
        return y;
    }

    public String getDomainOne() {
        return domainSeqOne;
    }

    public String getDomainTwo() {
        return domainSeqTwo;
    }
    public String getDomainThree() {
        return domainSeqThree;
    }

    public String getDomainFour() {
        return domainSeqFour;
    }

}
