/**
* (Caravelo( Recruitment process, Nov 2015
* C002 - Headline headache (Code)
* 
* @author Yago Liron Rodriguez
**********************************************/

import java.util.Hashtable;
import java.util.ArrayList;

/*Public class which represents the Rules that we want to apply*/
public class Rules
{
	//abbr
	private static Hashtable<String, String> wordType=new Hashtable<String, String>();
	private static ArrayList<String> caseSpecific=new ArrayList<String>();
	private static ArrayList<String> articles=new ArrayList<String>();
	private static Hashtable<String,String> specialWords=new Hashtable<String,String>();

	//we will initialize the wordType hashtable with the wordType that we need of this project to apply the rules
	static
	{
		//this is a DEMO version. We can improve it defining a txt file with more special words
		//initialize wordTypes of the dictionary
		wordType.put("a.","adjective");
		wordType.put("adj.","adjective");
		wordType.put("adv.","adverb");
		wordType.put("n.","noun");
		wordType.put("p.","participle");
		wordType.put("v.","verb");
		wordType.put("pron.","pronoun");
		wordType.put("conj.","conjunction");
		wordType.put("prep.","preposition");

		//initialize case-specific product names. We could update this list with new names or having a TXT file and read directly the case-specific words
		caseSpecific.add("README");
		caseSpecific.add("mySAP.com");
		caseSpecific.add("e-Business");
		//... and more words... 

		//set the definite/indefinite articles because are common use and could be repeated frequently
		articles.add("a");
		articles.add("an");
		articles.add("the");
		articles.add("some");//some is an article if the next word is a noun

		//define special words and the rule that are relationated to apply
		specialWords.put("to","0");
		specialWords.put("some","0");

	}

	//returns true if the word is special
	public static boolean isSpecialWord(String word)
	{
		//we need to parse the response with a RE and if we find the string \word\ we return true else false
		return caseSpecific.contains(word);
	}

	//returns word type gived a response of the dictionary
	public static String getWordType(String response)
	{
		//we need to parse the response to obtain the type. We need to search with a RE the pattern ", xxxx. ""
		return "";
	}

	//returns true if the word exist at dictionary
	public static boolean isWordAtDic(String response, String word)
	{
		//we need to parse the response with a RE and if we find the string \word\ we return true else false
		return true;
	}

	//returns true if is an specific-case
	public static boolean isCaseSpecific(String word)
	{
		return caseSpecific.contains(word);
	}
}