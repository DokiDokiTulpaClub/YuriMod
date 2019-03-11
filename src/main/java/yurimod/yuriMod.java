package yurimod;
import basemod.ModLabeledToggleButton;
import basemod.helpers.RelicType;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import java.util.Properties;

import basemod.BaseMod;
import basemod.ModPanel;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditKeywordsSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.PostInitializeSubscriber;


import yurimod.events.YuriShrine;
import yurimod.patches.yuriEnum;
import yurimod.patches.AbstractCardEnum;
import yurimod.relics.*;
import yurimod.cards.*;
import yurimod.characters.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/* 
 * Welcome to this mildly over-commented Slay the Spire modding base. 
 * Use it to make your own mod of any type. - If you want to add any standard in-game content (Character, 
 * cards, relics), this is a good starting point.
 * It features 1 character with a minimal set of things: 1 card of each type, 1 debuff, 1 relic, etc.
 * If you're new to modding, I highly recommend going though the BaseMod wiki for whatever you wish to add 
 * https://github.com/daviscook477/BaseMod/wiki  and work your way thought your made with this base. 
 * Feel free to use this in any way you like, of course. Happy modding!
 */

@SpireInitializer
public class yuriMod implements EditCardsSubscriber, EditRelicsSubscriber, EditStringsSubscriber, EditKeywordsSubscriber,EditCharactersSubscriber, PostInitializeSubscriber {
  	public static final Logger logger = LogManager.getLogger(yuriMod.class.getName());
  	
  	//This is for the in-game mod settings pannel.
  	private static final String MODNAME = "Yuri";
    private static final String AUTHOR = "DokiDokiTulpaClub";
    private static final String DESCRIPTION = "Adds Yuri into the game.";
    
    // =============== IMPUT TEXTURE LOCATION =================
   
    // Color
    public static final Color YURI_PURPLE = CardHelper.getColor(158.0f, 16.0f, 203.0f);
    public static final Color YURI_RED = CardHelper.getColor(169.0f, 36.0f, 18.0f);

    // Image folder name
    private static final String yuri_MOD_ASSETS_FOLDER = "yuriModResources/images";
    
    // card backgrounds
    private static final String ATTACK_YURI_PURPLE = "512/bg_attack_yuri_purple.png";
    private static final String POWER_YURI_PURPLE = "512/bg_power_yuri_purple.png";
    private static final String SKILL_YURI_PURPLE = "512/bg_skill_yuri_purple.png";
    private static final String ENERGY_ORB_YURI_PURPLE = "512/card_yuri_purple_orb.png";
    private static final String ATTACK_YURI_RED = "512/bg_attack_yuri_red.png";
    private static final String POWER_YURI_RED = "512/bg_power_yuri_red.png";
    private static final String SKILL_YURI_RED = "512/bg_skill_yuri_red.png";
    private static final String ENERGY_ORB_YURI_RED = "512/card_yuri_red_orb.png";
    private static final String CARD_ENERGY_ORB = "512/card_small_orb.png";
    private static final String CARD_ENERGY_ORB_RED = "512/card_small_orb_red.png";

    private static final String ATTACK_YURI_PURPLE_PORTRAIT = "1024/bg_attack_yuri_purple.png";
    private static final String POWER_YURI_PURPLE_PORTRAIT = "1024/bg_power_yuri_purple.png";
    private static final String SKILL_YURI_PURPLE_PORTRAIT = "1024/bg_skill_yuri_purple.png";
    private static final String ENERGY_ORB_YURI_PURPLE_PORTRAIT = "1024/card_yuri_purple_orb.png";
    private static final String ATTACK_YURI_RED_PORTRAIT = "1024/bg_attack_yuri_red.png";
    private static final String POWER_YURI_RED_PORTRAIT = "1024/bg_power_yuri_red.png";
    private static final String SKILL_YURI_RED_PORTRAIT = "1024/bg_skill_yuri_red.png";
    private static final String ENERGY_ORB_YURI_RED_PORTRAIT = "1024/card_yuri_red_orb.png";


    // Card images
    public static final String yuri_STRIKE = "cards/Attack.png";
    public static final String yuri_DEFEND = "cards/Skill.png";
    public static final String yuri_GHOST_FORM = "cards/Ghost.png";
    public static final String yuri_HEAVY_STRIKE = "cards/HStrike.png";
    public static final String yuri_HEAVIER_STRIKE = "cards/HvStrike.png";
    public static final String yuri_QUICK_STRIKE = "cards/QStrike.png";
    public static final String yuri_UNCOMMON_SKILL = "cards/Skill.png";
    public static final String yuri_INVASION = "cards/Invasion.png";
    public static final String yuri_INVASION_CORRUPT = "cards/InvasionCorrupt.png";
    public static final String yuri_RARE_ATTACK = "cards/Attack.png";
    public static final String yuri_TORMENT = "cards/Torment.png";
    public static final String yuri_STARE = "cards/Stare.png";
    public static final String yuri_RARE_POWER = "cards/Power.png";
    public static final String yuri_TPOSE = "cards/TPose.png";
    public static final String yuri_MPOSE = "cards/MPose.png";
    public static final String yuri_TEASET = "cards/TeaSet.png";
    public static final String yuri_OOLONG = "cards/Tea.png";
    public static final String yuri_THROW_KNIVE = "cards/ThrowKnives.png";
    public static final String yuri_MULTIDIP = "cards/Dip.png";
    public static final String yuri_STEALPEN = "cards/Pen.png";
    public static final String yuri_STAB = "cards/AttackIn.png";
    public static final String yuri_DEBUGCARD = "cards/CodeC.png";
    public static final String yuri_WSLASH = "cards/SlashIn.png";
    public static final String yuri_MARKOV_CURSE = "cards/Markov.png";
    public static final String yuri_SHARM = "cards/Shock.png";
    public static final String yuri_DEEP_BREATH = "cards/Breath.png";
    public static final String yuri_DEEP_BREATH_CORRUPT = "cards/BreathCorrupt.png";
    public static final String yuri_GROW_INSANITY = "cards/Grow.png";
    public static final String yuri_ADAPTIVE = "cards/Adaptive.png";
    public static final String yuri_ARMBRACE = "cards/Brace.png";
    public static final String yuri_NERFBAT = "cards/Nerf.png";
    public static final String yuri_SUICIDE = "cards/Suicide.png";
    public static final String yuri_GLITCH = "cards/Glitch.png";
    public static final String yuri_INDUCE_GLITCH = "cards/Eye.png";
    public static final String yuri_BROKE_CODE = "cards/Glitch2.png";
    public static final String yuri_ESCAPE = "cards/Escape.png";
    public static final String yuri_THORN_REMOVE = "cards/NoThorns.png";
    public static final String yuri_UNIFORM = "cards/Uniform.png";
    public static final String yuri_RELENTLESS_STRIKES = "cards/Power.png";
    public static final String yuri_DOUBLE = "cards/Double.png";
    public static final String yuri_DRAGON_FORM = "cards/Dragon.png";
    public static final String yuri_GLITCH_STRIKE = "cards/GStrike.png";
    public static final String yuri_REPAIR_CODE = "cards/CodeF.png";
    public static final String yuri_DEJAVU = "cards/Deja.png";
    public static final String yuri_RECKLESS_STRIKE = "cards/RStrike.png";
    public static final String yuri_UNTESTED_CODE = "cards/CodeT.png";
    public static final String yuri_THROW_BOOK = "cards/ThrowBook.png";
    public static final String yuri_GLITCH_REMOVE = "cards/CodeR.png";
    public static final String yuri_BOOK_RED = "cards/BookR.png";
    public static final String yuri_BOOK_GREEN = "cards/BookG.png";
    public static final String yuri_BOOK_BLUE = "cards/BookB.png";
    public static final String yuri_AURA_CALM = "cards/CalmAura.png";
    public static final String yuri_AURA_CALM_CORRUPT = "cards/CalmAuraCorrupt.png";
    public static final String yuri_DISTILL = "cards/Distill.png";
    public static final String yuri_DISTILL_CORRUPT = "cards/DistillCorrupt.png";
    public static final String yuri_EXPLOIT_STRIKE = "cards/ExploitStrike.png";
    public static final String yuri_RHAPSODY_CURSE = "cards/Rhapsody.png";
    public static final String yuri_SKIP = "cards/Skip.png";
    public static final String yuri_CONSUME_GLITCH = "cards/ConsumeGlitch.png";
    public static final String yuri_UNSTABLE_STRIKE = "cards/UnstableStrike.png";
    public static final String yuri_HITBOX = "cards/CorruptHitbox.png";
    public static final String yuri_PARRY = "cards/Parry.png";
    public static final String yuri_SPIN = "cards/Spin.png";
    public static final String yuri_DOUBLE_CODE = "cards/DoubleCode.png";
    public static final String yuri_GREEN_TEA = "cards/GreenTea.png";
    public static final String yuri_BLACK_TEA = "cards/BlackTea.png";
    public static final String yuri_STALK = "cards/Stalk.png";
    public static final String yuri_DISABLE_STRIKE = "cards/DisableStrike.png";
    public static final String yuri_SCARE = "cards/JumpScare.png";
    public static final String yuri_RELOAD = "cards/Reload.png";
    public static final String yuri_RELOAD_CORRUPT = "cards/ReloadCorrupt.png";
    public static final String yuri_MIRROR = "cards/Mirror.png";
    public static final String yuri_MIRROR_CORRUPT = "cards/MirrorCorrupt.png";
    public static final String yuri_VIRUS = "cards/Virus.png";
    public static final String yuri_REMOVE_SAFETY = "cards/RemoveSafety.png";
    public static final String yuri_HOLD_BACK = "cards/HoldBack.png";
    public static final String yuri_HOLD_BACK_CORRUPT = "cards/HoldBackCorrupt.png";
    public static final String yuri_MAGNET = "cards/MagneticKnives.png";
    public static final String yuri_REACT = "cards/ReactiveMadness.png";
    public static final String yuri_REACT_CORRUPT = "cards/ReactiveMadnessCorrupt.png";
    public static final String yuri_INSANITY_DEFENSE = "cards/InsanityDefense.png";


    // Power images
    public static final String COMMON_POWER = "powers/placeholder_power.png";
    public static final String RARE_POWER = "powers/placeholder_power.png";


    // Relic images  
    public static final String yuri_MARKOV = "relics/Markov.png";
    public static final String yuri_MARKOV_OUTLINE = "relics/outline/Markov.png";
    public static final String yuri_BOOK_BOOK = "relics/BookBook.png";
    public static final String yuri_BOOK_BOOK_OUTLINE = "relics/outline/BookBook.png";
    public static final String yuri_KNIFE = "relics/spearHead.png";
    public static final String yuri_KNIFE_OUTLINE = "relics/outline/spearHead.png";
    public static final String yuri_BLOODY_KNIFE = "relics/spearHeadBlood.png";
    public static final String yuri_BLOODY_KNIFE_OUTLINE = "relics/outline/spearHead.png";
    public static final String yuri_WATER = "relics/Water.png";
    public static final String yuri_WATER_OUTLINE = "relics/outline/Water.png";
    public static final String yuri_WINE = "relics/Wine.png";
    public static final String yuri_WINE_OUTLINE = "relics/outline/Water.png";
    public static final String yuri_GLITCH_BAG = "relics/GlitchBag.png";
    public static final String yuri_GLITCH_BAG_OUTLINE = "relics/outline/GlitchBag.png";
    public static final String yuri_GLITCH_POWDER = "relics/GlitchPowder.png";
    public static final String yuri_GLITCH_POWDER_OUTLINE = "relics/outline/GlitchPowder.png";


    // Character assets
    private static final String YURI_BUTTON = "charSelect/YuriButton.png";
    private static final String YURI_PORTRAIT = "charSelect/YuriBG.png";
    public static final String YURI_SHOULDER_1 = "char/Yuri/shoulder.png";
    public static final String YURI_SHOULDER_2 = "char/Yuri/shoulder2.png";
    public static final String YURI_CORPSE = "char/Yuri/corpse.png";

	//Mod Badge
    public static final String BADGE_IMAGE = "Badge.png";

    //Settings
    public static Properties yuriDefaults = new Properties();
    public static final String SETTING_BRUTAL = "BrutalInsanity";
    public static final String SETTING_SAFE = "SafeMode";
    public static boolean BrutalInsanity = false;
    public static boolean SafeMode = false;

	// Animations atlas and JSON files
    public static final String YURI_SKELETON_ATLAS = "char/Yuri/skeleton.atlas";
    public static final String YURI_SKELETON_JSON = "char/Yuri/skeleton.json";
    
    
    // =============== /IMPUT TEXTURE LOCATION/ =================
    
    /**
     * Makes a full path for a resource path
     * @param resource the resource, must *NOT* have a leading "/"
     * @return the full path
     */
    public static final String makePath(String resource) {
    	return  yuri_MOD_ASSETS_FOLDER + "/" + resource;
    }

    // =============== SUBSCRIBE, CREATE THE COLOR, INITIALIZE =================

    public yuriMod() {
		logger.info("Subscribe to basemod hooks");

		BaseMod.subscribe(this);

		logger.info("Done subscribing");

		
		logger.info("Creating the color " + AbstractCardEnum.YURI_PURPLE.toString());

		BaseMod.addColor(AbstractCardEnum.YURI_PURPLE, YURI_PURPLE, YURI_PURPLE, YURI_PURPLE, YURI_PURPLE, YURI_PURPLE, YURI_PURPLE, YURI_PURPLE,
				makePath(ATTACK_YURI_PURPLE), makePath(SKILL_YURI_PURPLE), makePath(POWER_YURI_PURPLE), makePath(ENERGY_ORB_YURI_PURPLE),
				makePath(ATTACK_YURI_PURPLE_PORTRAIT), makePath(SKILL_YURI_PURPLE_PORTRAIT), makePath(POWER_YURI_PURPLE_PORTRAIT),
				makePath(ENERGY_ORB_YURI_PURPLE_PORTRAIT), makePath(CARD_ENERGY_ORB));

		BaseMod.addColor(AbstractCardEnum.YURI_RED, YURI_RED, YURI_RED, YURI_RED, YURI_RED, YURI_RED, YURI_RED, YURI_RED,
				makePath(ATTACK_YURI_RED), makePath(SKILL_YURI_RED), makePath(POWER_YURI_RED), makePath(ENERGY_ORB_YURI_RED),
				makePath(ATTACK_YURI_RED_PORTRAIT), makePath(SKILL_YURI_RED_PORTRAIT), makePath(POWER_YURI_RED_PORTRAIT),
				makePath(ENERGY_ORB_YURI_RED_PORTRAIT), makePath(CARD_ENERGY_ORB_RED));

		logger.info("Done Creating the color");

		yuriDefaults.setProperty(SETTING_BRUTAL, "FALSE");
		yuriDefaults.setProperty(SETTING_SAFE, "FALSE");
        try {
            SpireConfig config = new SpireConfig("yuriMod", "YuriConfig",yuriDefaults);
            config.load();
            BrutalInsanity = config.getBool(SETTING_BRUTAL);
            SafeMode = config.getBool(SETTING_SAFE);
        } catch (Exception e) {
            e.printStackTrace();
    }
    }

    
	@SuppressWarnings("unused") 
    public static void initialize(){
        logger.info("========================= Initializing yuri Mod. Hi. =========================");
    	yuriMod yurimod = new yuriMod();
        logger.info("========================= /yuri Mod Initialized/ =========================");
    }
    
	// ============== /SUBSCRIBE, CREATE THE COLOR, INITIALIZE/ =================
	
	
	// =============== LOAD THE CHARACTER =================
	
	@Override
	public void receiveEditCharacters() {
		logger.info("begin editing characters");

		logger.info("add " + yuriEnum.YURI.toString());

		BaseMod.addCharacter(new yuri("Yuri", yuriEnum.YURI),
				makePath(YURI_BUTTON), makePath(YURI_PORTRAIT), yuriEnum.YURI);

		logger.info("done editing characters");
	}

	// =============== /LOAD THE CHARACTER/ =================
	
		  
    // =============== LOAD THE MOD BADGE AND MENU =================

	@Override
	public void receivePostInitialize() {
		logger.info("Load Badge Image and mod options");

		Texture badgeTexture = new Texture(makePath(BADGE_IMAGE));

		ModPanel settingsPanel = new ModPanel();
		BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);

        ModLabeledToggleButton BrutalButton = new ModLabeledToggleButton("Doubles health loss from insanity.",
                350.0f, 700.0f, Settings.CREAM_COLOR, FontHelper.charDescFont,
                BrutalInsanity, settingsPanel, (label) -> {
        }, (button) -> {
            BrutalInsanity = button.enabled;
            try {
                SpireConfig config = new SpireConfig("yuriMod", "YuriConfig",yuriDefaults);
                config.setBool(SETTING_BRUTAL, BrutalInsanity);
                config.save();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        settingsPanel.addUIElement(BrutalButton);
        ModLabeledToggleButton SafeButton = new ModLabeledToggleButton("Disables disturbing content (REQUIRES RESTART).",
                350.0f, 650.0f, Settings.CREAM_COLOR, FontHelper.charDescFont,
                SafeMode, settingsPanel, (label) -> {
        }, (button) -> {
            SafeMode = button.enabled;
            try {
                SpireConfig config = new SpireConfig("yuriMod", "YuriConfig",yuriDefaults);
                config.setBool(SETTING_SAFE, SafeMode);
                config.save();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        settingsPanel.addUIElement(SafeButton);

		logger.info("Done loading badge Image and mod options");

		if (SafeMode) {
            BaseMod.addEvent(YuriShrine.ID, YuriShrine.class, "TheCity");
        }

	}

	// =============== /LOAD THE MOD BADGE AND MENU/ =================

	

	// ================ ADD RELICS ===================
	
	@Override
	public void receiveEditRelics() {
		logger.info("Add relics");

		BaseMod.addRelicToCustomPool(new PortraitOfMarkov(), AbstractCardEnum.YURI_PURPLE);
		BaseMod.addRelic(new PortableGlitches(), RelicType.SHARED);
		BaseMod.addRelicToCustomPool(new yuriKnife(), AbstractCardEnum.YURI_PURPLE);
		BaseMod.addRelicToCustomPool(new BookOfBooks(), AbstractCardEnum.YURI_PURPLE);
        BaseMod.addRelicToCustomPool(new BloodyKnife(), AbstractCardEnum.YURI_RED);
        BaseMod.addRelicToCustomPool(new GlassOfWater(), AbstractCardEnum.YURI_PURPLE);
        BaseMod.addRelicToCustomPool(new GlassOfWine(), AbstractCardEnum.YURI_PURPLE);
        BaseMod.addRelicToCustomPool(new GlitchPowder(), AbstractCardEnum.YURI_PURPLE);

		logger.info("done adding relics!");
	}
	
	// ================ /ADD RELICS/ ===================

	  
	  
    // ================ ADD CARDS ===================
	
	@Override
	public void receiveEditCards() {
		
		logger.info("Add Cards");
		// Add the cards
		BaseMod.addCard(new yuriStrike());
		BaseMod.addCard(new yuriDefend());
		BaseMod.addCard(new GhostForm());
//		BaseMod.addCard(new yuriUncommonSkill());
		BaseMod.addCard(new yuriHeavyStrike());
		BaseMod.addCard(new HeavierStrike());
		BaseMod.addCard(new yuriQuickStrike());
		BaseMod.addCard(new ChibiInvasion());
		BaseMod.addCard(new ChibiInvasionCorrupt());
//		BaseMod.addCard(new yuriRareAttack());
		BaseMod.addCard(new Torment());
		BaseMod.addCard(new Stare());
//		BaseMod.addCard(new yuriRarePower());
		BaseMod.addCard(new TPose());
		BaseMod.addCard(new MPose());
		BaseMod.addCard(new TeaSet());
		BaseMod.addCard(new Oolong());
		BaseMod.addCard(new ThrowingKnives());
		BaseMod.addCard(new MultiDip());
		BaseMod.addCard(new StealPen());
		BaseMod.addCard(new yuriStab());
		BaseMod.addCard(new WickedSlash());
		BaseMod.addCard(new DebugCard());
		BaseMod.addCard(new MarkovCurse());
		BaseMod.addCard(new DeepBreathing());
		BaseMod.addCard(new DeepBreathingCorrupt());
		BaseMod.addCard(new GrowingInsanity());
		BaseMod.addCard(new AdaptiveAI());
		BaseMod.addCard(new ArmBrace());
		BaseMod.addCard(new NerfBat());
		BaseMod.addCard(new Glitch());
		BaseMod.addCard(new InduceGlitch());
		BaseMod.addCard(new BrokenCode());
		BaseMod.addCard(new NoEscape());
		BaseMod.addCard(new ThornRemove());
		BaseMod.addCard(new SturdyUniform());
		BaseMod.addCard(new RelentlessStrikes());
		BaseMod.addCard(new SeeingDouble());
		BaseMod.addCard(new DragonForm());
		BaseMod.addCard(new GlitchedStrike());
		BaseMod.addCard(new RepairCode());
		BaseMod.addCard(new DejaVu());
		BaseMod.addCard(new RecklessStrike());
		BaseMod.addCard(new UntestedCode());
		BaseMod.addCard(new ThrowBook());
		BaseMod.addCard(new GlitchRemove());
		BaseMod.addCard(new BookRed());
		BaseMod.addCard(new BookGreen());
		BaseMod.addCard(new BookBlue());
		BaseMod.addCard(new CalmingAura());
		BaseMod.addCard(new CalmingAuraCorrupt());
		BaseMod.addCard(new DistillInsanity());
		BaseMod.addCard(new DistillInsanityCorrupt());
		BaseMod.addCard(new ExploitStrike());
		BaseMod.addCard(new RhapsodyCurse());
		BaseMod.addCard(new Skip());
		BaseMod.addCard(new ConsumeGlitch());
		BaseMod.addCard(new UnstableStrike());
		BaseMod.addCard(new CorruptHitbox());
		BaseMod.addCard(new Parry());
		BaseMod.addCard(new Spin());
		BaseMod.addCard(new DuplicateCode());
		BaseMod.addCard(new GreenTea());
		BaseMod.addCard(new BlackTea());
		BaseMod.addCard(new Stalk());
		BaseMod.addCard(new DisableStrike());
		BaseMod.addCard(new JumpScare());
		BaseMod.addCard(new ReloadGame());
		BaseMod.addCard(new ReloadGameCorrupt());
		BaseMod.addCard(new MirrorInsanity());
//		BaseMod.addCard(new MirrorInsanityCorrupt());
		BaseMod.addCard(new Virus());
		BaseMod.addCard(new RemoveSafetyChecks());
		BaseMod.addCard(new HoldBack());
		BaseMod.addCard(new HoldBackCorrupt());
		BaseMod.addCard(new MagneticKnives());
		BaseMod.addCard(new ReactiveMadness());
		BaseMod.addCard(new ReactiveMadnessCorrupt());
		BaseMod.addCard(new InsanityDefense());
		if (!SafeMode){
            BaseMod.addCard(new Suicide());
            BaseMod.addCard(new SelfHarm());
        }

		logger.info("Making sure the cards are unlocked.");
		// Unlock the cards
		UnlockTracker.unlockCard(yuriStrike.ID);
		UnlockTracker.unlockCard(yuriDefend.ID);
		UnlockTracker.unlockCard(GhostForm.ID);
		UnlockTracker.unlockCard(yuriUncommonSkill.ID);
		UnlockTracker.unlockCard(yuriHeavyStrike.ID);
		UnlockTracker.unlockCard(HeavierStrike.ID);
		UnlockTracker.unlockCard(yuriQuickStrike.ID);
		UnlockTracker.unlockCard(ChibiInvasion.ID);
		UnlockTracker.unlockCard(yuriRareAttack.ID);
		UnlockTracker.unlockCard(Torment.ID);
		UnlockTracker.unlockCard(Stare.ID);
		UnlockTracker.unlockCard(yuriRarePower.ID);
        UnlockTracker.unlockCard(TPose.ID);
        UnlockTracker.unlockCard(MPose.ID);
        UnlockTracker.unlockCard(TeaSet.ID);
        UnlockTracker.unlockCard(Oolong.ID);
        UnlockTracker.unlockCard(ThrowingKnives.ID);
        UnlockTracker.unlockCard(MultiDip.ID);
        UnlockTracker.unlockCard(StealPen.ID);
        UnlockTracker.unlockCard(yuriStab.ID);
        UnlockTracker.unlockCard(WickedSlash.ID);
        UnlockTracker.unlockCard(DebugCard.ID);
        UnlockTracker.unlockCard(MarkovCurse.ID);
        UnlockTracker.unlockCard(SelfHarm.ID);
        UnlockTracker.unlockCard(DeepBreathing.ID);
        UnlockTracker.unlockCard(GrowingInsanity.ID);
        UnlockTracker.unlockCard(AdaptiveAI.ID);
        UnlockTracker.unlockCard(ArmBrace.ID);
        UnlockTracker.unlockCard(NerfBat.ID);
        UnlockTracker.unlockCard(Glitch.ID);
        UnlockTracker.unlockCard(InduceGlitch.ID);
        UnlockTracker.unlockCard(BrokenCode.ID);
        UnlockTracker.unlockCard(NoEscape.ID);
        UnlockTracker.unlockCard(ThornRemove.ID);
        UnlockTracker.unlockCard(SturdyUniform.ID);
        UnlockTracker.unlockCard(RelentlessStrikes.ID);
        UnlockTracker.unlockCard(SeeingDouble.ID);
        UnlockTracker.unlockCard(DragonForm.ID);
        UnlockTracker.unlockCard(GlitchedStrike.ID);
        UnlockTracker.unlockCard(RepairCode.ID);
        UnlockTracker.unlockCard(DejaVu.ID);
        UnlockTracker.unlockCard(RecklessStrike.ID);
        UnlockTracker.unlockCard(UntestedCode.ID);
        UnlockTracker.unlockCard(ThrowBook.ID);
        UnlockTracker.unlockCard(GlitchRemove.ID);
        UnlockTracker.unlockCard(BookRed.ID);
        UnlockTracker.unlockCard(BookGreen.ID);
        UnlockTracker.unlockCard(BookBlue.ID);
        UnlockTracker.unlockCard(CalmingAura.ID);
        UnlockTracker.unlockCard(DistillInsanity.ID);
        UnlockTracker.unlockCard(ExploitStrike.ID);
        UnlockTracker.unlockCard(RhapsodyCurse.ID);
        UnlockTracker.unlockCard(Skip.ID);
        UnlockTracker.unlockCard(ConsumeGlitch.ID);
        UnlockTracker.unlockCard(UnstableStrike.ID);
        UnlockTracker.unlockCard(CorruptHitbox.ID);
        UnlockTracker.unlockCard(Parry.ID);
        UnlockTracker.unlockCard(Spin.ID);
        UnlockTracker.unlockCard(DuplicateCode.ID);
        UnlockTracker.unlockCard(GreenTea.ID);
        UnlockTracker.unlockCard(BlackTea.ID);
        UnlockTracker.unlockCard(Stalk.ID);
        UnlockTracker.unlockCard(DisableStrike.ID);
        UnlockTracker.unlockCard(JumpScare.ID);
        UnlockTracker.unlockCard(ReloadGame.ID);
        UnlockTracker.unlockCard(MirrorInsanity.ID);
        UnlockTracker.unlockCard(Virus.ID);
        UnlockTracker.unlockCard(RemoveSafetyChecks.ID);
        UnlockTracker.unlockCard(HoldBack.ID);
        UnlockTracker.unlockCard(MagneticKnives.ID);
        UnlockTracker.unlockCard(ReactiveMadness.ID);
        UnlockTracker.unlockCard(InsanityDefense.ID);

		logger.info("Cards - added!");
	}
	
    // ================ /ADD CARDS/ ===================

	        
	// ================ LOAD THE TEXT ===================
	
	@Override
	public void receiveEditStrings() {
        logger.info("begin editing strings");
        if (Settings.language == Settings.GameLanguage.ZHS) {
            BaseMod.loadCustomStringsFile(CardStrings.class, "yuriModResources/localization/yuriMod-Card-Strings-zh.json");
            BaseMod.loadCustomStringsFile(PowerStrings.class, "yuriModResources/localization/yuriMod-Power-Strings-zh.json");
            BaseMod.loadCustomStringsFile(RelicStrings.class, "yuriModResources/localization/yuriMod-Relic-Strings-zh.json");
            BaseMod.loadCustomStringsFile(EventStrings.class, "yuriModResources/localization/yuriMod-Event-Strings-zh.json");
            BaseMod.loadCustomStringsFile(CharacterStrings.class, "yuriModResources/localization/yuriMod-Character-Strings-zh.json");
        } else {
            BaseMod.loadCustomStringsFile(CardStrings.class, "yuriModResources/localization/yuriMod-Card-Strings.json");
            BaseMod.loadCustomStringsFile(PowerStrings.class, "yuriModResources/localization/yuriMod-Power-Strings.json");
            BaseMod.loadCustomStringsFile(RelicStrings.class, "yuriModResources/localization/yuriMod-Relic-Strings.json");
            BaseMod.loadCustomStringsFile(EventStrings.class, "yuriModResources/localization/yuriMod-Event-Strings.json");
            BaseMod.loadCustomStringsFile(CharacterStrings.class, "yuriModResources/localization/yuriMod-Character-Strings.json");
        }
            logger.info("done editing strings");
    }

	// ================ /LOAD THE TEXT/ ===================

	// ================ LOAD THE KEYWORDS ===================

	@Override
	public void receiveEditKeywords() {
		final String[] insanity = { "insanity" };
		BaseMod.addKeyword(insanity, "Increases attack damage and reduces incoming attack damage. NL Lose HP at the end of each turn.");
		final String[] glitched = { "glitched" };
		BaseMod.addKeyword(glitched, "Reduces attack damage and increases incoming attack damage. NL Lose HP at the start of each turn.");
		final String[] glitch = { "glitch" };
		BaseMod.addKeyword(glitch, "Add one or more Glitches on top of your draw pile. NL Glitch is a status card that inflicts glitched and draws another card when played.");
		final String[] thornlike = { "thornlike" };
		BaseMod.addKeyword(thornlike, "Thornlike buffs include thorns, sharp hide, angry, malleable, curl up and similar modded buffs.");
		final String[] book = { "book", "books" };
		BaseMod.addKeyword(book, "A card that grants abilities based on other characters, and synergize with themselves.");

		BaseMod.addKeyword(new String[]{"精神错乱"}, "提升攻击伤害，降低受到的伤害。 NL 在每回合结束时失去生命。");
		BaseMod.addKeyword(new String[]{"受损"}, "降低攻击伤害，提高受到的伤害。 NL 在每回合开始时失去生命。");
		BaseMod.addKeyword(new String[]{"故障"}, "向你的抽牌堆中加入一或几张故障（状态牌）。 NL 故障可以零费打出，效果是使你获得一层受损，然后抽一张牌。");
		BaseMod.addKeyword(new String[]{"类反伤"}, "类反伤状态包括荆棘、锋利外壳、生气和柔韧。");
		BaseMod.addKeyword(new String[]{"书"}, "通过获得其他角色的能力来增强自己的能力牌。");
		BaseMod.addKeyword(new String[]{"临时生命"}, "临时生命可以为你抵挡伤害和生命损失，在战斗结束后消失");


	}
	
    // ================ /LOAD THE KEYWORDS/ ===================    

	// this adds "ModName: " before the ID of any card/relic/power etc.
	// in order to avoid conflicts if any other mod uses the same ID.
	public static String makeID(String idText) {
		return "yuri:" + idText;
	}

}
