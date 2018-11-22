package yurimod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.ui.panels.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.actions.common.*;

import yurimod.powers.CommonPower;

public class InvasionAction extends AbstractGameAction {
	public int[] multiDamage;
	private boolean freeToPlayOnce;
	private AbstractPlayer p;
	private DamageInfo.DamageType damageType;
	private int energyOnUse;

	public InvasionAction(final AbstractPlayer p, int[] multiDamage, DamageInfo.DamageType damageTypeForTurn, final boolean freeToPlayOnce,
			final int energyOnUse)

	{
		this.freeToPlayOnce = false;
		this.energyOnUse = -1;
		this.p = p;
		this.freeToPlayOnce = freeToPlayOnce;
		this.duration = Settings.ACTION_DUR_XFAST;
		this.actionType = ActionType.SPECIAL;
		this.energyOnUse = energyOnUse;
		this.damageType = damageTypeForTurn;
		this.multiDamage = multiDamage;
	}

	@Override
	public void update() {
		int effect = EnergyPanel.totalCount;
		if (this.energyOnUse != -1) {
			effect = this.energyOnUse;
		}
		if (this.p.hasRelic("Chemical X")) {
			effect += 2;
			this.p.getRelic("Chemical X").flash();
		}
		if (effect > 0) {
			for (int i = 0; i < effect; ++i) {

				AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(this.p, this.multiDamage, this.damageType, AttackEffect.BLUNT_LIGHT, true));

			}
			if (!this.freeToPlayOnce) {
				this.p.energy.use(EnergyPanel.totalCount);
			}
		}
		this.isDone = true;
	}
}
