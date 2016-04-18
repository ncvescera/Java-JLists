package progetto.liste;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

public class GUI implements ActionListener{
    JFrame frame = new JFrame("Progetto-Liste");
    
    DefaultListModel modUno;
    DefaultListModel modDue;
    JList uno = new JList(modUno = new DefaultListModel());
    JList due = new JList(modDue = new DefaultListModel());
    
    JPanel btnPanel = new JPanel();
    JButton copia = new JButton("Copia");
    JButton taglia = new JButton("Taglia");
    
    public GUI() {
        initModUno();
        initModDue();
        initCopia();
        initTaglia();
        initBtnPanel();
        initFrame();    
    }

    private void initModUno(){
        modUno.addElement("uno");
        modUno.addElement("due");
        modUno.addElement("tre");
    }
    
    private void initModDue(){
        modDue.addElement("VUOTO");
    }
    
    private void initBtnPanel(){
        btnPanel.add(copia);
        btnPanel.add(taglia);
        //btnPanel.setVisible(true);
    }
    
    private void initCopia(){
        copia.setActionCommand("copia");
        copia.addActionListener(this);
    }
    private void initTaglia(){
        taglia.setActionCommand("taglia");
        taglia.addActionListener(this);
    }
    
    private void initFrame(){
        frame.add(uno,"West");
        frame.add(due,"East");
        frame.add(btnPanel,"Center");
        
        frame.setSize(300, 200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    boolean firstTime = true;
    @Override
    public void actionPerformed(ActionEvent e) {
        int i,j;
        String selected;
        if("copia".equals(e.getActionCommand())){
            selected = (String)uno.getSelectedValue();
            j = 0;
            for(i=0;i<modDue.size();i++){
                //System.out.println(modDue.size());
                if(((String)modDue.get(i)).contains(selected)){
                    j++;
                }
            }
            
            if(firstTime){
               modDue.remove(0);
               firstTime = false;
            }
            
            if(j>0)
                selected = (selected +"("+String.valueOf(j)+")");
            modDue.addElement(selected);
        }
        
        if("taglia".equals(e.getActionCommand())){
            selected = (String)uno.getSelectedValue();
            j = 0;
            for(i=0;i<modDue.size();i++){
                //System.out.println(modDue.size());
                if(((String)modDue.get(i)).contains(selected)){
                    j++;
                }
            }
            
            if(firstTime){
               modDue.remove(0);
               firstTime = false;
            }
            
            if(j>0)
                selected = (selected +"("+String.valueOf(j)+")");
            modDue.addElement(selected);
            modUno.removeElement(uno.getSelectedValue());
        }
    }
}
