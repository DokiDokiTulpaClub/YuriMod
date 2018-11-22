package yurimod.relics;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.OnReceivePowerRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import yurimod.powers.InsanityPower;
import yurimod.yuriMod;

public class GlassOfWater extends CustomRelic implements OnReceivePowerRelic
{
	// ID, images, stats.
	public static final String ID = yurimod.yuriMod.makeID("GlassOfWater");
	public static final String IMG = yuriMod.makePath(yuriMod.yuri_WATER);
	public static final String OUTLINE = yuriMod.makePath(yuriMod.yuri_WATER_OUTLINE);

	public GlassOfWater()
    {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.UNCOMMON, LandingSound.CLINK);
    }

    // Description
	@Override
	public String getUpdatedDescription() {
            return DESCRIPTIONS[0];
	}

	//
    @Override
        public boolean onReceivePower(AbstractPower power, AbstractCreature target) {
            if (power.ID.equals("InsanityPower") && target == AbstractDungeon.player) {
                this.flash();
                AbstractDungeon.actionManager
                        .addToBottom(new com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction(AbstractDungeon.player, AbstractDungeon.player, 1));

            }
    return true;
	}

    // Which relic to return on making a copy of this relic.
    @Override
    public AbstractRelic makeCopy()
    {
        return new GlassOfWater();
    }
}
