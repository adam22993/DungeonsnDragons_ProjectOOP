package Speaches_Screens;

public class Quotes {
    // TODO - Add quotes for all characters, situations and enemies.
    /**
     *   This class contains all the quotes used in the game. to use a quote, simply use the following syntax:
     *   String quote = Quotes.{quote array of your choosing}[Random.nextInt(Quotes.{quote array of your choosing}.length)];
     *   The above line will return a random quote from the array of your choosing.
     *   Not all quotes are using the name of the player but still use the {0} syntax. This is because the quotes
     *   are randomly chosen and the name of the player would be needed in most of them.
     */

    //################################# Greetings ####################################
    public static String[] playerGreetings = {
            "Hail and well met, {0}! Thou art most welcome to our company.",
            "Greetings, {0}! Thy arrival is a boon to our cause!",
            "Well met, {0}! Pray, what brings thee to our camp?",
            "Good morrow, {0}! Let us set forth on our noble quest!",
            "Ho there, {0}! Thou art come at a most fortuitous time!",
            "Hail, {0}! Thou art among kindred spirits in our company.",
            "Good den, {0}! Our fortunes shall surely prosper with thy aid.",
            "Greetings, {0}! Thou art most welcome to our humble abode.",
            "Well met, {0}! Thy sword arm shall prove vital to our cause.",
            "Hail and welcome, {0}! Pray, make thyself comfortable in our company.",
            "Hail, {0}! Thy arrival bodes well for our quest. Let us make haste!",
            "Hail, {0}! Verily, thou art a worthy addition to our ranks!"};

    public static String[] enemyGreetings = {
            "You dare to challenge me, {0}? You will pay with your life!",
            "I will crush you like the insignificant worm you are, {0}!",
            "Your bones will be my playthings, {0}. Prepare to meet your doom!",
            "My power is beyond your reckoning, {0}. Bow before me or be destroyed!",
            "You will not survive this encounter, {0}. I will enjoy watching you suffer.",
            "I will feast on your flesh, {0}, and relish in your screams of agony!",
            "You will die a slow and painful death, {0}. I will make sure of it.",
            "Foolish {0}! Prepare to meet thy doom at the hands of this fearsome beast!",
            "Your puny existence ends here, {0}! Tremble before the might of this monstrous creature!",
            "Behold, {0}! The terror that shall consume thee and leave no trace behind!",
            "Run while you still can, {0}! This beast thirsts for your blood and will not be denied!",
            "Pathetic {0}, your feeble attempts shall only fuel the wrath of this abomination!",
            "Prepare to be devoured, {0}! This creature knows no mercy and shall spare none!"
    };

    //################################ Special Abilities ####################################
    public static String[] avengersShieldQuotes = {
            "Feel the might of the Avenger's Shield!",
            "I shall smite thee with the power of the Avenger's Shield!",
            "None can withstand the Avenger's Shield!",
            "Avenger's Shield, protect and serve!",
            "My shield will be your downfall!",
            "Avenger's Shield, because sometimes hitting things with a sword just isn't enough.",
            "Avenger's Shield, because who needs a ranged weapon when you have a shield?",
            "I didn't choose the shield life, the shield life chose me.",
            "I'm not saying I'm Captain America, but have you ever seen us in the same room together?",
            "Avenger's Shield, the ultimate boomerang.",
            "Avenger's Shield, when you absolutely, positively have to kill every mob in the room.",
            "I don't always use a shield, but when I do, I prefer Avenger's Shield.",
            "Avenger's Shield: for when you need to make a statement, loudly and with a flying shield.",
            "Avenger's Shield: Because throwing a shield is always cooler than throwing a punch.",
            "I could've been a mage or a rogue, but no, I had to be a warrior with a giant shield.",
            "Behold the Avenger's Shield, the bane of all who oppose me!",
            "The Avenger's Shield is my weapon, my shield is my defense, and my will is unbreakable!",
            "The Avenger's Shield is justice, striking true and swift!",
            "I am the shield that protects the innocent, the hammer that crushes the wicked, and the avenger that hunts the guilty!",
            "The Avenger's Shield never misses its target, and its target never survives!",
            "Let the Avenger's Shield be a reminder of your folly, and a warning to all who oppose me!"
    };

    String[] unableToCastQuotes = {
            "I need a breather. Can someone else take over for a bit?",
            "Looks like I need to give my shield a break before I break it.",
            "My shield needs to cool off, just like me after a long battle.",
            "I'm not slacking, I'm just waiting for the perfect moment to strike.",
            "I think my shield is having an identity crisis. It keeps changing colors.",
            "My shield is taking a nap, it's been working overtime lately.",
            "I need a little break from being the hero all the time.",
            "Even heroes need a break. I'll let someone else take the lead for now.",
            "My shield is on strike. I guess it's time for some hand-to-hand combat.",
            "I may be on cooldown, but my fists still pack a punch!",
            "I'll let my weapon cool off for a bit, then it's back to slicing and dicing!",
            "I'm not slacking, I'm just waiting for the perfect moment to strike.",
            "I'll let my weapon cool off for a bit, then it's back to slicing and dicing!",
            "My enemies may think they've won, but they've only earned a brief reprieve.",
            "My blade may be on cooldown, but my spirit burns hotter than ever!",
            "Patience is a warrior's greatest ally. I'll bide my time and strike when the moment is right.",
            "Cooldowns are just a reminder that I'm mortal. But I'll be back to smiting foes in no time!",
            "I'll take a moment to catch my breath, then it's back to the battle!",
            "My sword arm may be on cooldown, but my mind is still sharp as ever.",
            "Cooldowns are a necessary evil, but I'll come out the other side even stronger!"
    };

    public static String[] blizzardQuotes = {
            "Behold the power of the Blizzard!",
            "Feel the chill of my Blizzard!",
            "Winter is coming... with my Blizzard!",
            "Let it snow, let it snow, let it snow... with my Blizzard!",
            "Icy winds, hear my call, unleash the Blizzard upon them all!",
            "Blizzard, because sometimes a fireball just won't cut it.",
            "I may not have a sword, but I have a Blizzard and that's all I need.",
            "I'm a frost mage, so you better watch your step or you'll slip and fall into my Blizzard.",
            "Blizzard, the most effective way to cool off after a heated battle.",
            "I cast Blizzard, and suddenly everyone forgets about that pesky fire mage.",
            "I don't always cast spells, but when I do, they're Blizzard spells.",
            "With Blizzard, you never have to worry about getting brain freeze from your slushie again.",
            "I'll just leave this Blizzard here and let you chill for a bit.",
            "Blizzard: because making it rain ice is way cooler than making it rain cats and dogs.",
            "If you thought winter was bad before, just wait until you see my Blizzard!",
            "Blizzard: when you absolutely, positively have to freeze every mob in the room.",
            "I'm not a snowman, but with my Blizzard, I could make you one.",
            "Blizzard, because there's no better way to say 'chill out' to your enemies.",
            "I may be running low on mana, but my Blizzard is about to make it snow!"
    };



    String[] unableToCastMageQuotes = {
            "Oops, looks like I forgot to pay my mana bill.",
            "I guess I'm not as powerful as I thought...",
            "I swear I had mana just a minute ago...",
            "This is embarrassing...I guess I'll just have to rely on my wimpy basic attack.",
            "I blame the wizard who sold me this faulty wand.",
            "I must have eaten too many mana-free donuts before this battle.",
            "I'll just pretend I'm using my special ability, and hope the enemy falls for it.",
            "I'm not a real mage, I'm just a poser with a shiny robe.",
            "I need a nap...or maybe a mana potion.",
            "I think my magic is scared of the boss. Can we just run away?",
            "I'm not a mage, I'm just a really good illusionist.",
            "I knew I should have practiced my magic more...",
            "Looks like I'll have to rely on my trusty wooden staff for now.",
            "Who needs magic when you have a good ol' fashioned punch?",
            "Maybe I'll just distract the enemy with some silly dance moves...",
            "I think I accidentally turned my wand into a spaghetti noodle.",
            "I guess this is what happens when you skip wizarding school.",
            "Note to self: always pack extra mana potions.",
            "I'm not sure what's worse, the enemy or my terrible magic skills.",
            "I think my wand is broken. Maybe I can fix it with some duct tape?",
            "I must have left my magic powers in my other robe."
    };


    public static String[] fanOfKnivesQuotes = {
            "Dance with the knives!",
            "Looks like it's time for a little Fan of Knives action!",
            "You know what they say, a knife in the back is worth two in the hand.",
            "The shadows hide my Fan of Knives, but not its deadly effects.",
            "With a flick of my wrist, my Fan of Knives strikes its targets with deadly precision.",
            "My Fan of Knives is a symphony of death, striking each enemy with deadly accuracy.",
            "My Fan of Knives is like a whirlwind of steel, slicing through all who stand in my way.",
            "They say the pen is mightier than the sword, but my Fan of Knives is mightier than both.",
            "My Fan of Knives cuts through the air like a blade through silk, striking all who dare to oppose me.",
            "With my Fan of Knives in hand, I am a force to be reckoned with.",
            "My Fan of Knives is like a burst of lightning, striking my foes with shocking speed and precision.",
            "My Fan of Knives is like a serpent, striking at the hearts of my enemies with deadly accuracy.",
            "My Fan of Knives is a dance of death, leaving a trail of fallen foes in its wake.",
            "My knives have a message for you... and it's pointy.",
            "If you thought one knife was dangerous, just wait until you see my Fan of Knives.",
            "Ready to take a little spin with my knives?",
            "The only thing sharper than my wit is my Fan of Knives.",
            "Nothing says 'I mean business' like a good old fashioned Fan of Knives.",
            "Don't mind me, I'm just practicing my Fan of Knives for the big show.",
            "They say the pen is mightier than the sword... but have you tried a Fan of Knives?",
            "I don't need a license to carry a weapon when I have my Fan of Knives!",
            "Fan of Knives: because sometimes one knife just isn't enough.",
            "You might call it a Fan of Knives, but I like to think of it as a Fan of Sharpness.",
            "I hope you're ready for some slicing and dicing, because my Fan of Knives is itching to go!",
            "I don't always use AOE attacks, but when I do, I prefer Fan of Knives.",
            "My Fan of Knives is so sharp, it can cut through both armor and bread.",
            "Fan of Knives: for when you need to make a point (or 50)!",
            "Fan of Knives: because sometimes a subtle hint just isn't enough.",
            "My Fan of Knives isn't just a weapon, it's a statement."
    };

    public static String[] unableToCastRogueQuotes = {
            "I guess I'm not as sneaky as I thought...",
            "I swear I had energy just a minute ago...",
            "This is embarrassing...I guess I'll just have to rely on my wimpy basic attack.",
            "I blame the rogue who sold me this faulty dagger.",
            "I must have eaten too many energy-free donuts before this battle.",
            "I'll just pretend I'm using my special ability, and hope the enemy falls for it.",
            "I'm not a real rogue, I'm just a poser with a shiny dagger.",
            "I need a nap...or maybe an energy potion.",
            "I think my stealth is scared of the boss. Can we just run away?",
            "I'm not a rogue, I'm just a really good illusionist.",
            "I knew I should have practiced my stealth more...",
            "Looks like I'll have to rely on my trusty wooden staff for now.",
            "Who needs stealth when you have a good ol' fashioned punch?",
            "Maybe I'll just distract the enemy with some silly dance moves...",
            "I think I accidentally turned my dagger into a spaghetti noodle.",
            "I guess this is what happens when you skip rogue school.",
            "Note to self: always pack extra energy potions.",
            "I'm not sure what's worse, the enemy or my terrible stealth skills.",
            "I think my dagger is broken. Maybe I can fix it with some duct tape?",
            "I must have left my stealth in my other robe.",
            "Sorry, I can't perform surgery without my trusty scalpels. And by scalpels, I mean energy.",
            "Looks like my knives need a coffee break.",
            "Ran out of juice. Can't make a smoothie without the energy.",
            "Well, I could throw some rocks, but that wouldn't be very sneaky, would it?",
            "Out of energy? Looks like I've been partying too hard.",
            "I'm all out of knives, but I've got plenty of sass left.",
            "I could throw a tantrum instead of knives, but it's not as effective.",
            "I'm not out of energy, I'm just conserving it for my next trick. Don't ask me what it is, it's a secret.",
            "You know what they say, a rogue without energy is just a jester.",
            "I guess I'll just have to rely on my charming good looks to get out of this one."
    };

    //############################## Enemy Interactions ################################
    public static String[] bossGreetingQuotes = {
            "I am the end of your journey.",
            "Your pitiful attempts to defeat me amuse me.",
            "You dare to challenge me? You will suffer for your arrogance.",
            "I will crush you like insects beneath my feet.",
            "You cannot defeat me. I am too powerful.",
            "Your fate is sealed. You will perish at my hands.",
            "You are nothing to me. A mere annoyance.",
            "Your deaths will be swift and painful.",
            "You have already lost. You just don't know it yet.",
            "Your feeble attempts at resistance only serve to amuse me.",
            "I will destroy you, {0}, and all that you hold dear.",
            "You have made a grave mistake by crossing me, {0}. You will pay dearly for your insolence.",
            "Your feeble attempts to defeat me, {0}, are laughable at best.",
            "Your destiny is to fall before me, {0}, and I will make sure it is a painful one.",
            "You should have never challenged me, {0}. Your fate is sealed.",
            "You are no match for me, {0}. Prepare to meet your doom.",
            "Your time is up, {0}. I will enjoy crushing you beneath my heel.",
            "I will make an example of you, {0}, to all who dare to oppose me.",
            "Your struggles are futile, {0}. I will crush you without mercy.",
            "You have sealed your own fate, {0}. Your death will be slow and agonizing."
    };

    public static String[] bossAttackedQuotes = {
            "You think you can defeat me, {0}? I laugh in the face of your feeble attempts.",
            "Your puny attacks are no match for my strength, {0}. But I'll give you an 'A' for effort.",
            "You're like a little mosquito, {0}, buzzing around my head. It's time to squash you.",
            "Your destiny is to be crushed beneath my heel, {0}. But hey, at least you'll go down in style.",
            "You remind me of a clumsy toddler, {0}, flailing around without any real direction. And now you fall.",
            "You're starting to bore me, {0}. Time to end this little game and get on with my day.",
            "I'm not sure whether to be impressed or amused, {0}. You've managed to last longer than I thought you would.",
            "You really thought you could beat me, {0}? I'm afraid you'll have to try a little harder than that.",
            "Your efforts are valiant, {0}, but ultimately pointless. Surrender now and save yourself the humiliation.",
            "I've faced tougher opponents than you in my sleep, {0}. But don't worry, I'll still make your defeat entertaining.",
            "You're like a gnat, {0}. I'll swat you with a flick of my wrist.",
            "Do you really think you stand a chance, {0}? You're nothing but a flea on my back.",
            "I'm starting to get annoyed, {0}. You're like a persistent itch I can't scratch.",
            "You're making me break a sweat, {0}. That's not easy to do, considering I'm not even trying.",
            "I have to admit, {0}, you've got some spunk. But it won't save you from your inevitable defeat.",
            "You're not even a worthy adversary, {0}. More like a pesky fly that needs to be swatted.",
            "I'm beginning to think this is all just a game to you, {0}. A game you're losing, of course.",
            "You're like a puppy, {0}. Cute and harmless, but ultimately no match for my power.",
            "I'm going to enjoy this, {0}. Watching you squirm beneath my heel brings me great pleasure.",
            "You're like a child with a toy sword, {0}. I'll humor you for a while before I crush you."
    };



}
