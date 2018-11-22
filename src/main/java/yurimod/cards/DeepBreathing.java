package yurimod.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;

import yurimod.actions.CorruptAction;
import yurimod.powers.NextTurnInsanityPower;
import yurimod.yuriMod;
import yurimod.patches.AbstractCardEnum;

public class DeepBreathing
extends CustomCard {
	
/*
 * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
 * 
 * Defend
 * Gain 5 (8) block.
 */
	
	
// TEXT DECLARATION 
	
	public static final String ID = yurimod.yuriMod.makeID("DeepBreathing");
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String IMG = yuriMod.makePath(yuriMod.yuri_DEEP_BREATH);
	
	public static final	String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

// /TEXT DECLARATION/
	

// STAT DECLARATION 	
	
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.SKILL;
	public static final CardColor COLOR = AbstractCardEnum.YURI_PURPLE;

	private static final int COST = 1;
	private static final int UPGRADE_COST = 0;

	
// /STAT DECLARATION/
	
	public DeepBreathing() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,COLOR,RARITY,TARGET);
	}
	
	// Actions the card should do.
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		if (p.hasPower("InsanityPower")) {
			int insane = (AbstractDungeon.player.getPower("InsanityPower").amount);
			AbstractDungeon.actionManager
					.addToBottom(new com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction(p, p, "InsanityPower"));
			AbstractDungeon.actionManager
					.addToBottom(new ApplyPowerAction(p, p, new NextTurnInsanityPower(p, p, insane), insane));
			if (AbstractDungeon.player.hasRelic("yuri:BloodyKnife") && !this.purgeOnUse) {
				AbstractDungeon.actionManager.addToBottom(new CorruptAction(this, new DeepBreathingCorrupt()));
				FleetingField.fleeting.set(this, true);
			}
		}
	}
	
	// Which card to return when making a copy of this card.
    @Override
    public AbstractCard makeCopy() {
        return new DeepBreathing();
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