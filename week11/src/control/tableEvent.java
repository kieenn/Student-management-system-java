package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static view.view.*;

public class tableEvent implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = getTableStudent().rowAtPoint(e.getPoint());

        getId().setText((String) getTableStudent().getValueAt(row, 0));
        getName1().setText((String) getTableStudent().getValueAt(row, 1));
        getAge().setText((String) getTableStudent().getValueAt(row, 2));
        getAddress().setText((String) getTableStudent().getValueAt(row, 3));
        getGPA().setText((String) getTableStudent().getValueAt(row, 4));
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
