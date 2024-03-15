/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BtnAcction;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;



/**
 *
 * @author DELL
 */
public class TableActionCellEditor extends DefaultCellEditor{

    private TableActionEvent event;
    
    public TableActionCellEditor(TableActionEvent event){
        super(new JCheckBox());
        this.event = event;
    }
    
    
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        JPanelActionLamBai action = new JPanelActionLamBai();
        action.initEvent(event, row);
        action.setBackground(table.getSelectionBackground());
        return action;
    }
    
}
