package yurimod.powers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.*;

import yurimod.yuriMod;


public class InsanityPower extends AbstractPower implements HealthBarRenderPower {
	public AbstractCreature source;
	
	public static final String POWER_ID = yurimod.yuriMod.makeID("InsanityPower");
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	private int hpLoss;
	private int hpLossDes;
	private int hpLossBar;

    public InsanityPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        this.name = NAME;
        this.ID = "InsanityPower";
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
        this.img = ImageMaster.loadImage("yuriModResources/images/powers/Insanity.png");
        this.source = source;
        this.canGoNegative = false;
    }

    @Override
    public int getHealthBarAmount() {
        if (this.owner.isPlayer && AbstractDungeon.player.hasRelic("yuri:BloodyKnife")) {
            this.hpLossBar = 0;
        } else if (this.owner.isPlayer && AbstractDungeon.player.hasPower("PeacePower") && !AbstractDungeon.player.hasRelic("yuri:PortraitOfMarkov")) {
            this.hpLossBar = this.amount - AbstractDungeon.player.getPower("PeacePower").amount;
        }
        else {
            this.hpLossBar = this.amount;
        }
        return this.hpLossBar;
    }

    @Override
    public Color getColor(){
        return Color.valueOf("400000");
    }


	// Increase damage the same way as strength.
    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        return type == DamageInfo.DamageType.NORMAL ? damage + (float)this.amount : damage;
    }
    // Reduces damage taken.
    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
        if (yuriMod.BrutalInsanity) {
            return type == DamageInfo.DamageType.NORMAL ? damage - (float) (this.amount * 0.5) : damage;
        } else {
            return type == DamageInfo.DamageType.NORMAL ? damage - (float) this.amount : damage;
        }
    }
    // Hp loss at turn end
    @Override
    public void atEndOfTurn(final boolean isPlayer) {
        if (this.owner.isPlayer && AbstractDungeon.player.hasRelic("yuri:BloodyKnife")) {
            this.hpLoss = 0;
        } else if (this.owner.isPlayer && AbstractDungeon.player.hasPower("PeacePower") && !AbstractDungeon.player.hasRelic("yuri:PortraitOfMarkov")) {
            this.hpLoss = this.amount - AbstractDungeon.player.getPower("PeacePower").amount;
        }
        else {
            this.hpLoss = this.amount;
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
        } else if (this.owner.isPlayer && AbstractDungeon.player.hasPower("PeacePower") && !AbstractDungeon.player.hasRelic("yuri:PortraitOfMarkov")) {
            this.hpLossDes = this.amount - AbstractDungeon.player.getPower("PeacePower").amount;
        }
        else {
            this.hpLossDes = this.amount;
        }
        if (yuriMod.BrutalInsanity) {
            if (this.hpLossDes > 0) {
                this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + (MathUtils.ceil((float)this.amount / 2)) + DESCRIPTIONS[2] + (this.hpLossDes) + DESCRIPTIONS[3];
            } else {
                this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + (MathUtils.ceil((float)this.amount / 2));
            }
        } else if (this.hpLossDes > 0) {
                this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2] + (this.hpLossDes) + DESCRIPTIONS[3];
            } else {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount;
            }
        }
    }


