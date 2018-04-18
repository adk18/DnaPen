import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Foram
 * Date: 8/5/13
 * Time: 6:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class SaveImageActionListener implements ActionListener {

    static ArrayList<Domains> xyDrawdataList =SaveDrawDataActionListener.xyDrawdataListDigital;
    static ArrayList<Domains> xyDrawdataListFree =SaveDrawDataActionListener.xyDrawdataListFree;
    static int bw=FreeGridActionListener.brickWidth;
    static int bh=FreeGridActionListener.brickHeight;

    @Override
    public void actionPerformed(ActionEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.

        if (MainFrame.newBrickCreated) {
            JFrame gui = new JFrame("Output Value Generated");
            contentPane = gui.getContentPane();
            contentPane.setLayout(null);
            contentPane.setBackground(SystemColor.WHITE);

            //----
            if(MainFrame.currentWindow==3){
                File file=new File(MainFrame.ProjPath+"/"+"DigitalGrid_Detailed_DNAData_"+MainFrame.ProjName+".csv");
                if(!file.exists()){
                    JOptionPane.showMessageDialog(null, "Please save the Detailed DNA Data first",
                            "Success!", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                else{
                for(int i=0;i<xyDrawdataList.size();i++){
                    if(xyDrawdataList.get(i).getDomainOne()!=""&&xyDrawdataList.get(i).getDomainTwo()!=""&&
                            xyDrawdataList.get(i).getDomainThree()!=""&&xyDrawdataList.get(i).getDomainFour()!=""){

                        addFull(xyDrawdataList.get(i).getxCoordinate(),xyDrawdataList.get(i).getyCoordinate());
                    }
                    if(xyDrawdataList.get(i).getDomainOne()==""&&xyDrawdataList.get(i).getDomainTwo()==""){

                        addLowerHalf(xyDrawdataList.get(i).getxCoordinate(),xyDrawdataList.get(i).getyCoordinate());
                    }
                    if(xyDrawdataList.get(i).getDomainThree()==""&&xyDrawdataList.get(i).getDomainFour()==""){

                        addUpperHalf(xyDrawdataList.get(i).getxCoordinate(),xyDrawdataList.get(i).getyCoordinate());
                    }
                }


                gui.setVisible(true);
                gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                gui.setSize(700,450);
                gui.setResizable(false);
                ImageIcon imgd = new ImageIcon("images/templogo.png");
                gui.setIconImage(imgd.getImage());

                //jdialog starts
                final JDialog d = new JDialog((Frame)null,"DNA Sequences");
                d.setIconImage(imgd.getImage());
                JPanel topPanel = new JPanel(new GridLayout(1,2));
                d.setLocation(710,0);
                JPanel basic = new JPanel();
                basic.setLayout(new BoxLayout(basic, BoxLayout.Y_AXIS));
                d.add(basic);
                JPanel textPanel = new JPanel(new BorderLayout());
                textPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
                final JTextPane pane = new JTextPane();
                pane.setContentType("text/html");
                String text =getText();
                pane.setText(text);
                pane.setEditable(false);
                textPanel.add(new JScrollPane(pane));
                topPanel.setSize(700,400);
                basic.add(textPanel);
                basic.add(topPanel);
                d.setSize(new Dimension(450, 150));
                d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                d.setVisible(true);
                }
            }
            else if(MainFrame.currentWindow==2){
                File file=new File(MainFrame.ProjPath+"/"+"FreeGrid_Detailed_DNAData_"+MainFrame.ProjName+".csv");
                if(!file.exists()){
                    JOptionPane.showMessageDialog(null, "Please save the Detailed DNA Data first",
                            "Success!", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                else{
                    bw=FreeGridActionListener.brickWidth;
                    bh=FreeGridActionListener.brickHeight;
                for(int i=0;i<xyDrawdataListFree.size();i++){

                    if(xyDrawdataListFree.get(i).getDomainOne()!=""&&xyDrawdataListFree.get(i).getDomainTwo()!=""&&
                            xyDrawdataListFree.get(i).getDomainThree()!=""&&xyDrawdataListFree.get(i).getDomainFour()!=""){
                        addFullFree(xyDrawdataListFree.get(i).getxCoordinate(),xyDrawdataListFree.get(i).getyCoordinate());
                    }
                    if(xyDrawdataListFree.get(i).getDomainOne()==""&&xyDrawdataListFree.get(i).getDomainTwo()==""){
                        addLowerHalfFree(xyDrawdataListFree.get(i).getxCoordinate(),xyDrawdataListFree.get(i).getyCoordinate());
                    }
                    if(xyDrawdataListFree.get(i).getDomainThree()==""&&xyDrawdataListFree.get(i).getDomainFour()==""){
                        addUpperHalfFree(xyDrawdataListFree.get(i).getxCoordinate(),xyDrawdataListFree.get(i).getyCoordinate());
                    }
                }

                gui.setVisible(true);
                gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                gui.setSize(1600,1000);
                gui.setResizable(false);
                ImageIcon imgd = new ImageIcon("images/templogo.png");
                gui.setIconImage(imgd.getImage());

                //jdialog starts
                final JDialog d = new JDialog((Frame)null,"DNA Sequences");
                d.setIconImage(imgd.getImage());
                JPanel topPanel = new JPanel(new GridLayout(1,2));
                d.setLocation(710,0);
                JPanel basic = new JPanel();
                basic.setLayout(new BoxLayout(basic, BoxLayout.Y_AXIS));
                d.add(basic);
                JPanel textPanel = new JPanel(new BorderLayout());
                textPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
                final JTextPane pane = new JTextPane();
                pane.setContentType("text/html");
                String text =getTextFree();
                pane.setText(text);
                pane.setEditable(false);
                textPanel.add(new JScrollPane(pane));
                topPanel.setSize(700,400);
                basic.add(textPanel);
                basic.add(topPanel);
                d.setSize(new Dimension(450, 150));
                d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                d.setVisible(true);
               }
            }
        }
    }
    public static void addUpperHalf(int a, int b){
        JLabel img = new JLabel(new ImageIcon("images/upperhalf.png"));
        if(b%2==0)
            img.setBounds(45*a+22,15*b+5, 45,8); // x, y, width, height
        else
            img.setBounds(45*a+22+22,15*b+5, 45,8); // x, y, width, height
        contentPane.add(img);
    }
    public static void addFull(int a, int b){
        JLabel img = new JLabel(new ImageIcon("images/full.png"));
        if(b%2==0){
            img.setBounds(45*a,15*b+15, 45,15); // x, y, width, height
        }
        else
            img.setBounds(45*a+22,15*b+15, 45,15); // x, y, width, height
        contentPane.add(img);
    }
    public static void addLowerHalf(int a, int b){
        JLabel img = new JLabel(new ImageIcon("images/lowerhalf.png"));
        if(b%2==0)
            img.setBounds(45*a+22,15*b+15+15, 45,8); // x, y, width, height
        else
            img.setBounds(45*a+22+22,15*b+15+15, 45,8); // x, y, width, height
        contentPane.add(img);
    }
    public static void addUpperHalfFree(int a, int b){
        JLabel img = new JLabel(new ImageIcon("images/TileImages/"+bh+"x"+bw+"upperhalf.png"));
            if(b%2==0)
                img.setBounds(a*bw+(bw/2),b*bh,bw,bh/2);
            else
                img.setBounds(a*bw+(bw/2)+(bw/2),b*bh,bw,bh/2);
        contentPane.add(img);
    }
    public static void addFullFree(int a, int b){
        JLabel img = new JLabel(new ImageIcon("images/TileImages/"+bh+"x"+bw+"full.png"));
            if(b%2==0){
                img.setBounds(a*bw,b*bh+(bh/2),bw,bh);
            }
            else
                img.setBounds(a*bw+(bw/2),b*bh+(bh/2),bw,bh);

        contentPane.add(img);
    }
    public static void addLowerHalfFree(int a, int b){
        JLabel img = new JLabel(new ImageIcon("images/TileImages/"+bh+"x"+bw+"lowerhalf.png"));
            if(b%2==0)
                img.setBounds(a*bw+(bw/2),b*bh+bh+(bh/2),bw,bh/2);
            else
                img.setBounds(a*bw+(bw/2)+(bw/2),b*bh+bh+(bh/2), bw,bh/2);

        contentPane.add(img);

    }
    public static String getText(){
        String dnaSequences="\n";
        for(int i=0;i<xyDrawdataList.size();i++){
            dnaSequences=dnaSequences.concat("Brick "+i+": X: "+xyDrawdataList.get(i).getxCoordinate()+" Y:"+
                    xyDrawdataList.get(i).getyCoordinate()+ " DNA Sequence: "+xyDrawdataList.get(i).getDomainOne()+
                    xyDrawdataList.get(i).getDomainTwo()+xyDrawdataList.get(i).getDomainThree()+
                    xyDrawdataList.get(i).getDomainFour()+"\n");
        }
        System.out.println(dnaSequences);
        return dnaSequences;
    }
    public static String getTextFree(){
        String dnaSequences="\n";
        for(int i=0;i<xyDrawdataListFree.size();i++){
            dnaSequences=dnaSequences.concat("Brick "+i+": X: "+xyDrawdataListFree.get(i).getxCoordinate()+" Y:"+
                    xyDrawdataListFree.get(i).getyCoordinate()+ " DNA Sequence: "+xyDrawdataListFree.get(i).getDomainOne()+
                    xyDrawdataListFree.get(i).getDomainTwo()+xyDrawdataListFree.get(i).getDomainThree()+
                    xyDrawdataListFree.get(i).getDomainFour()+"\n");
        }
        System.out.println(dnaSequences);
        return dnaSequences;
    }

    public static Container contentPane;

}
