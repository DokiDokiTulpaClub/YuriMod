package yurimod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.ThrowDaggerEffect;

public class ThrowingKnivesAction extends AbstractGameAction {
    private DamageInfo info;
    private static final float DURATION = 0.01F;
    private static final float POST_ATTACK_WAIT_DUR = 0.2F;
    private int numTimes;


	public ThrowingKnivesAction(AbstractCreature target, DamageInfo info, int numTimes) {
		this.info = info;
		this.target = target;
		this.actionType = ActionType.DAMAGE;
		this.attackEffect = AttackEffect.NONE;
		this.duration = 0.01F;
		this.numTimes = numTimes;
	}

	public void update() {
		if (this.target == null) {
			this.isDone = true;
		} else if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
			AbstractDungeon.actionManager.clearPostCombatActions();
			this.isDone = true;
		} else {
			if (this.target.currentHealth > 0) {
				AbstractDungeon.effectList.add(new ThrowDaggerEffect(this.target.hb.cX, this.target.hb.cY));
				this.info.applyPowers(this.info.owner, this.target);
				this.target.damage(this.info);
				if (this.numTimes > 1 && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
					--this.numTimes;
					AbstractDungeon.actionManager.addToTop(new ThrowingKnivesAction(AbstractDungeon.getMonsters().getRandomMonster(true), this.info, this.numTimes));
				}
                AbstractDungeon.actionManager.addToTop(new WaitAction(0.2F));
			}
			this.isDone = true;
		}
	}
}