/* Author: Arnav Goyal and Akshita Sahai
 * Project: DNA Pen
 * Mentor: Prof. Manish K Gupta
 */

import javax.swing.*;
import java.util.ArrayList;

/*
*
* The combined length of the Domains is 42.
* There are two pairs of Domains. Domain 1,2 and Domain 3,4
* each Pair is of length 21.
*/
public class DNADomains {

    // Define the DNA Strings for the 4 Domains to be used in the construction of DNA Bricks

    public String domainSeqOne;
    public String domainSeqTwo;
    public String domainSeqThree;
    public String domainSeqFour;
    public String CompleteDomainSeq;
    public boolean allPassed = false;
    public int checkCount = 0;
    static DataStack digitizedDataStack = GenerateDNAFile.digitizedDataStack;

    static ArrayList<Domains> xyDomainList = GenerateDNAFile.xyDomainList;
    DNASequenceGenerator dnaSequenceGenerator;
    int i,j;
    int entry;
    public DNADomains(){
        checkAll();
    }
    public DNADomains(int i, int j, int e) {
        entry=e;
        checkAll(i,j); // This function would ensure Error-free and stable sequences
    }

    public void generateSeq(int i, int j) {
        if(j==0){
            if(i==0){
                dnaSequenceGenerator = new DNASequenceGenerator(10,"T");
                domainSeqOne = dnaSequenceGenerator.getRandomDNASeq();

                dnaSequenceGenerator = new DNASequenceGenerator(11,"t");
                domainSeqTwo = dnaSequenceGenerator.getRandomDNASeq();

                dnaSequenceGenerator = new DNASequenceGenerator(11,"");
                domainSeqThree = dnaSequenceGenerator.getRandomDNASeq();

                dnaSequenceGenerator = new DNASequenceGenerator(10,"T");
                domainSeqFour = dnaSequenceGenerator.getRandomDNASeq();
            }

            else if(i==14){
                dnaSequenceGenerator = new DNASequenceGenerator(10,"C");
                domainSeqOne = dnaSequenceGenerator.getRandomDNASeq();

                dnaSequenceGenerator = new DNASequenceGenerator(11,"T");
                domainSeqTwo = dnaSequenceGenerator.getRandomDNASeq();

                dnaSequenceGenerator = new DNASequenceGenerator(11,"T");
                domainSeqThree = dnaSequenceGenerator.getRandomDNASeq();

                dnaSequenceGenerator = new DNASequenceGenerator(10,"A");
                domainSeqFour = dnaSequenceGenerator.getRandomDNASeq();
            }

            else {
                dnaSequenceGenerator = new DNASequenceGenerator(10,"C");
                domainSeqOne = dnaSequenceGenerator.getRandomDNASeq();

                dnaSequenceGenerator = new DNASequenceGenerator(11,"t");
                domainSeqTwo = dnaSequenceGenerator.getRandomDNASeq();

                dnaSequenceGenerator = new DNASequenceGenerator(11,"");
                domainSeqThree = dnaSequenceGenerator.getRandomDNASeq();

                dnaSequenceGenerator = new DNASequenceGenerator(10,"A");
                domainSeqFour = dnaSequenceGenerator.getRandomDNASeq();
            }

            //xyDomainList.add(new Domains(i,j,domainSeqOne,domainSeqTwo,domainSeqThree,domainSeqFour));
            //entry++;

            System.out.print("\n one "+i+" "+j+" "+entry);
        }

        else if(j%2==1){
            int temp;

            temp=entry-15;

            domainSeqFour = negateSeqRev(xyDomainList.get(temp).domainSeqTwo) ;
            temp++;
            domainSeqThree = negateSeqRev(xyDomainList.get(temp).domainSeqOne) ;

            dnaSequenceGenerator = new DNASequenceGenerator(11,"C");
            domainSeqOne = dnaSequenceGenerator.getRandomDNASeq();

            dnaSequenceGenerator = new DNASequenceGenerator(10,"t");
            domainSeqTwo = dnaSequenceGenerator.getRandomDNASeq();

            System.out.print("\n two "+i+" "+j+ " "+entry);

        }
        else if((j>0)&&(j%2==0)){
            int temp;
            temp=entry-14;
            if(i==0){

                dnaSequenceGenerator = new DNASequenceGenerator(10,"T");
                domainSeqOne = dnaSequenceGenerator.getRandomDNASeq();

                dnaSequenceGenerator = new DNASequenceGenerator(10,"T");
                domainSeqFour = dnaSequenceGenerator.getRandomDNASeq();

                dnaSequenceGenerator = new DNASequenceGenerator(11,"t");
                domainSeqTwo = dnaSequenceGenerator.getRandomDNASeq();

                domainSeqThree = negateSeqRev(xyDomainList.get(temp).domainSeqOne) ;



                System.out.print("\n three "+i+" "+j+" "+entry);
            }
            else if(i==14){
                temp--;
                domainSeqFour = negateSeqRev(xyDomainList.get(temp).domainSeqTwo) ;

                dnaSequenceGenerator = new DNASequenceGenerator(10,"C");
                domainSeqOne = dnaSequenceGenerator.getRandomDNASeq();

                dnaSequenceGenerator = new DNASequenceGenerator(11,"T");
                domainSeqTwo = dnaSequenceGenerator.getRandomDNASeq();

                dnaSequenceGenerator = new DNASequenceGenerator(11,"T");
                domainSeqThree = dnaSequenceGenerator.getRandomDNASeq();


                System.out.print("\n four "+i+" "+j+" "+entry);

            }

            else{

                temp--;
                domainSeqFour = negateSeqRev(xyDomainList.get(temp).domainSeqTwo) ;
                temp++;
                domainSeqThree = negateSeqRev(xyDomainList.get(temp).domainSeqOne) ;

                dnaSequenceGenerator = new DNASequenceGenerator(10,"C");
                domainSeqOne = dnaSequenceGenerator.getRandomDNASeq();

                dnaSequenceGenerator = new DNASequenceGenerator(11,"t");
                domainSeqTwo = dnaSequenceGenerator.getRandomDNASeq();

                System.out.print("\n five "+i+" "+j+" "+entry);

            }


        }

    }

    public void checkAll(){
        boolean hammingCheck;
        boolean thermalCheck;
        while(!allPassed) {
            if (checkCount >= 10) {

                JOptionPane.showMessageDialog(null, "No Stable Sequence Found. Try Again Later. Sorry !",
                        "Error!", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
            checkCount++;
            generateSeq(i,j);

            hammingCheck = checkHamming();
            thermalCheck = checkThermalSecondaryStability();
            allPassed = hammingCheck && thermalCheck;
        }
    }

    public void checkAll (int i,int j) {
        boolean hammingCheck;
        boolean thermalCheck;
        if ((j%2==1)&&(i==14))  {
            //do nothing
        }
        else if(j%2==0&&((i==0)||(i==14)))   {
            generateSeq(i,j);
            xyDomainList.add(new Domains(i,j,domainSeqOne,domainSeqTwo,domainSeqThree,domainSeqFour));
        }
        else{
            while(!allPassed) {
                if (checkCount >= 10) {

                    JOptionPane.showMessageDialog(null, "No Stable Sequence Found. Try Again Later. Sorry !",
                            "Error!", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                }
                checkCount++;
                generateSeq(i,j);

                hammingCheck = checkHamming();
                thermalCheck = checkThermalSecondaryStability();
                allPassed = hammingCheck && thermalCheck;
            }
            if(allPassed)
                xyDomainList.add(new Domains(i,j,domainSeqOne,domainSeqTwo,domainSeqThree,domainSeqFour));
        }
    }

    public String negateSeqRev(String domainSeq) {
        String negateSeq = "";
        int stringLength = domainSeq.length();

        for (int i = stringLength-1; i >=0; i--) {
            if (domainSeq.charAt(i) == 'A') {
                negateSeq += "T";
            } else if (domainSeq.charAt(i) == 'T') {
                negateSeq += "A";
            } else if (domainSeq.charAt(i) == 'G') {
                negateSeq += "C";
            } else if (domainSeq.charAt(i) == 'C') {
                negateSeq += "G";
            }
        }
        return (negateSeq);
    }
    public String negateSeq(String domainSeq) {
        String negateSeq = "";
        int stringLength = domainSeq.length();

        for (int i = 0; i <stringLength; i++) {
            if (domainSeq.charAt(i) == 'A') {
                negateSeq += "T";
            } else if (domainSeq.charAt(i) == 'T') {
                negateSeq += "A";
            } else if (domainSeq.charAt(i) == 'G') {
                negateSeq += "C";
            } else if (domainSeq.charAt(i) == 'C') {
                negateSeq += "G";
            }
        }
        return (negateSeq);
    }

    public String getDomainSeqOne() {
        return domainSeqOne;
    }

    public String getDomainSeqTwo() {
        return domainSeqTwo;
    }

    public String getDomainSeqThree() {
        return domainSeqThree;
    }

    public String getDomainSeqFour() {
        return domainSeqFour;
    }
    public String getDomainAll() {
        CompleteDomainSeq=new String("");
        CompleteDomainSeq=CompleteDomainSeq.concat(domainSeqOne);
        CompleteDomainSeq=CompleteDomainSeq.concat(domainSeqTwo);
        CompleteDomainSeq=CompleteDomainSeq.concat(domainSeqThree);
        CompleteDomainSeq=CompleteDomainSeq.concat(domainSeqFour);
        return CompleteDomainSeq;
    }
    public boolean checkHamming() {
        int countMatch = 0; // The lesser the value, the more error correcting capability
        boolean passedHamming = false;

        char[] sequenceOne = domainSeqOne.toCharArray();
        char[] sequenceTwo = domainSeqTwo.toCharArray();

        for (int i = 0; i < 10; i++) {
            if (sequenceOne[i] == sequenceTwo[i]) {
                countMatch++;
            }
        }

        if (countMatch <= 3) {
            passedHamming = true;
        }

        countMatch = 0;
        //i<11
        for (int i = 1; i < 10; i++) {
            if (sequenceOne[i - 1] == sequenceTwo[i]) {
                countMatch++;
            }
        }

        if (countMatch <= 3) {
            passedHamming = true;
        }

        return passedHamming;
    }

    public boolean checkThermalSecondaryStability() {
        boolean thermalPassed = false;
        boolean thermalSeqOne = false;
        boolean thermalSeqTwo = false;
        int seqOneLength = domainSeqOne.length();
        int seqTwoLength = domainSeqTwo.length();

        thermalSeqOne = checkSeqStability(domainSeqOne, seqOneLength);
        thermalSeqTwo = checkSeqStability(domainSeqTwo, seqTwoLength);

        thermalPassed = thermalSeqOne && thermalSeqTwo;

        return thermalPassed;
    }

    public boolean checkSeqStability(String dnaSequence, int seqLength) {
        boolean seqStable = false;

        int[][] seqOneEnergyArray = new int[seqLength + 1][seqLength + 1];
        int[][] seqOneArray = new int[seqLength + 1][seqLength + 1];

        for (int i = 0; i < seqLength; i++) {
            for (int j = 0; j < seqLength; j++) {
                seqOneEnergyArray[i][j] = 1;
                seqOneEnergyArray[i][seqLength] = 0;
                seqOneEnergyArray[seqLength][j] = 0;
            }
        }

        seqOneEnergyArray[0][0] = 0;

        for (int i = 1; i < seqLength; i++) {
            seqOneEnergyArray[i][i] = 0;
            seqOneEnergyArray[i][i - 1] = 0;
        }

        int j = 0;

        for (int i = 0; i < seqLength; i++) {
            char seqChar = dnaSequence.charAt(i);

            if (seqChar == 'A') {
                seqOneEnergyArray[0][i + 1] = 65;
                seqOneEnergyArray[i+1][0]=65;
            } else if (seqChar == 'G') {
                seqOneEnergyArray[0][i + 1] = 71;
                seqOneEnergyArray[i + 1][0] = 71;
            } else if (seqChar == 'C') {
                seqOneEnergyArray[0][i + 1] = 67;
                seqOneEnergyArray[i + 1][0] = 67;
            } else if (seqChar == 'T') {
                seqOneEnergyArray[0][i + 1] = 84;
                seqOneEnergyArray[i + 1][0] = 84;
            }
        }

        for (int i = 0; i <= seqLength; i++) {
            for (j = 0; j <= seqLength; j++) {
                seqOneArray[i][j] = 0;
                seqOneArray[i][seqLength] = 0;
                seqOneArray[seqLength][j] = 0;
            }
        }

        for (int i = 1; i <= seqLength; i++) {
            seqOneArray[0][i] = seqOneEnergyArray[0][i];
            seqOneArray[i][0] = seqOneEnergyArray[i][0];
        }

        for (int i = 1; i <= seqLength; i++) {
            for (j = 1; j <= seqLength; j++) {
                if (seqOneEnergyArray[i][0] == 65 && seqOneEnergyArray[0][j] == 84) {
                    seqOneArray[i][j] = -1;
                } else if (seqOneEnergyArray[i][0] == 84 && seqOneEnergyArray[0][j] == 65) {
                    seqOneArray[i][j] = -1;
                } else if (seqOneEnergyArray[i][0] == 67 && seqOneEnergyArray[0][j] == 71) {
                    seqOneArray[i][j] = -2;
                } else if (seqOneEnergyArray[i][0] == 71 && seqOneEnergyArray[0][j] == 67) {
                    seqOneArray[i][j] = -2;
                }
            }
        }

        int a1, b1 = 0;
        int c1, d1;
        int k = 0, i = 0, p = 0, countOne = 0, countTwo = 0;
        int[] varArray1 = new int[seqLength];
        int[] varArray2 = new int[seqLength];
        for (k = 1; k <= seqLength; k++) {
            j = 1;
            for (i = k + 1; i <= seqLength; i++ ) {
                countOne = 0;
                countTwo = 0;

                for(p = j + 1; p <= i - 1; p++) {
                    varArray1[countOne]= seqOneEnergyArray[j][p];
                    countOne++;
                }
                for(p = j + 1; p <= i - 1; p++) {
                    varArray2[countTwo] = seqOneEnergyArray[p][i];
                    countTwo++;
                }

                int minValueOne = minimum(varArray1, countOne);
                int minValueTwo = minimum(varArray2, countTwo);
                b1 = Math.min(minValueOne, minValueTwo);
                a1 = seqOneEnergyArray[j+1][i-1] + seqOneArray[j][i];

                seqOneEnergyArray[j][i] = Math.min(a1, b1);
                j++;
            }
        }

        int seqEnergy = calcMinimumEnergy(seqOneEnergyArray, seqLength);

        if (seqEnergy < 0) {
            seqStable = true;
        }
        return seqStable;
    }

    public int minimum(int[] dataArray, int seqLength) {
        int minimumValue = dataArray[0];
        int i = 0;

        while (i < seqLength) {
            if (minimumValue > dataArray[i]) {
                minimumValue = dataArray[i];
            }
            if(minimumValue <= dataArray[i]) {
                i++;
            }
        }
        return minimumValue;
    }

    public int calcMinimumEnergy(int[][] e, int seqLength) {
        int minEnergy = 0;
        int j = 0;
        for(int i = 0; i < seqLength; i++) {
            j = 0;
            while(j <= seqLength) {
                if (e[i][j] <= minEnergy) {
                    minEnergy = e[i][j];
                }
                j++;
            }
        }
        return minEnergy;
    }
}

