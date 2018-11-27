package yurimod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.EscapeAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MinionPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import basemod.abstracts.CustomCard;

import com.megacrit.cardcrawl.powers.WeakPower;
import yurimod.actions.ScareAction;
import yurimod.yuriMod;
import yurimod.patches.AbstractCardEnum;

public class JumpScare
extends CustomCard {
	
/*
 * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
 * 
 * for each loop x2"
 * "Apply 1 Vulnerable to all enemies, 2(3) times.
 */
		
	
// TEXT DECLARATION 
	
	public static final String ID = yurimod.yuriMod.makeID("JumpScare");
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	
	public static final	String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String IMG = yuriMod.makePath(yuriMod.yuri_SCARE);

// STAT DECLARATION 	
	
	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.ENEMY;
	private static final CardType TYPE = CardType.SKILL;
	public static final CardColor COLOR = AbstractCardEnum.YURI_PURPLE;

	private static final int COST = 5;
	private static final int UPGRADE_COST = 4;


// /STAT DECLARATION/
	
	public JumpScare() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,COLOR,RARITY,TARGET);
		this.exhaust = true;
	}
	
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		if (AbstractDungeon.getCurrRoom().monsters.monsters.size() > 1) {
			int nomin = 0;
			for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
				if (!mo.hasPower("Minion")) {
				nomin++;
				}
			}
			if (nomin > 1){
				AbstractDungeon.actionManager.addToBottom(new EscapeAction(m));
				AbstractDungeon.actionManager.addToBottom(new ScareAction(m));
			} else if (nomin == 1 && m.hasPower("Minion")){
				AbstractDungeon.actionManager.addToBottom(new EscapeAction(m));
                AbstractDungeon.actionManager.addToBottom(new ScareAction(m));
			}
		}
    }

    
	// Which card to return when making a copy of this card.
    @Override
    public AbstractCard makeCopy() {
        return new JumpScare();
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