package yurimod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import yurimod.patches.AbstractCardEnum;
import yurimod.powers.PeacePower;
import yurimod.yuriMod;

import java.util.ArrayList;
import java.util.Iterator;

public class HappyCorrupt
extends CustomCard {

/*
 * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
 *
 * Defend
 * Gain 5 (8) block.
 */


// TEXT DECLARATION

	public static final String ID = yuriMod.makeID("HappyCorrupt");
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String IMG = yuriMod.makePath(yuriMod.yuri_HAPPY_CORRUPT);

	public static final	String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

// /TEXT DECLARATION/


// STAT DECLARATION

	private static final CardRarity RARITY = CardRarity.SPECIAL;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.SKILL;
	public static final CardColor COLOR = AbstractCardEnum.YURI_RED;

	private static final int COST = 0;
	private boolean cardsSelected = true;
	private static final int MAGIC = 6;



// /STAT DECLARATION/

	public HappyCorrupt() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,COLOR,RARITY,TARGET);
		this.baseMagicNumber = magicNumber = MAGIC;
		this.purgeOnUse = true;
	}
	
	// Actions the card should do.
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.player.decreaseMaxHealth(this.magicNumber);
		this.cardsSelected = false;
		if (AbstractDungeon.isScreenUp) {
			AbstractDungeon.dynamicBanner.hide();
			AbstractDungeon.overlayMenu.cancelButton.hide();
			AbstractDungeon.previousScreen = AbstractDungeon.screen;
		}

		CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
		Iterator var2 = AbstractDungeon.player.masterDeck.getPurgeableCards().group.iterator();

		while(var2.hasNext()) {
			AbstractCard card = (AbstractCard)var2.next();
			tmp.addToTop(card);
		}

		if (tmp.group.isEmpty()) {
			this.cardsSelected = true;
		} else {
			if (tmp.group.size() <= 1) {
				this.deleteCards(tmp.group);
			} else {
				AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.masterDeck.getPurgeableCards(), 1, UPGRADE_DESCRIPTION, false, false, false, true);
			}

		}
	}
	public void update() {
		super.update();
		if (!this.cardsSelected && AbstractDungeon.gridSelectScreen.selectedCards.size() == 1) {
			this.deleteCards(AbstractDungeon.gridSelectScreen.selectedCards);
		}

	}

	public void deleteCards(ArrayList<AbstractCard> group) {
		this.cardsSelected = true;
		float displayCount = 0.0F;
		Iterator i = group.iterator();

		while(i.hasNext()) {
			AbstractCard card = (AbstractCard)i.next();
			card.untip();
			card.unhover();
			AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(card, (float) Settings.WIDTH / 3.0F + displayCount, (float)Settings.HEIGHT / 2.0F));
			displayCount += (float)Settings.WIDTH / 6.0F;
			AbstractDungeon.player.masterDeck.removeCard(card);
		}

		AbstractDungeon.gridSelectScreen.selectedCards.clear();
	}
	
	// Which card to return when making a copy of this card.
    @Override
    public AbstractCard makeCopy() {
        return new HappyCorrupt();
    }
    
    //Upgraded stats.
    @Override
    public void upgrade() {
	}
}