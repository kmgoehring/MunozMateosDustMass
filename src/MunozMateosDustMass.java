import java.text.DecimalFormat;
import javax.swing.JOptionPane;

/**
 * 
 * @author Kevin M Goehring
 *	A brief class/UI that runs the Munoz-Mateos empirical mathematical calibrations of the Draine/Li 07
 *  dust model as outlined in "Draine, B. T., et al. 2007, ApJ, 663, 866". The related paper that 
 *  defines these calculations is "Munoz-Mateos, et al. 2009, ApJ, 701, 1965"
 */

public class MunozMateosDustMass {

	public static void main(String[] args) {

		// Variables for user input flux.
		// 3.6, 7.9, 24, 71, and 160 micron fluxes, in janskys. 
		String 
		flux3_6, 
		flux7_9, 
		flux24, 
		flux71, 
		flux160; 

		/**
		 * Prompts user for various inputs needed for model calculations. 
		 */
		
		String DISTANCE = JOptionPane
				.showInputDialog("Enter the distance to your object in megaparsecs");

		flux3_6 = JOptionPane
				.showInputDialog("Enter 3.6 micron flux (in janskys) for your object (Enter '-1' if no flux)");

		flux7_9 = JOptionPane
				.showInputDialog("Enter 7.9 micron flux (in janskys) for your object (Enter '-1' if no flux))");

		flux24 = JOptionPane
				.showInputDialog("Enter 24 micron flux (in janskys) for your object");
		
		flux71 = JOptionPane
				.showInputDialog("Enter 71 micron flux (in janskys) for your object");
		
		flux160 = JOptionPane
				.showInputDialog("Enter 160 micron flux (in janskys) for your object");

		// Convert numbers from type String to type double

		double 
		distance = Double.parseDouble(DISTANCE),
		Flux3_6 = Double.parseDouble(flux3_6),
		Flux7_9 = Double.parseDouble(flux7_9),
		Flux24 = Double.parseDouble(flux24),
		Flux71 = Double.parseDouble(flux71),
		Flux160 = Double.parseDouble(flux160);

		
		// Variables initialized for various frequency bands and non-stellar fluxes.
		// the 'x' variable values are provided by Munoz-Mateos in the related paper. 
		double
		x8 = 3.7948E13, 
		x24 = 1.2491E13, 
		x71 = 4.222E12, 
		x160 = 1.8737E12, 
		NSflux7_9 = Flux7_9 - (0.232 * Flux3_6), 
		NSflux24 = Flux24 - (0.032 * Flux3_6),

		// Various variables to simplify final equation.
		A = (4 * Math.PI) / 1.616E-13, 
		B = Math.pow(((x71 * Flux71) / (x160 * Flux160)), -1.801), 
		C = x8* NSflux7_9, 
		D = x24 * NSflux24, 
		E = x71 * Flux71, 
		F = x160 * Flux160, 
		M_Dust,
		
		// Variable for unit conversion factor with units erg^-1*cm^-2
		G = (1E-23);
		
		// Decimal format for final dust mass result. 
		DecimalFormat df = new DecimalFormat("0.00E0");

		/**
		 * Final calculations. There are 2 calibrations depending on the number of fluxes available. 
		 */
		if (Flux3_6 > 0 && Flux7_9 > 0) {
			// Final equation for dust mass using all 5 infrared wavelength bands
			M_Dust = A * distance * distance * B
					* (0.95 * C + 1.150 * D + E + F) * G;
			JOptionPane.showMessageDialog(
					null,
					"The dust mass estimate for your object is "
							+ df.format(M_Dust) + " solar masses.", "Results",
					JOptionPane.PLAIN_MESSAGE);
		}
		if (Flux3_6 == -1 || Flux7_9 == -1) {
			// Final equation for dust mass using 24, 71, 160 micron bands only
			M_Dust = A * distance * distance * B
					* (1.559 * D + 0.7686 * E + 1.347 * F) * G;
			JOptionPane.showMessageDialog(
					null,
					"The dust mass estimate for your object is "
							+ df.format(M_Dust) + " solar masses.", "Results",
					JOptionPane.PLAIN_MESSAGE);
		}
	}

}

