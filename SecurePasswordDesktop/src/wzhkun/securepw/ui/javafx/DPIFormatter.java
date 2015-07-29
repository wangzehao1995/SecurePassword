package wzhkun.securepw.ui.javafx;

import java.awt.Toolkit;

public class DPIFormatter {
	public static double cmToPx(double cm){
		final double cmToIn=0.3937008;
		int dpi = Toolkit.getDefaultToolkit().getScreenResolution();
		return cm*cmToIn*dpi;
	}
}
