package com.cv.parser;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.SwingConstants;

public class About extends JPanel {

	/**
	 * Create the panel.
	 */
	public About() {
		
		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(47)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 506, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(51, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(58)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(68, Short.MAX_VALUE))
		);
		panel.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel aboutTitleLabel = new JLabel("About CanadaCitizenshipQuiz");
		aboutTitleLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		aboutTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(aboutTitleLabel);
		
		JLabel aboutDescLabel = new JLabel("@Copyright 2017-2018. All rights reserved. Leo Sudarma and Raymart De Guzman");
		aboutDescLabel.setVerticalAlignment(SwingConstants.TOP);
		aboutDescLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(aboutDescLabel);
		setLayout(groupLayout);

	}
}
