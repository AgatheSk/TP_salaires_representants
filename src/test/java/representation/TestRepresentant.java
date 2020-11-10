package representation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestRepresentant {
	// Quelques constantes
	private static final float FIXE_BASTIDE = 1000f;
	private static final float INDEMNITE_OCCITANIE = 200f;
	
	private Representant r; // L'objet à tester
	private ZoneGeographique occitanie;
	
	@BeforeEach
	public void setUp() {
		// Initialiser les objets utilisés dans les tests
		occitanie = new ZoneGeographique(1, "Occitanie");
		occitanie.setIndemniteRepas(INDEMNITE_OCCITANIE);

		r = new Representant(36, "Bastide", "Rémi", occitanie);	
		r.setSalaireFixe(FIXE_BASTIDE);				
	}
	
        
        // TEST SALAIREMENSUEL()
	@Test
	public void testSalaireMensuel() {
		float CA = 50000f;
		float POURCENTAGE= 0.1f; // 10% de pourcentage sur CA
		// On enregistre un CA pour le mois 0 (janvier)
		r.enregistrerCA(0, CA);
		
		// On calcule son salaire pour le mois 0 avec 10% de part sur CA
		float salaire = r.salaireMensuel(0, POURCENTAGE);
		
		// A quel résultat on s'attend ?
		
		assertEquals(// Comparaison de "float"
			// valeur attendue
			FIXE_BASTIDE + INDEMNITE_OCCITANIE + CA * POURCENTAGE,
			// Valeur calculée
			salaire,
			// Marge d'erreur tolérée
			0.001,
			// Message si erreur
			"Le salaire mensuel est incorrect"
		); 
	}
        
        @Test
        public void testSalaireMensuelmois(){
		try {
			// On enregistre le mois 12
			// On s'attend à recevoir une exception
			r.salaireMensuel(12, FIXE_BASTIDE);
			// Si on arrive ici, c'est une erreur, le test doit échouer
			fail("Le mois 12 doit lever une exception"); // Forcer l'échec du test			
		} catch (IllegalArgumentException e) {
			// Si on arrive ici, c'est normal, c'est le comportement attendu
		}
        }
        
        @Test
	public void testPourcentageNegatifImpossible() {
		
		try {
                        float POURCENTAGE= -0.1f;
			// On enregistre un pourcentage négatif
			// On s'attend à recevoir une exception
			r.salaireMensuel(0, POURCENTAGE);
			// Si on arrive ici, c'est une erreur, le test doit échouer
			fail("Un pourcentage négatif doit générer une exception"); // Forcer l'échec du test			
		} catch (IllegalArgumentException e) {
			// Si on arrive ici, c'est normal, c'est le comportement attendu
		}

	}

        // TEST CAMENSUEL
	@Test
	public void testCAParDefaut() {
		float POURCENTAGE= 0.1f; // 10% de pourcentage sur CA
		
		// On n'enregistre aucun CA
		//r.enregistrerCA(0, 10000f);
		
		// On calcule son salaire pour le mois 0 avec 10% de part sur CA
		float salaire = r.salaireMensuel(0, POURCENTAGE);
		
		// A quel résultat on s'attend ?
		// Le CA du mois doit avoir été initialisé à 0
		
		assertEquals(
			FIXE_BASTIDE + INDEMNITE_OCCITANIE, 
			salaire, 
			0.001,
			"Le CA n'est pas correctement initialisé"
		);
	}
        
        // TESTS ENREGISTRER()
        
        @Test
        public void enregistrer(){
            float CA = 50000f;
            //On enregistre un CA pour le mois zero
            r.enregistrerCA(0, CA);
            assertEquals(CA, r.getCAMensuel()[0], "Le CA enregistré n'est pas le bon");
            
        }
        
        @Test
        public void enregistrermois(){
		try {
			// On enregistre le mois -1
			// On s'attend à recevoir une exception
			r.enregistrerCA(-1, 5000f);
			// Si on arrive ici, c'est une erreur, le test doit échouer
			fail("Un mois négatif doit lever une exception"); // Forcer l'échec du test			
		} catch (IllegalArgumentException e) {
			// Si on arrive ici, c'est normal, c'est le comportement attendu
		}
        }
        
        @Test
	public void testCANegatifImpossible() {
		
		try {
			// On enregistre un CA négatif, que doit-il se passer ?
			// On s'attend à recevoir une exception
			r.enregistrerCA(0, -10000f);
			// Si on arrive ici, c'est une erreur, le test doit échouer
			fail("Un CA négatif doit générer une exception"); // Forcer l'échec du test			
		} catch (IllegalArgumentException e) {
			// Si on arrive ici, c'est normal, c'est le comportement attendu
		}

	}
        
        // TEST SETCAMENSUEL
        
        @Test
	public void testCAMENSUEL13() {
		try {
                        float[] CA13 = new float[13];
			// On enregistre treize mois de CA
			// On s'attend à recevoir une exception
			r.setCAMensuel(CA13);
			// Si on arrive ici, c'est une erreur, le test doit échouer
			fail("On ne peut pas enregistrer plus de 12 valeurs dans CAMensuel"); // Forcer l'échec du test			
		} catch (IllegalArgumentException e) {
			// Si on arrive ici, c'est normal, c'est le comportement attendu
		}

	}
        
        @Test
        public void testgetCAMENSUEL() {
                float[] CA2 = {5000f,6000f};
	        r.setCAMensuel(CA2);
                assertEquals(CA2, r.getCAMensuel(), "Le CA retourné n'est pas le bon.");
	}
        
        //Test pas d'adresse
        @Test
        public void testadressenulle(){
           assertTrue(r.getAdresse().isEmpty());
        }
        
        //TEST TOSTRING
         @Test
	public void testToStringL() {
            assertEquals("Representant{numero=36, nom=Bastide, prenom=Rémi}", r.toString(), "Le tostring n'affiche pas le bon message");
	}
	
	
}
