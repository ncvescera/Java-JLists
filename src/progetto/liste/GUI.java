package progetto.liste;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI implements ActionListener{
    JFrame frame = new JFrame("Progetto-Liste");
    
    DefaultListModel modUno;
    DefaultListModel modDue;
    JList uno = new JList(modUno = new DefaultListModel());
    JList due = new JList(modDue = new DefaultListModel());
    
    JPanel btnPanel = new JPanel();
    JButton copia = new JButton("Copia");
    JButton taglia = new JButton("Taglia");
    
    JPanel files = new JPanel();
    JTextField target = new JTextField();
    JButton submit = new JButton("Cerca");
    
    public GUI() {
        //initModUno();
        initModDue();
        initCopia();
        initTarget();
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
    
    private void initTarget(){
        target.setActionCommand("cerca");
        target.addActionListener(this);
    }
    
    private void initFrame(){
        
        frame.add(target,"North");
        //frame.add(submit,"South");
        frame.add(uno,"West");
        frame.add(due,"East");
        frame.add(btnPanel,"Center");
        
        frame.setSize(400, 600);
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
        
        if("cerca".equals(e.getActionCommand())){
            if(!modUno.isEmpty()){
                modUno.removeAllElements();
            }
            
            File f = new File((String)target.getText());
            ArrayList<String> names = new ArrayList<>(Arrays.asList(f.list()));
            for(String name:names){
                System.out.println(name);
                modUno.addElement(name);
            }
        }
    }
}
