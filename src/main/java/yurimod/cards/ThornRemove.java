package yurimod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;

import yurimod.powers.NoMamaSneckoPower;
import yurimod.yuriMod;
import yurimod.patches.AbstractCardEnum;

public class ThornRemove
extends CustomCard {
	
/*
 * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
 * 
 * Defend
 * Gain 5 (8) block.
 */
	
	
// TEXT DECLARATION 
	
	public static final String ID = yurimod.yuriMod.makeID("ThornRemove");
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String IMG = yuriMod.makePath(yuriMod.yuri_THORN_REMOVE);
	
	public static final	String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

// /TEXT DECLARATION/
	

// STAT DECLARATION 	
	
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.SKILL;
	public static final CardColor COLOR = AbstractCardEnum.YURI_PURPLE;

	private static final int COST = 2;
	private static final int BLOCK = 13;
	private static final int UPGRADE_PLUS_BLOCK = 5;

	
// /STAT DECLARATION/
	
	public ThornRemove() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,COLOR,RARITY,TARGET);
		this.baseBlock = BLOCK;
	}
	
	// Actions the card should do.
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager
				.addToBottom(new com.megacrit.cardcrawl.actions.common.GainBlockAction(p, p, this.block));
        for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(mo, p, "Thorns"));
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(mo, p, "Sharp Hide"));
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(mo, p, "Curl Up"));
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(mo, p, "Angry"));
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(mo, p, "Malleable"));
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(mo, p, "Flame Barrier"));
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(mo, p, "theJungle:SwingingTrapPower"));
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(mo, p, "theJungle:ObsessionPower"));
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(mo, p, "theJungle:ProtectionPower"));
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(mo, p, "theJungle:EnergeticPower"));
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(mo, p, "theJungle:TotemRevengeAttackPower"));
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(mo, p, "infinitespire:TempThorns"));
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(mo, p, "infinitespire:RealityShiftPower"));
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(mo, p, "conspire:HoldsTreasure"));
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(mo, p, "conspire:Shedding"));
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(mo, p, "conspire:ReflectAttack"));
            if (mo.id.equals("theJungle:MamaSnecko") && mo.hasPower("theJungle:MamaSneckoRevengePower")){
            	AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(mo, p, "theJungle:MamaSneckoRevengePower"));
            	AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new NoMamaSneckoPower(mo,1), 1));
			}
        }
	}
	
	// Which card to return when making a copy of this card.
    @Override
    public AbstractCard makeCopy() {
        return new ThornRemove();
    }
    
    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(UPGRADE_PLUS_BLOCK);
            this.initializeDescription();
        }
    }
}