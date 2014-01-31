package net.fe.fightStage.anim;

import net.fe.FEResources;
import net.fe.fightStage.CombatCalculator;
import net.fe.fightStage.FightStage;
import net.fe.unit.ItemDisplay;
import net.fe.unit.Unit;
import chu.engine.Entity;
import chu.engine.anim.Renderer;

public class HUD extends Entity {
	private Unit unit;
	private int sign;
	private String hit,crit,dmg;

	public HUD(Unit u1, Unit u2, FightStage stage) {
		super(0, 0);
		this.unit = u1;
		sign = stage.isLeft(u1) ? -1 : 1;
		this.stage = stage;
		
		if (!CombatCalculator.shouldAttack(u1, u2, stage.getRange())) {
			hit = "  -";
			crit = "  -";
			dmg = "  -";
		} else {
			hit = String.format("%3d",
					Math.min(100, Math.max(u1.hit() - u2.avoid(), 0)));
			crit = String.format("%3d",
					Math.min(100, Math.max(u1.crit() - u2.dodge(), 0)));
			dmg = String.format("%3d", Math.min(100,
					Math.max(CombatCalculator.calculateBaseDamage(u1, u2), 0)));
		}

		renderDepth = FightStage.HUD_DEPTH;

		stage.addEntity(new ItemDisplay(FightStage.CENTRAL_AXIS + sign * 39
				- 37, FightStage.FLOOR + 13, u1.getWeapon(), false));
	}

	public void render() {
		// Main status
		Renderer.drawRectangle(FightStage.CENTRAL_AXIS + sign * 120,
				FightStage.FLOOR + 12, FightStage.CENTRAL_AXIS,
				FightStage.FLOOR + 56, renderDepth, FightStage.BORDER_DARK);
		Renderer.drawRectangle(FightStage.CENTRAL_AXIS + sign * 119,
				FightStage.FLOOR + 13, FightStage.CENTRAL_AXIS + sign,
				FightStage.FLOOR + 55, renderDepth, FightStage.BORDER_LIGHT);
		Renderer.drawRectangle(FightStage.CENTRAL_AXIS + sign * 118,
				FightStage.FLOOR + 14, FightStage.CENTRAL_AXIS + sign * 2,
				FightStage.FLOOR + 54, renderDepth, unit.getPartyColor().darker(0.5f));

		// Weapon
		Renderer.drawRectangle(FightStage.CENTRAL_AXIS + sign * 119,
				FightStage.FLOOR + 13, FightStage.CENTRAL_AXIS + sign,
				FightStage.FLOOR + 31, renderDepth, FightStage.BORDER_LIGHT);
		Renderer.drawRectangle(FightStage.CENTRAL_AXIS + sign * 118,
				FightStage.FLOOR + 14, FightStage.CENTRAL_AXIS + sign * 2,
				FightStage.FLOOR + 30, renderDepth, FightStage.NEUTRAL);

		// Attack Stats
		Renderer.drawRectangle(FightStage.CENTRAL_AXIS + sign * 120,
				FightStage.FLOOR + 2, FightStage.CENTRAL_AXIS + sign * 76,
				FightStage.FLOOR + 32, renderDepth, FightStage.BORDER_DARK);
		Renderer.drawRectangle(FightStage.CENTRAL_AXIS + sign * 119,
				FightStage.FLOOR + 3, FightStage.CENTRAL_AXIS + sign * 77,
				FightStage.FLOOR + 31, renderDepth, FightStage.BORDER_LIGHT);
		Renderer.drawRectangle(FightStage.CENTRAL_AXIS + sign * 118,
				FightStage.FLOOR + 4, FightStage.CENTRAL_AXIS + sign * 78,
				FightStage.FLOOR + 30, renderDepth, unit.getPartyColor());
		
		Renderer.render(FEResources.getTexture("gui_battleStats"), 0, 0, 1, 1, FightStage.CENTRAL_AXIS
				+ sign * 98 - 18, FightStage.FLOOR+5, FightStage.CENTRAL_AXIS
				+ sign * 98 - 3, FightStage.FLOOR+29, renderDepth);

		Renderer.drawString("stat_numbers", hit, FightStage.CENTRAL_AXIS + sign * 98 - 5, 
				FightStage.FLOOR + 5, renderDepth);

		Renderer.drawString("stat_numbers", dmg, FightStage.CENTRAL_AXIS + sign * 98 - 5,
				FightStage.FLOOR + 13, renderDepth);

		Renderer.drawString("stat_numbers", crit,FightStage.CENTRAL_AXIS + sign * 98 - 5, 
				FightStage.FLOOR + 21, renderDepth);

		// Name
		Renderer.drawRectangle(FightStage.CENTRAL_AXIS + sign * 120,
				FightStage.FLOOR - 99, FightStage.CENTRAL_AXIS + sign * 63,
				FightStage.FLOOR - 77, renderDepth, FightStage.BORDER_DARK);
		Renderer.drawRectangle(FightStage.CENTRAL_AXIS + sign * 120,
				FightStage.FLOOR - 98, FightStage.CENTRAL_AXIS + sign * 64,
				FightStage.FLOOR - 78, renderDepth, FightStage.BORDER_LIGHT);
		Renderer.drawRectangle(FightStage.CENTRAL_AXIS + sign * 120,
				FightStage.FLOOR - 97, FightStage.CENTRAL_AXIS + sign * 65,
				FightStage.FLOOR - 79, renderDepth, unit.getPartyColor());
		Renderer.drawString("default_med", unit.name, FightStage.CENTRAL_AXIS + sign
				* 94 - 16, 10, 0.0f);
	}
}
