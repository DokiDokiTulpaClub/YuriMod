package yurimod.cards;

import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import yurimod.actions.CorruptAction;
import yurimod.patches.AbstractCardEnum;
import yurimod.powers.PeacePower;
import yurimod.yuriMod;

public class Happy
extends CustomCard {

/*
 * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
 *
 * Defend
 * Gain 5 (8) block.
 */


// TEXT DECLARATION

	public static final String ID = yuriMod.makeID("Happy");
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String IMG = yuriMod.makePath(yuriMod.yuri_HAPPY);

	public static final	String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

// /TEXT DECLARATION/


// STAT DECLARATION

	private static final CardRarity RARITY = CardRarity.COMMON;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.SKILL;
	public static final CardColor COLOR = AbstractCardEnum.YURI_PURPLE;

	private static final int COST = 1;
	private static final int UPGRADE_COST = 0;
	private static final int MAGIC = 1;



// /STAT DECLARATION/

	public Happy() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,COLOR,RARITY,TARGET);
		this.baseMagicNumber = magicNumber = MAGIC;
	}
	
	// Actions the card should do.
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
				addToBot(new ApplyPowerAction(p, p, new PeacePower(p, this.magicNumber), this.magicNumber));

		if (AbstractDungeon.player.hasRelic("yuri:BloodyKnife") && !this.purgeOnUse) {
			AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new HappyCorrupt(), 1));
			FleetingField.fleeting.set(this, true);
		}
	}
	
	// Which card to return when making a copy of this card.
    @Override
    public AbstractCard makeCopy() {
        return new Happy();
    }
    
    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
			this.upgradeBaseCost(UPGRADE_COST);
            this.initializeDescription();
        }
    }
}