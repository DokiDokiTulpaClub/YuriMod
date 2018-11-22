package yurimod.powers;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.*;

import yurimod.cards.Glitch;
import yurimod.yuriMod;

import java.util.Iterator;


public class GlitchRemovePower extends AbstractPower {
    public AbstractCreature source;

    public static final String POWER_ID = yurimod.yuriMod.makeID("GlitchRemovePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final String IMG = yuriMod.makePath(yuriMod.GLITCH_REMOVE);

    public GlitchRemovePower(final AbstractCreature owner, final int amount) {
        this.name = NAME;
        this.ID = "GlitchRemovePower";
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
        this.img = new Texture(IMG);
        this.canGoNegative = false;


    }

    @Override
    public void atEndOfTurn(final boolean isPlayer) {
        if (isPlayer) {
            Iterator var1 = AbstractDungeon.player.hand.group.iterator();
            while (var1.hasNext()) {
                AbstractCard c = (AbstractCard) var1.next();
                if (c instanceof Glitch) {
                    AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
                }
            }
        }
    }

    @Override
    public void onExhaust(AbstractCard card) {
        if (card instanceof Glitch) {
            this.flash();
            AbstractDungeon.actionManager.addToTop(new GainBlockAction(this.owner, this.owner, this.amount * 3));
        }
    }
        // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
        @Override
        public void updateDescription () {
            this.description = DESCRIPTIONS[0] + (this.amount * 3) + DESCRIPTIONS[1];
        }
    }


