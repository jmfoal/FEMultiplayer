package net.fe.fightStage;

import net.fe.unit.Unit;

public class Brave extends CombatTrigger{
	public Brave(){
		super(NO_NAME_MOD, YOUR_TURN_POST);
	}

	@Override
	public boolean attempt(Unit user) {
		return true;
	}

	@Override
	public void runPostAttack(FightStage stage, boolean dir, Unit a, Unit d,
			int damage, String currentEffect) {
		if(d.getHp() > 0 && currentEffect.equals("None")){
			stage.attack(dir, "Brave");
		}
	}
	

}