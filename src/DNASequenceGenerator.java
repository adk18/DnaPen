/* Author: Arnav Goyal and Akshita Sahai
 * Project: DNA Pen
 * Mentor: Prof. Manish K Gupta
 */

import java.util.Random;

public class DNASequenceGenerator {

    String before;
    String now1;
    String now2;
    String now3;
    String randomDNASeq;
    String dnaAlphabet = "ACGT";

    public DNASequenceGenerator (int seqLength,String x) {
        this.randomDNASeq = "";
        Random randomSeq = new Random();
        if(x.equals("T")){
            if(seqLength==10)
                randomDNASeq = new String("TTTTTTTTTT");
            else
                randomDNASeq = new String("TTTTTTTTTTT");
        }
        else{
            if(seqLength==11) {
                if(x.equals("C")==true)
                    this.randomDNASeq += "C";

                else
                    this.randomDNASeq +=new String(String.valueOf(this.dnaAlphabet.charAt(randomSeq.nextInt(this.dnaAlphabet.length())))) ;

            }
            for (int i = 2; i < seqLength; i++) {

                now1=new String(String.valueOf(this.dnaAlphabet.charAt(randomSeq.nextInt(this.dnaAlphabet.length())))) ;
                this.randomDNASeq += now1;
                i++;
                before=new String(String.valueOf(now1));
                now2=new String(String.valueOf(this.dnaAlphabet.charAt(randomSeq.nextInt(this.dnaAlphabet.length())))) ;
                while((now1.equals(before))&&(now2.equals(before))){
                    now2=new String(String.valueOf(this.dnaAlphabet.charAt(randomSeq.nextInt(this.dnaAlphabet.length())))) ;
                }
                if(i==11&&x.equals("t")){

                    this.randomDNASeq += "T";
                }
                else
                    this.randomDNASeq += now2;
            }
            if(seqLength==10)
                this.randomDNASeq +=new String(String.valueOf(this.dnaAlphabet.charAt(randomSeq.nextInt(this.dnaAlphabet.length())))) ;
            if(seqLength==10){
                if(x.equals("A")==true)
                    this.randomDNASeq +="A";
                else if (x.equals("t")==true)
                    this.randomDNASeq +="T";
                else
                    this.randomDNASeq +=new String(String.valueOf(this.dnaAlphabet.charAt(randomSeq.nextInt(this.dnaAlphabet.length())))) ;
            }
        }
    }


    public  String getT10(){
        return randomDNASeq;
    }

    public String getRandomDNASeq() {
        return randomDNASeq;
    }
}
