import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GenerateDNAFile {
    int check;
    static ArrayList<Domains> xyDomainList = new ArrayList<Domains>();
    static DataStack digitizedDataStack = new DataStack();
    void SaveToFile(){
        try {

            String content = ",";


            File file = new File("filename.txt");

            // if file doesnt exist, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            DNADomains SeqDNA;
            int i,j;
            digitizedDataStack.clearData();
            xyDomainList.clear();
            int counter=0;
            for(j=0;j<23;j++)
                for(i=0;i<15;i++){
                    if(j%2==1){
                        if(i<14)  {
                            SeqDNA=new DNADomains(i,j,counter);
                            counter++;
                        }
                    }
                    else if(j%2==0) {
                        SeqDNA=new DNADomains(i,j,counter);
                        counter++;
                    }



                }

            /*
            for(i=0;i<xyDomainList.size();i++){
                bw.write(String.valueOf(xyDomainList.get(i).getxCoordinate()));
                bw.write(String.valueOf(xyDomainList.get(i).getyCoordinate()));
                bw.write(" ");
                bw.write(xyDomainList.get(i).getDomainOne());
                bw.write(" ");
                bw.write(xyDomainList.get(i).getDomainTwo());
                bw.write(" ");
                bw.write(xyDomainList.get(i).getDomainThree());
                bw.write(" ");
                bw.write(xyDomainList.get(i).getDomainFour());
                bw.write("\n");
            }
            bw.close();
             */
            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void doCheck(){
        //DNADomains SeqDNA=new DNADomains();
        //StringBuilder domain=new StringBuilder(SeqDNA.getDomainAll());
        //SearchFile checkDuplicate=new SearchFile();
        //check=checkDuplicate.Search(domain);
        //if(check==1){

        //    System.out.print("\nFound!");
        //    doCheck();
        //}

    }
}