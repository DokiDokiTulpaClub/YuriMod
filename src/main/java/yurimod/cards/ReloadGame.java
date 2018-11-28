package yurimod.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;

import yurimod.actions.CorruptAction;
import yurimod.powers.InsanityPower;
import yurimod.powers.NextTurnInsanityPower;
import yurimod.yuriMod;
import yurimod.patches.AbstractCardEnum;

public class ReloadGame
extends CustomCard {
	
/*
 * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
 * 
 * Defend
 * Gain 5 (8) block.
 */
	
	
// TEXT DECLARATION 
	
	public static final String ID = yurimod.yuriMod.makeID("ReloadGame");
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String IMG = yuriMod.makePath(yuriMod.yuri_RELOAD);
	
	public static final	String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

// /TEXT DECLARATION/
	

// STAT DECLARATION 	
	
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.SKILL;
	public static final CardColor COLOR = AbstractCardEnum.YURI_PURPLE;

	private static final int COST = 0;

	
// /STAT DECLARATION/
	
	public ReloadGame() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,COLOR,RARITY,TARGET);
	}
	
	// Actions the card should do.
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
			AbstractDungeon.actionManager
					.addToBottom(new com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction(p, p, "InsanityPower"));
			if (p.hasRelic("yuri:yuriKnife")) {
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new InsanityPower(p, p, 1), 1));
			}
			if (p.hasRelic("yuri:GlassOfWine")) {
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new InsanityPower(p, p, 3), 3));
			}
			if (this.upgraded) {
				AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 1));
			}
			if (AbstractDungeon.player.hasRelic("yuri:BloodyKnife") && !this.purgeOnUse) {
				AbstractDungeon.actionManager.addToBottom(new CorruptAction(this, new ReloadGameCorrupt()));
				FleetingField.fleeting.set(this, true);
			}
		}
	
	// Which card to return when making a copy of this card.
    @Override
    public AbstractCard makeCopy() {
        return new ReloadGame();
    }
    
    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
}