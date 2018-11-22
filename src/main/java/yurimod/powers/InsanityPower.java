package yurimod.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.*;

import yurimod.yuriMod;


public class InsanityPower extends AbstractPower {
	public AbstractCreature source;
	
	public static final String POWER_ID = yurimod.yuriMod.makeID("InsanityPower");
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	public static final String IMG = yuriMod.makePath(yuriMod.INSANITY);
	private int hpLoss;
	private int hpLossDes;

    public InsanityPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        this.name = NAME;
        this.ID = "InsanityPower";
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
        this.img = new Texture(IMG);
        this.source = source;
        this.canGoNegative = false;


    }

	// Increase damage the same way as strength.
    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        return type == DamageInfo.DamageType.NORMAL ? damage + (float)this.amount : damage;
    }
    // Reduces damage taken.
    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
        return type == DamageInfo.DamageType.NORMAL ? damage - (float)this.amount : damage;
    }
    // Hp loss at turn end
    @Override
    public void atEndOfTurn(final boolean isPlayer) {
        if (this.owner.isPlayer && AbstractDungeon.player.hasRelic("yuri:BloodyKnife")) {
            this.hpLoss = 0;
        } else if (this.owner.isPlayer && AbstractDungeon.player.hasRelic("yuri:yuriKnife")) {
            this.hpLoss = this.amount - 1;
        }
        else {
            this.hpLoss = this.amount;
        }
        if (yuriMod.BrutalInsanity) {
          this.hpLoss *= 2;
        }
        if (this.hpLoss > 0) {
            AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.LoseHPAction(this.owner, this.owner, this.hpLoss));
        }
    }
  // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
    @Override
    public void updateDescription() 
    {
        if (this.owner.isPlayer && AbstractDungeon.player.hasRelic("yuri:BloodyKnife")) {
            this.hpLossDes = 0;
        } else if (this.owner.isPlayer && AbstractDungeon.player.hasRelic("yuri:yuriKnife")) {
            this.hpLossDes = this.amount - 1;
        }
        else {
            this.hpLossDes = this.amount;
        }
        if (yuriMod.BrutalInsanity) {
            this.hpLossDes *= 2;
        }
        if (this.hpLossDes > 0) {
                this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2] + (this.hpLossDes) + DESCRIPTIONS[3];
            } else {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount;
            }
        }
    }


