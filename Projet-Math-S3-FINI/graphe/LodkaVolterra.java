package graphe;

import com.sun.org.apache.xpath.internal.SourceTree;
import sun.java2d.SurfaceDataProxy;

public class LodkaVolterra {

	private double m_ValMax = 0 ;
	
	// Proies
	private double m_NbProies ;
	private double m_ReprodProies ;
	private double m_MortaliteProies ;
	
	//Prédateurs
	private double m_NbPred;
	private double m_MortalitePred ; 
	private double m_ReprodPred ;

	private final double m_EchelleReprodProies = 0.0166666666666 ;
	private final double m_EchelleMortaProies = 0.000001 ;
	private final double m_EchelleReprodPred =0.00000666666666 ;
	private final double m_EchelleMortaPred = 0.05 ;
	
	public void next(int temps) {
		m_NbProies = m_NbProies + m_ReprodProies * m_NbProies - m_MortaliteProies * m_NbPred * m_NbProies ;
		if (m_NbProies > m_ValMax) {
			m_ValMax = m_NbProies ;
		}
		m_NbPred = m_NbPred + m_ReprodPred * m_NbPred * m_NbProies - m_MortalitePred * m_NbPred ;
		if (m_NbPred > m_ValMax) {
			m_ValMax = m_NbPred ;
		}
	}

	private void Echelle() {
		//53000, 0.07, 0.00001, 9000, 0.000006, 0.25
		m_ReprodProies = m_ReprodProies * m_EchelleReprodProies ;
		m_MortaliteProies = m_MortaliteProies * m_EchelleMortaProies ;
		m_ReprodPred = m_ReprodPred * m_EchelleMortaPred ;
		m_ReprodPred = m_ReprodPred * m_EchelleReprodProies ;
	}

	public LodkaVolterra(double NbProies, double ReprodProies, double MortaliteProies,
				double NbPred,double ReprodPred,  double MortalitePred) {
		m_NbProies = NbProies ;
		m_ReprodProies = ReprodProies ;
		m_MortaliteProies = MortaliteProies ;
		m_NbPred = NbPred ;
		m_ReprodPred = ReprodPred ;
		m_MortalitePred = MortalitePred ;
	}

	public LodkaVolterra(double NbProies, double FreqReprodProies, double TauxReprodProies, double MortaProies,
						 double NbPred, double FreqReprodPred,  double TauxReprodPred, double MortaPred)  {
		m_NbProies = NbProies ;
		m_ReprodProies = (FreqReprodProies * TauxReprodProies) * m_EchelleReprodProies ;
		m_MortaliteProies = MortaProies * m_EchelleMortaProies ;
		m_NbPred = NbPred ;
		m_ReprodPred = (FreqReprodPred * TauxReprodPred) * m_EchelleReprodPred ;
		m_MortalitePred = MortaPred * m_EchelleMortaPred;
		//this.Echelle() ;
		/*System.out.println(m_ReprodProies) ;
		System.out.println(m_MortaliteProies) ;
		System.out.println(m_ReprodPred) ;
		System.out.println(m_MortalitePred) ;*/
	}
	
	public double getMax() {
		return m_ValMax ;
	}

	public double getNbProie(double Temps) {
		return m_NbProies ;
	}
	
	public double getNbPredateur(double Temps) {
		return m_NbPred ;
	}
	
	public static void main(String[] args) {
		 LodkaVolterra Test = new LodkaVolterra(53000, 1, 3, 10, 9000 ,3, 1, 5) ;
		//LodkaVolterra Test = new LodkaVolterra(53000, 0.09, 0.00001, 9000, 0.000001, 0.25) ;
	}

}