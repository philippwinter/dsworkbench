/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tor.tribes.ui.models;

import de.tor.tribes.types.AbstractForm;
import de.tor.tribes.types.Arrow;
import de.tor.tribes.types.Circle;
import de.tor.tribes.types.Line;
import de.tor.tribes.types.Rectangle;
import de.tor.tribes.util.map.FormManager;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Torridity
 */
public class FormTableModel extends AbstractTableModel {
    
    private Class[] types = new Class[]{String.class, String.class, Integer.class, Integer.class, Integer.class, Integer.class, Boolean.class};
    private String[] colNames = new String[]{"Name", "Typ", "X", "Y", "Breite", "Höhe", "Sichtbar"};
    private boolean[] editableColumns = new boolean[]{true, false, true, true, true, true, false};
    
    @Override
    public int getColumnCount() {
        return colNames.length;
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return types[columnIndex];
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return editableColumns[columnIndex];
    }
    
    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }
    
    @Override
    public int getRowCount() {
        return FormManager.getSingleton().getAllElements().size();
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        AbstractForm f = (AbstractForm) FormManager.getSingleton().getAllElements().get(rowIndex);
        AbstractForm.FORM_TYPE type = f.getFormType();
        switch (columnIndex) {
            case 0: {
                String name = f.getFormName();
                if (name == null || name.length() == 0) {
                    return "Kein Name";
                }
                return name;
            }
            case 1: {
                switch (type) {
                    case ARROW:
                        return "Pfeil";
                    case CIRCLE:
                        return "Kreis";
                    case FREEFORM:
                        return "Freiform";
                    case LINE:
                        return "Linie";
                    case RECTANGLE:
                        return "Rechteck";
                    default:
                        return "Text";
                }
            }
            case 2:
                return Math.round(f.getXPos());
            case 3:
                return Math.round(f.getYPos());
            case 4:
                switch (type) {
                    case ARROW:
                        return Math.round(((Arrow) f).getBounds().getWidth());
                    case CIRCLE:
                        return Math.round(((Circle) f).getBounds().getWidth());
                    case FREEFORM:
                        return 0;
                    case LINE:
                        return Math.round(((Line) f).getBounds().getWidth());
                    case RECTANGLE:
                        return Math.round(((Rectangle) f).getBounds().getWidth());
                    default:
                        return 0;
                }
            case 5:
                switch (type) {
                    case ARROW:
                        return Math.round(((Arrow) f).getBounds().getHeight());
                    case CIRCLE:
                        return Math.round(((Circle) f).getBounds().getHeight());
                    case FREEFORM:
                        return 0;
                    case LINE:
                        return Math.round(((Line) f).getBounds().getHeight());
                    case RECTANGLE:
                        return Math.round(((Rectangle) f).getBounds().getHeight());
                    default:
                        return 0;
                }
            default:
                return f.isVisibleOnMap();
        }
    }
    
    @Override
    public void setValueAt(Object o, int rowIndex, int columnIndex) {
        AbstractForm f = (AbstractForm) FormManager.getSingleton().getAllElements().get(rowIndex);
        AbstractForm.FORM_TYPE type = f.getFormType();
        Integer v = null;
        try {
            v = (Integer) o;
            if (v <= 0) {
                throw new NumberFormatException();
            }
        } catch (ClassCastException cce) {
            if (columnIndex != 0) {
                return;
            }
        } catch (NumberFormatException nfe) {
            if (columnIndex != 0) {
                return;
            }
        }
        switch (columnIndex) {
            case 2:
                f.setXPos(v);
                break;
            case 3:
                f.setYPos(v);
                break;
            case 4:
                switch (type) {
                    case ARROW:
                        ((Arrow) f).setXPosEnd(f.getXPos() + v);
                        break;
                    case CIRCLE:
                        ((Circle) f).setXPosEnd(f.getXPos() + v);
                        break;
                    case FREEFORM:
                        break;
                    case LINE:
                        ((Line) f).setXPosEnd(f.getXPos() + v);
                        break;
                    case RECTANGLE:
                        ((Rectangle) f).setXPosEnd(f.getXPos() + v);
                        break;
                    default:
                        break;
                }
                break;
            case 5:
                switch (type) {
                    case ARROW:
                        ((Arrow) f).setYPosEnd(f.getYPos() + v);
                        break;
                    case CIRCLE:
                        ((Circle) f).setYPosEnd(f.getYPos() + v);
                        break;
                    case FREEFORM:
                        break;
                    case LINE:
                        ((Line) f).setYPosEnd(f.getYPos() + v);
                        break;
                    case RECTANGLE:
                        ((Rectangle) f).setYPosEnd(f.getYPos() + v);
                        break;
                    default:
                        break;
                }
                break;
            default:
                f.setFormName((String) o);
                break;
        }
        FormManager.getSingleton().revalidate(true);
    }
}