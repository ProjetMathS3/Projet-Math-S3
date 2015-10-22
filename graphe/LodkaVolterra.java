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

	public LodkaVolterra(double NbProies, double ReprodProies, double MortaliteProies,
				double NbPred,double ReprodPred,  double MortalitePred) {
		m_NbProies = NbProies ;
		m_ReprodProies = ReprodProies ;
		m_MortaliteProies = MortaliteProies ;
		m_NbPred = NbPred ;
		m_ReprodPred = ReprodPred ;
		m_MortalitePred = MortalitePred ;
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
		 LodkaVolterra Test = new LodkaVolterra(53000, 0.09, 0.00001, 9000, 0.000001, 0.25) ;
	}

}