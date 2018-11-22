package yurimod.relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import yurimod.cards.BookBlue;
import yurimod.cards.BookGreen;
import yurimod.cards.BookRed;
import yurimod.patches.yuriModTags;
import yurimod.yuriMod;

import java.util.ArrayList;

public class BookOfBooks extends CustomRelic {
    // ID, images, stats.
    public static final String ID = yurimod.yuriMod.makeID("BookOfBooks");
    public static final String IMG = yuriMod.makePath(yuriMod.yuri_BOOK_BOOK);
    public static final String OUTLINE = yuriMod.makePath(yuriMod.yuri_BOOK_BOOK_OUTLINE);
    private static CardGroup books;
    {
    books = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        books.addToTop(new BookRed());
        books.addToTop(new BookGreen());
        books.addToTop(new BookBlue());
        for (AbstractCard c : books.group) {
            c.upgrade();
    }
    }
    private static ArrayList<AbstractCard> getBooks() {
        ArrayList<AbstractCard> retVal = new ArrayList<>();
                AbstractCard cr = new BookRed();
        AbstractCard cg = new BookGreen();
        AbstractCard cb = new BookBlue();
        cr.upgrade();
        cg.upgrade();
        cb.upgrade();
        retVal.add(cr);
        retVal.add(cg);
        retVal.add(cb);
        return retVal;
    }


    public BookOfBooks() {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.BOSS, LandingSound.FLAT);
    }

    // Flash at the start of Battle.

    // Gain 1 energy on equip.
    @Override
    public void onEquip() {
        AbstractDungeon.cardRewardScreen.open(getBooks(), null, "Choose a book");
   }



    @Override
   public void onUseCard(AbstractCard card, UseCardAction action) {
       if (!card.purgeOnUse && card.hasTag(yuriModTags.BOOK)) {
           this.flash();
           AbstractMonster m = null;
           if (action.target != null) {
               m = (AbstractMonster)action.target;
           }

           AbstractCard tmp = card.makeSameInstanceOf();
           AbstractDungeon.player.limbo.addToBottom(tmp);
           tmp.current_x = card.current_x;
           tmp.current_y = card.current_y;
           tmp.target_x = (float)Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
           tmp.target_y = (float)Settings.HEIGHT / 2.0F;
           tmp.freeToPlayOnce = true;
           if (m != null) {
               tmp.calculateCardDamage(m);
           }

           tmp.purgeOnUse = true;
           AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(tmp, m, card.energyOnUse));
       }

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
        return new BookOfBooks();
    }
}
