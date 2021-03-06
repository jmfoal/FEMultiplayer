package net.fe.fightStage;

import net.fe.RNG;
import net.fe.unit.Unit;

public class Deadeye extends CombatTrigger {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5208500314150806972L;
	public Deadeye(){
		super(REPLACE_NAME_AFTER_PRE, YOUR_TURN_PRE, "deadeye");
	}
	@Override
	public boolean runPreAttack(CombatCalculator stage, Unit a, Unit d) {
		a.setTempMod("Crit", 9000);
		a.setTempMod("Hit", 9000);
		return true;
	}
	@Override
	public boolean attempt(Unit user, int range) {
		return RNG.get() < user.get("Skl");
	}

	public CombatTrigger getCopy(){
		return new Deadeye();
	}
}
