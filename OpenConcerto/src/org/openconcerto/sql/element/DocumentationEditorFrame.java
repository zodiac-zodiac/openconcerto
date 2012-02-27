/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 * Copyright 2011 OpenConcerto, by ILM Informatique. All rights reserved.
 * 
 * The contents of this file are subject to the terms of the GNU General Public License Version 3
 * only ("GPL"). You may not use this file except in compliance with the License. You can obtain a
 * copy of the License at http://www.gnu.org/licenses/gpl-3.0.html See the License for the specific
 * language governing permissions and limitations under the License.
 * 
 * When distributing the software, include this License Header Notice in each file.
 */
 
 package org.openconcerto.sql.element;

import org.openconcerto.ui.DefaultGridBagConstraints;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class DocumentationEditorFrame extends JFrame {

    public DocumentationEditorFrame(final GroupSQLComponent groupSQLComponent, final String id, String doc) {
        this.setTitle("Documentation");
        if (doc == null) {
            doc = "";
        }
        JPanel p = new JPanel();
        JComponent label = groupSQLComponent.createLabel(id);
        p.setLayout(new GridBagLayout());
        GridBagConstraints c = new DefaultGridBagConstraints();

        c.weightx = 1;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.NONE;
        p.add(label, c);

        // Editor
        final JTextArea txt = new JTextArea();
        txt.setFont(new JTextField().getFont());
        txt.setText(doc);
        c.weighty = 1;
        c.gridy++;
        c.fill = GridBagConstraints.BOTH;
        txt.setText(groupSQLComponent.getDocumentation(id));
        final JScrollPane comp = new JScrollPane(txt);
        comp.setMinimumSize(new Dimension(400, 300));
        comp.setPreferredSize(new Dimension(400, 300));
        p.add(comp, c);
        // Ok, Cancel
        c.gridwidth = 1;
        c.gridy++;
        c.weighty = 0;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.SOUTHEAST;
        JButton bOk = new JButton("Valider");
        c.gridx = 0;
        p.add(bOk, c);
        c.gridx++;
        c.weightx = 0;

        JButton bCancel = new JButton("Annuler");
        p.add(bCancel, c);
        this.setContentPane(p);
        // Listeners
        bOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                groupSQLComponent.saveDocumentation(id, txt.getText());
                dispose();
            }
        });
        bCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        pack();
        setLocationRelativeTo(null);
    }
}
