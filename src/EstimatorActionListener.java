import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: shikhar
 * Date: 8/16/13
 * Time: 3:31 AM
 */
public class EstimatorActionListener implements ActionListener {
    static ArrayList<Domains> xyDrawdataList =SaveDrawDataActionListener.xyDrawdataListDigital;
    static ArrayList<Domains> xyDrawdataListFree =SaveDrawDataActionListener.xyDrawdataListFree;
    @Override
    public void actionPerformed(ActionEvent e) {
        if(MainFrame.currentWindow==2){
        File file=new File(MainFrame.ProjPath+"/"+"FreeGrid_Detailed_DNAData_"+MainFrame.ProjName+".csv");
        if(!file.exists()){
            JOptionPane.showMessageDialog(null, "Please save the Detailed DNA Data first",
                    "Success!", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        else{
        String fn=JOptionPane.showInputDialog(null, "Enter cost for a base (USD): ", "DNA Pen Cost Estimator", JOptionPane.QUESTION_MESSAGE)  ;
        float n1=Float.parseFloat(fn);
            int fullfree=0;
            int halffree=0;
            float totalcostfree=0;

            for(int i=0;i<xyDrawdataListFree.size();i++){
            if(xyDrawdataListFree.get(i).getDomainOne()!=""&&xyDrawdataListFree.get(i).getDomainTwo()!=""&&
                    xyDrawdataListFree.get(i).getDomainThree()!=""&&xyDrawdataListFree.get(i).getDomainFour()!=""){

                fullfree++;
            }
            if(xyDrawdataListFree.get(i).getDomainOne()==""&&xyDrawdataListFree.get(i).getDomainTwo()==""){

                halffree++;
            }
            if(xyDrawdataListFree.get(i).getDomainThree()==""&&xyDrawdataListFree.get(i).getDomainFour()==""){

                halffree++;
            }
        }
            totalcostfree=(fullfree*42+halffree*21)*n1;
            JOptionPane.showMessageDialog(null, "Total cost (USD):"+totalcostfree, "DNA Pen Cost Estimator", JOptionPane.INFORMATION_MESSAGE );
        }
        }
        if(MainFrame.currentWindow==3){
            File file=new File(MainFrame.ProjPath+"/"+"DigitalGrid_Detailed_DNAData_"+MainFrame.ProjName+".csv");
            if(!file.exists()){
                JOptionPane.showMessageDialog(null, "Please save the Detailed DNA Data first",
                        "Success!", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            else{
                String fn=JOptionPane.showInputDialog(null, "Enter cost for a base (USD): ", "DNA Pen Cost Estimator", JOptionPane.QUESTION_MESSAGE)  ;
                float n1=Float.parseFloat(fn);
                int full=0;
                int half=0;
                float totalcost=0;

                for(int i=0;i<xyDrawdataList.size();i++){
                    if(xyDrawdataList.get(i).getDomainOne()!=""&&xyDrawdataList.get(i).getDomainTwo()!=""&&
                            xyDrawdataList.get(i).getDomainThree()!=""&&xyDrawdataList.get(i).getDomainFour()!=""){

                        full++;
                    }
                    if(xyDrawdataList.get(i).getDomainOne()==""&&xyDrawdataList.get(i).getDomainTwo()==""){

                        half++;
                    }
                    if(xyDrawdataList.get(i).getDomainThree()==""&&xyDrawdataList.get(i).getDomainFour()==""){

                        half++;
                    }
                }
                totalcost=(full*42+half*21)*n1;
                JOptionPane.showMessageDialog(null, "Total cost (USD):"+totalcost, "DNA Pen Cost Estimator", JOptionPane.INFORMATION_MESSAGE );
            }
        }
    }

}
