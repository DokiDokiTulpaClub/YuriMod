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

public class BloodyKnife extends CustomRelic
{
	// ID, images, stats.
	public static final String ID = yurimod.yuriMod.makeID("BloodyKnife");
	public static final String IMG = yuriMod.makePath(yuriMod.yuri_BLOODY_KNIFE);
	public static final String OUTLINE = yuriMod.makePath(yuriMod.yuri_BLOODY_KNIFE_OUTLINE);

	public BloodyKnife()
    {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.SPECIAL, LandingSound.CLINK);
    }

    // Description
	@Override
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0];
	}


    // Which relic to return on making a copy of this relic.
    @Override
    public AbstractRelic makeCopy()
    {
        return new BloodyKnife();
    }
}
