package yurimod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;

import yurimod.actions.IncreaseCostAction;
import yurimod.powers.GlitchedPower;
import yurimod.yuriMod;
import yurimod.patches.AbstractCardEnum;

public class UnstableStrike
extends CustomCard {

	/*
	 * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
	 *
	 * Big Slap
	 * Deal 10(15)) damage.
	 */


// TEXT DECLARATION 

	public static final String ID = yurimod.yuriMod.makeID("UnstableStrike");
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String IMG = yuriMod.makePath(yuriMod.yuri_UNSTABLE_STRIKE);

	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

// /TEXT DECLARATION/

// STAT DECLARATION 	

	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.ENEMY;
	private static final CardType TYPE = CardType.ATTACK;
	public static final CardColor COLOR = AbstractCardEnum.YURI_PURPLE;

	private static final int COST = 1;
	private static final int DAMAGE = 13;
	private static final int UPGRADE_PLUS_DMG = 4;
	private static final int MAGIC = 2;
	private static final int UPGRADE_MAGIC = 1;


// /STAT DECLARATION/

	public UnstableStrike() {
		super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
		this.baseDamage = DAMAGE;
		this.baseMagicNumber = magicNumber = MAGIC;
		this.tags.add(CardTags.STRIKE);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
                new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new GlitchedPower(m, p, this.magicNumber), this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new IncreaseCostAction(this, 1));
    }

	// Which card to return when making a copy of this card.
	@Override
	public AbstractCard makeCopy() {
		return new UnstableStrike();
	}
	
    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
            this.upgradeMagicNumber(UPGRADE_MAGIC);
            this.initializeDescription();
        }
    }
}