/**
 * Created with IntelliJ IDEA.
 * User: Foram
 * Date: 8/11/13
 * Time: 10:41 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.ArrayList;

public class GenerateFreegridDNA {
    int check;
    static ArrayList<Domains> xyDomainList = new ArrayList<Domains>();
    static DataStack digitizedDataStack = new DataStack();
    static int w;
    static int h;

    public void savetoFile(){
        /*
        if(EditDimensionActionListener.BrickwallWidth!=0){
            w=1050/EditDimensionActionListener.BrickwallWidth;
            h=700/EditDimensionActionListener.BrickWallHeight;

        }
        */
        //else {
            w=1050/7;
            h=700/3;
        //}
        DNADomainsFreeGrid SeqDNA;
        int i,j;

        digitizedDataStack.clearData();
        xyDomainList.clear();
        int counter=0;
        for(j=0;j<h;j++)
            for(i=0;i<w;i++){
                if(j%2==1){
                    if(i<w-1)  {
                        SeqDNA=new DNADomainsFreeGrid(i,j,counter);
                        counter++;
                    }
                }
                else if(j%2==0) {
                    SeqDNA=new DNADomainsFreeGrid(i,j,counter);
                    counter++;
                }



            }
        System.out.println("Done");

    }
}