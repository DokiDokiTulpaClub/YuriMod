package yurimod.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import yurimod.powers.InsanityPower;
import yurimod.yuriMod;
import yurimod.patches.AbstractCardEnum;

public class Suicide
extends CustomCard {
	
/*
 * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
 * 
 * Defend
 * Gain 5 (8) block.
 */
	
	
// TEXT DECLARATION 
	
	public static final String ID = yurimod.yuriMod.makeID("Suicide");
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String IMG = yuriMod.makePath(yuriMod.yuri_SUICIDE);
	
	public static final	String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

// /TEXT DECLARATION/
	

// STAT DECLARATION 	
	
	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.SKILL;
	public static final CardColor COLOR = AbstractCardEnum.YURI_PURPLE;

	private static final int COST = 3;
	private static final int UPGRADE_COST = 1;

	
// /STAT DECLARATION/
	
	public Suicide() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,COLOR,RARITY,TARGET);
        FleetingField.fleeting.set(this, true);
		this.tags.add(CardTags.HEALING);
	}
	
	// Actions the card should do.
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		if (AbstractDungeon.player.hasRelic("yuri:yuriKnife")) {
        AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(p, p, "IntangiblePlayer", 3));
		AbstractDungeon.actionManager
				.addToBottom(new com.megacrit.cardcrawl.actions.common.LoseHPAction(p, p, 25));
		AbstractDungeon.actionManager.addToBottom(new WaitAction(0.1f));
		AbstractDungeon.actionManager.addToBottom(new WaitAction(0.1f));
		AbstractDungeon.actionManager.addToBottom(new WaitAction(0.1f));
		AbstractDungeon.actionManager.addToBottom(new WaitAction(0.1f));
		AbstractDungeon.actionManager.addToBottom(new WaitAction(0.1f));
		AbstractDungeon.actionManager.addToBottom(new WaitAction(0.1f));
		AbstractDungeon.actionManager.addToBottom(new WaitAction(0.1f));
		AbstractDungeon.actionManager.addToBottom(new WaitAction(0.1f));
		AbstractDungeon.actionManager.addToBottom(new WaitAction(0.1f));
		AbstractDungeon.actionManager.addToBottom(new WaitAction(0.1f));
		AbstractDungeon.actionManager.addToBottom(new WaitAction(0.1f));
		AbstractDungeon.actionManager.addToBottom(new WaitAction(0.1f));
		AbstractDungeon.actionManager.addToBottom(new WaitAction(0.1f));
		AbstractDungeon.actionManager.addToBottom(new WaitAction(0.1f));
		AbstractDungeon.actionManager.addToBottom(new WaitAction(0.1f));
		AbstractDungeon.actionManager
				.addToBottom(new com.megacrit.cardcrawl.actions.common.LoseHPAction(p, p, 50));
		AbstractDungeon.actionManager.addToBottom(new WaitAction(0.1f));
		AbstractDungeon.actionManager.addToBottom(new WaitAction(0.1f));
		AbstractDungeon.actionManager.addToBottom(new WaitAction(0.1f));
		AbstractDungeon.actionManager.addToBottom(new WaitAction(0.1f));
		AbstractDungeon.actionManager.addToBottom(new WaitAction(0.1f));
		AbstractDungeon.actionManager.addToBottom(new WaitAction(0.1f));
		AbstractDungeon.actionManager.addToBottom(new WaitAction(0.1f));
		AbstractDungeon.actionManager.addToBottom(new WaitAction(0.1f));
		AbstractDungeon.actionManager.addToBottom(new WaitAction(0.1f));
		AbstractDungeon.actionManager.addToBottom(new WaitAction(0.1f));
		AbstractDungeon.actionManager.addToBottom(new WaitAction(0.1f));
		AbstractDungeon.actionManager.addToBottom(new WaitAction(0.1f));
		AbstractDungeon.actionManager.addToBottom(new WaitAction(0.1f));
		AbstractDungeon.actionManager.addToBottom(new WaitAction(0.1f));
		AbstractDungeon.actionManager.addToBottom(new WaitAction(0.1f));
		AbstractDungeon.actionManager
				.addToBottom(new com.megacrit.cardcrawl.actions.common.LoseHPAction(p, p, 9999));
		 int relicAtIndex = 0;

		 for(int i = 0; i < AbstractDungeon.player.relics.size(); ++i) {
			 if ((AbstractDungeon.player.relics.get(i)).relicId.equals("yuri:yuriKnife")) {
				 relicAtIndex = i;
				 break;
			 }
		 }
         (AbstractDungeon.player.relics.get(relicAtIndex)).onUnequip();
         AbstractRelic BloodyKnife = RelicLibrary.getRelic("yuri:BloodyKnife").makeCopy();
         BloodyKnife.instantObtain(AbstractDungeon.player, relicAtIndex, false);
     } else if (AbstractDungeon.player.hasRelic("yuri:BloodyKnife")) {
			AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, "...", true));
		} else {
			AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, UPGRADE_DESCRIPTION, true));
		}
	}
	
	// Which card to return when making a copy of this card.
    @Override
    public AbstractCard makeCopy() {
        return new Suicide();
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