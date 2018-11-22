package yurimod.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.*;

import yurimod.yuriMod;

import static com.megacrit.cardcrawl.cards.red.PerfectedStrike.isStrike;

//Gain 1 dex for the turn for each card played.

public class RelentlessStrikePower extends AbstractPower {
	
	public static final String POWER_ID = yurimod.yuriMod.makeID("RelentlessStrikePower");
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	public static final String IMG = yuriMod.makePath(yuriMod.RELENTLESS_STRIKE_POWER);

    public RelentlessStrikePower(final AbstractCreature owner, final int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
        this.img = new Texture(IMG);

        
    }

    @Override
    public void onUseCard(final AbstractCard card, final UseCardAction action) {
		if(isStrike(card)){
			AbstractDungeon.actionManager.addToBottom(new DrawCardAction(this.owner, this.amount));
		}
    }

    
  // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
    @Override
    public void updateDescription() 
    {
    	if (this.amount == 1){
    		this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];}
    	
    	else if (this.amount > 1) {
    		this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];}
    	}
    

}


