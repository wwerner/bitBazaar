package de.bit.internal.bazaar.data;

import java.io.Serializable;
import java.util.Random;

import de.bit.internal.bazaar.model.Item;
import de.bit.internal.bazaar.model.ItemState;
import de.bit.internal.bazaar.model.User;

@SuppressWarnings("serial")
public class TestItemContainer extends ItemDataContainer implements
		Serializable {

	public TestItemContainer() {
		super(Item.class);
		createWithTestData();
	}

	public void createWithTestData() {

		final String[] titles = { "Laufrad", "Ferngesteuertes Auto",
				"Wickeltisch", "Bong", "E-Gitarre" };
		final String[] descriptions = {
				"Now that the, uh, garbage ball is in space, Doctor, perhaps you can help me with my sexual inhibitions? You can crush me but you can't crush my spirit! You know, I was God once. We'll need to have a look inside you with [pulls out a long cord with a camera lens at the end] this camera. Fry! Quit doing the right thing, you jerk!",
				"OK, this has gotta stop. I'm going to remind Fry of his humanity the way only a woman can. Hey, whatcha watching? I'm sorry, guys. I never meant to hurt you. Just to destroy everything you ever believed in. Leela's gonna kill me. Eeeee! Now say \"nuclear wessels\"! And I'd do it again! And perhaps a third time! But that would be it.",
				"Robot 1-X, save my friends! And Zoidberg! Shut up and take my money! We're also Santa Claus! Good news, everyone! There's a report on TV with some very bad news!",
				"I feel like I was mauled by Jesus. And yet you haven't said what I told you to say! How can any of us trust you? Yeah, I do that with my stupidness.",
				"Switzerland is small and neutral! We are more like Germany, ambitious and misunderstood! You, a bobsleder!? That I'd like to see! This opera's as lousy as it is brilliant! Your lyrics lack subtlety. You can't just have your characters announce how they feel. That makes me feel angry! That's the ONLY thing about being a slave. I love this planet! I've got wealth, fame, and access to the depths of sleaze that those things bring. Or a guy who burns down a bar for the insurance money!",
				"Anyhoo, your net-suits will allow you to experience Fry's worm infested bowels as if you were actually wriggling through them. When I was first asked to make a film about my nephew, Hubert Farnsworth, I thought \"Why should I?\" Then later, Leela made the film. But if I did make it, you can bet there would have been more topless women on motorcycles. Roll film! Well I'da done better, but it's plum hard pleading a case while awaiting trial for that there incompetence. I had more, but you go ahead. Hey, you add a one and two zeros to that or we walk!",
				"And then the battle's not so bad? Guess again. You won't have time for sleeping, soldier, not with all the bed making you'll be doing. I decline the title of Iron Cook and accept the lesser title of Zinc Saucier, which I just made up. Uhh... also, comes with double prize money. I found what I need. And it's not friends, it's things.",
				"Hey! I'm a porno-dealing monster, what do I care what you think? I barely knew Philip, but as a clergyman I have no problem telling his most intimate friends all about him. I could if you hadn't turned on the light and shut off my stereo. With a warning label this big, you know they gotta be fun!" };

		Random r = new Random(0);
		for (int i = 0; i < 100; i++) {
			User user = new User();
			user.setDisplayName("Hubert Farnsworth");
			user.setUserName("hubert.farnsworth@gmail.com");
			
			Item item = new Item();
			item.setTitle(titles[r.nextInt(titles.length)]);
			item.setDescription(descriptions[r.nextInt(descriptions.length)]);
			item.setPrice(r.nextDouble() * 10);
			item.setState(ItemState.DRAFT);
			item.setAuthor(user);
			addItem(item);
		}
	}
}
