package yurimod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;

import yurimod.yuriMod;
import yurimod.patches.AbstractCardEnum;

public class HeavierStrike
extends CustomCard {
	
/*
 * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
 * 
 * Big Slap
 * Deal 10(15)) damage.
 */
	
	
// TEXT DECLARATION 
	
	public static final String ID = yurimod.yuriMod.makeID("HeavierStrike");
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String IMG = yuriMod.makePath(yuriMod.yuri_HEAVIER_STRIKE);

	public static final	String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

// /TEXT DECLARATION/

// STAT DECLARATION 	
	
	private static final CardRarity RARITY = CardRarity.COMMON;
	private static final CardTarget TARGET = CardTarget.ENEMY;
	private static final CardType TYPE = CardType.ATTACK;
	public static final CardColor COLOR = AbstractCardEnum.YURI_PURPLE;

	private static final int COST = 2;
	private static final int DAMAGE = 15;
	private static final int UPGRADE_PLUS_DMG = 5;
	private static final int MAGIC = 3;
	private static final int UPGRADE_MAGIC = 1;

	
// /STAT DECLARATION/
	
	public HeavierStrike() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,COLOR,RARITY,TARGET);
		this.baseDamage = DAMAGE;
		this.baseMagicNumber = this.magicNumber = MAGIC;
		this.tags.add(CardTags.STRIKE);
	}

	@Override
	public void calculateCardDamage(AbstractMonster mo){
		if (AbstractDungeon.player.hasPower("InsanityPower")){
            baseDamage += (AbstractDungeon.player.getPower("InsanityPower").amount) * (this.magicNumber - 1);
            this.isDamageModified = true;
        }
        super.calculateCardDamage(mo);
		this.baseDamage = DAMAGE;
		if (this.upgraded){
			this.baseDamage += UPGRADE_PLUS_DMG;
		}
	}
	
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
			AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
					new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));

		}

	// Which card to return when making a copy of this card.
	@Override
	public AbstractCard makeCopy() {
		return new HeavierStrike();
	}
	
    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
            this.upgradeMagicNumber(UPGRADE_MAGIC);
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
}