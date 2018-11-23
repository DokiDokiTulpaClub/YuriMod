package yurimod.relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import yurimod.powers.InsanityPower;
import yurimod.yuriMod;

public class yuriKnife extends CustomRelic
{
	// ID, images, stats.
	public static final String ID = yurimod.yuriMod.makeID("yuriKnife");
	public static final String IMG = yuriMod.makePath(yuriMod.yuri_KNIFE);
	public static final String OUTLINE = yuriMod.makePath(yuriMod.yuri_KNIFE_OUTLINE);

	public yuriKnife()
    {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.STARTER, LandingSound.CLINK);
    }

    // Description
	@Override
	public String getUpdatedDescription() {
        if (yuriMod.BrutalInsanity) {
            return DESCRIPTIONS[0] + 2 + DESCRIPTIONS[1];
        } else {
            return DESCRIPTIONS[0] + 1 + DESCRIPTIONS[1];
        }
    }

	//
    @Override
    public void atBattleStart(){
	    this.flash();
	    AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new InsanityPower(AbstractDungeon.player, AbstractDungeon.player, 1), 1));
	    AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }

    // Which relic to return on making a copy of this relic.
    @Override
    public AbstractRelic makeCopy()
    {
        return new yuriKnife();
    }
}
