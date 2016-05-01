package Main;

import Views.AlleyView;

public class drive {

	public static void main(String[] args) {

		int numLanes = 3;
		int maxPatronsPerParty=5;

		Alley alley = new Alley(numLanes);

		AlleyView cdv = new AlleyView(alley, maxPatronsPerParty);
		alley.subscribe( cdv );

	}
}
