package progetto.liste;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class GUI implements ActionListener{
    JFrame frame = new JFrame("Progetto-Liste");
    
    
    DefaultListModel modUno;
    DefaultListModel modDue;
    JList uno = new JList(modUno = new DefaultListModel());
    JList due = new JList(modDue = new DefaultListModel());
    JScrollPane scrollPaneUno = new JScrollPane(uno);
    JScrollPane scrollPaneDue = new JScrollPane(due);
    
    
    JPanel btnPanel = new JPanel();
    JButton copia = new JButton("Copia");
    JButton taglia = new JButton("Taglia");
    JButton delAll = new JButton("Elimina Tutto");
    
    JPanel files = new JPanel();
    JTextField target = new JTextField();
    JButton submit = new JButton("Cerca");
    
    public GUI() {
        //initModUno();
        //initModDue();

        initTarget();
        initBtnPanelButtons();
        initBtnPanel();
        initFrame();    
    }

    private void initBtnPanel(){
        btnPanel.add(copia);
        btnPanel.add(taglia);
        btnPanel.add(delAll);
        //btnPanel.setVisible(true);
    }
 
    private void initBtnPanelButtons(){
        copia.setActionCommand("copia");
        copia.addActionListener(this);
        
        taglia.setActionCommand("taglia");
        taglia.addActionListener(this);
        
        delAll.setActionCommand("del");
        delAll.addActionListener(this);
    }
    
    private void initTarget(){
        target.setActionCommand("cerca");
        target.addActionListener(this);
    }
    
    private void initFrame(){
        frame.add(target,"North");
        
        frame.add(scrollPaneUno,"West");
        frame.add(scrollPaneDue,"East");
        
        frame.add(btnPanel,"Center");
        
        frame.setSize(900, 400);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    boolean firstTime = false;
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
        
        if("cerca".equals(e.getActionCommand())){
            if(!modUno.isEmpty()){
                modUno.removeAllElements();
            }
            
            File f = new File((String)target.getText());
            ArrayList<String> names = new ArrayList<>(Arrays.asList(f.list()));
            for(String name:names){
                //System.out.println(name); //debug string :D
                modUno.addElement(name);
            }
        }
        
        if("del".equals(e.getActionCommand())){
            modUno.removeAllElements();
            modDue.removeAllElements();
            
           /*if(scrollPaneUno.is){
            
               System.out.println("Il coso e' selezionato");
           }
           else
               System.out.println("Non e' selezionato");*/
        }
    }
}
