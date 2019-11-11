import java.awt.event.ActionEvent;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class JTextFieldActionListner {


		public static void main(String[] a) {
			JFrame frame = new JFrame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			JTextField jTextField1 = new JTextField();

			jTextField1.setText("jTextField1");
			jTextField1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("action");
					
				}
			});
			frame.add(jTextField1);

			frame.setSize(300, 200);
			frame.setVisible(true);
		}
	}

