package yurimod.events;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom.RoomPhase;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;


public class YuriShrine extends AbstractImageEvent {
    public static final String ID = "yuri:Yuri Shrine";
    private static final EventStrings eventStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    public static final String[] OPTIONS;
    private static final String DIALOG_1;
    private static final String DIALOG_2;
    private static final String DIALOG_3;
    private static final String IGNORE;
    private static float MAX_HP_LOSS_PERCENT = 0.4f;
    private static int MAX_HP_LOSS = 40;
    private YuriShrine.CUR_SCREEN screen;

    public YuriShrine() {
        super(NAME, DIALOG_1, "events/yurishrine.png");
        this.screen = YuriShrine.CUR_SCREEN.INTRO;
        if (AbstractDungeon.ascensionLevel >= 15){
            MAX_HP_LOSS_PERCENT = 0.5f;
            MAX_HP_LOSS = 50;
        }
        if (AbstractDungeon.player.hasRelic("yuri:yuriKnife")){
            this.imageEventText.setDialogOption(OPTIONS[5] + (MAX_HP_LOSS) + OPTIONS[6], !AbstractDungeon.player.hasRelic("yuri:yuriKnife"));
        } else {
            this.imageEventText.setDialogOption(OPTIONS[4], !AbstractDungeon.player.hasRelic("yuri:yuriKnife"));
        }
        if (AbstractDungeon.player.masterDeck.hasUpgradableCards()) {
            this.imageEventText.setDialogOption(OPTIONS[0]);
        } else {
            this.imageEventText.setDialogOption(OPTIONS[3], true);
        }

        this.imageEventText.setDialogOption(OPTIONS[1]);


    }

    public void onEnterRoom() {
        CardCrawlGame.music.playTempBGM("SHRINE");
    }

    public void update() {
        super.update();
        if (!AbstractDungeon.isScreenUp && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            AbstractCard c = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
            c.upgrade();
            AbstractDungeon.player.bottledCardUpgradeCheck(c);
            AbstractDungeon.effectsQueue.add(new ShowCardBrieflyEffect(c.makeStatEquivalentCopy()));
            AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect((float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
        }

    }

    protected void buttonEffect(int buttonPressed) {
        switch(this.screen) {
            case INTRO:
                switch(buttonPressed) {
                    case 0:
                        this.screen = YuriShrine.CUR_SCREEN.COMPLETE;
                        AbstractDungeon.getCurrRoom().phase = RoomPhase.COMPLETE;
                        this.imageEventText.updateBodyText(DIALOG_3);
                        int hpLoss = (int)((float)AbstractDungeon.player.maxHealth * (1 - MAX_HP_LOSS_PERCENT));
                        AbstractDungeon.player.maxHealth = hpLoss;
                        if (AbstractDungeon.player.maxHealth <= 0) {
                            AbstractDungeon.player.maxHealth = 1;
                        }

                        if (AbstractDungeon.player.currentHealth > AbstractDungeon.player.maxHealth) {
                            AbstractDungeon.player.currentHealth = AbstractDungeon.player.maxHealth;
                        }
                        if (AbstractDungeon.player.hasRelic("yuri:yuriKnife")) {
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
                        }
                        this.imageEventText.updateDialogOption(0, OPTIONS[1]);
                        this.imageEventText.clearRemainingOptions();
                        return;
                    case 1:
                        this.screen = YuriShrine.CUR_SCREEN.COMPLETE;
                        AbstractDungeon.getCurrRoom().phase = RoomPhase.COMPLETE;
                        this.imageEventText.updateBodyText(DIALOG_2);
                        AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.masterDeck.getUpgradableCards(), 1, OPTIONS[2], true, false, false, false);
                        this.imageEventText.updateDialogOption(0, OPTIONS[1]);
                        this.imageEventText.clearRemainingOptions();
                        return;
                    case 2:
                        this.screen = YuriShrine.CUR_SCREEN.COMPLETE;
                        AbstractDungeon.getCurrRoom().phase = RoomPhase.COMPLETE;
                        this.imageEventText.updateBodyText(IGNORE);
                        this.imageEventText.updateDialogOption(0, OPTIONS[1]);
                        this.imageEventText.clearRemainingOptions();
                        return;
                    default:
                        return;
                }
            case COMPLETE:
                this.openMap();
        }

    }

    static {
        eventStrings = CardCrawlGame.languagePack.getEventString("yuri:Yuri Shrine");
        NAME = eventStrings.NAME;
        DESCRIPTIONS = eventStrings.DESCRIPTIONS;
        OPTIONS = eventStrings.OPTIONS;
        DIALOG_1 = DESCRIPTIONS[0];
        DIALOG_2 = DESCRIPTIONS[2];
        IGNORE = DESCRIPTIONS[3];
        DIALOG_3 = DESCRIPTIONS[4];
    }

    private enum CUR_SCREEN {
        INTRO,
        COMPLETE;

        CUR_SCREEN() {
        }
    }
}
