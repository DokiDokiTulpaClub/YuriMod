package yurimod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.*;

import yurimod.cards.ThrowingKnives;
import yurimod.yuriMod;


public class MagneticKnivesPower extends AbstractPower {
    public AbstractCreature source;

    public static final String POWER_ID = yurimod.yuriMod.makeID("MagneticKnivesPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public MagneticKnivesPower(final AbstractCreature owner, final int amount) {
        this.name = NAME;
        this.ID = "MagneticKnivesPower";
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
        this.img = ImageMaster.loadImage("yuriModResources/images/powers/Magnet.png");
        this.canGoNegative = false;


    }

    @Override
    public void atStartOfTurn() {
        this.flash();
        AbstractCard s = (new ThrowingKnives().makeCopy());
        s.modifyCostForCombat(-1);
        AbstractDungeon.actionManager
                .addToBottom(new MakeTempCardInHandAction(s, this.amount, false));

        }

    // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}


