package yurimod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.*;

import yurimod.yuriMod;


public class NextTurnInsanityPower extends AbstractPower {
	public AbstractCreature source;
	
	public static final String POWER_ID = yurimod.yuriMod.makeID("NextTurnInsanityPower");
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public NextTurnInsanityPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        this.name = NAME;
        this.ID = "NextTurnInsanityPower";
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
        this.img = ImageMaster.loadImage("yuriModResources/images/powers/InsanityRight.png");
        this.source = source;
        this.canGoNegative = true;


    }

    @Override
    public void atStartOfTurn(){
        this.flash();
        if (amount > 0) {
            AbstractDungeon.actionManager
                    .addToBottom(new ApplyPowerAction(this.owner, this.owner, new InsanityPower(this.owner, this.owner, this.amount), this.amount));
        } else {
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "InsanityPower", -this.amount));
        }
        AbstractDungeon.actionManager
                .addToBottom(new com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction(this.owner, this.owner, "NextTurnInsanityPower"));
    }

  // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
    @Override
    public void updateDescription()
    {
        if (amount > 0){
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
        } else {
            this.description = DESCRIPTIONS[1] + -this.amount + DESCRIPTIONS[2];
        }
        }
    }


