/**
* (Caravelo( Recruitment process, Nov 2015
* C002 - Headline headache (Code)
* 
* @author Yago Liron Rodriguez
**********************************************/

/*
We observe that for apply the required rules we need an English dictionary. We 
can use an offline dictionary or online. We can profit the exercice 1 connection 
to use other Web service which allows us to query words and obtain the type of words. 

The online dictionary Web Service that we are going to use is: 

services.aonaware.com

We can use SOAP 1.1, 1.2, HTTP GET y POST. We are going to choose HTTP GET 
to profit previous code

an example of the API use HTTP GET is

http://services.aonaware.com/DictService/DictService.asmx/DefineInDict?dictid=gcide&word=figure

We have several dictionaries to use and the best dictionary that adapt to solve part of the exercice
is "The Collaborative International Dictionary of English v.0.44" with id "gcid"

vars: 

dictid = the identificator to query only one dictionary (we have more to query)
word = the word that we want to query and obtain the type of word

We have the same advantages and disadvantages as Coin flipper proposed solution (see C001 - Coin flipper) 

########################################################
    RULES TO APPLY TO THE STYLE
########################################################

CAPITALIZE WORDS
+ noun
+ verbs
+ participles (this is a verb. At dictionary we identify verb)
+ adverbs
+ adjectives
+ pronouns
? subordinating conjunctions (if, because, as, that)
+ prepositions and conjunctions with five or more letters 
+ first and last words of sentence
? prepositions of phrasal verbs (We can check if AFTER the verb we will have a PREPOSITION but we will not know surely if is a phrasal verb or no...) 
+ hyphenated words (words that has -) (NOPE)
+ words and phrases in parentesis
+ any word after a colon or semicolon , or ; 

NO CAPITALIZE WORDS
+ coordinating conjunctions (and,but,or,nor,for)
+ prepositions of four or fewer letters 
+ articles (except if there is a first or final word of the sentence)
+ the word "to" in the infinitive cases
+ case-specific product names, words or phrases

########################################################
    HOW TO APPLY THE DEFINED RULES
########################################################

We need to establish an order to apply the rules before implement the algorithm... I am studying that... 

CAPITALIZE RULES
(1) we need to identify parentesis. Then we need to trim the "(" and ")" and continue applying the rules (do not affect)
(2) we need to identify hyphenated words (if they has a "-" between words). We can split in two words and compare if this words exist or not
(3) The first token [0] and last token [length-1] they will be capitalized anyway. We can do it the last rule to apply. We can use a FLAG
(4) we need to identify colon or semicolon of the token because the next token will be capitalized. We can use a FLAG to mark this situation
//(5) we need to identify possible phrasal verb. if we identify a VERB and after we identify a PREPOSITION. We can use other FLAG to that
//(6) we search the word and we need to identify the kind of word NOUN, VERB, PARTICIPLE (is a VERB), ADVERB and ADJECTIVE
//(6)(bis) if we found a AMBIGUOUS word with double function as articles (a, an, the) and "that" "than" "this" "each" "to" "as" ...
//(7) if we find a conjunction (if, because, as, that) there are AMBIGUOUS words
//(8) if we find a conjunction of five or more letters
//(9) if we find a preposition of five or more letters

NO CAPITALIZE RULES
//(10) coordinated conjunctions (and,but,or,for,nor) activated at (7) and (8) we need to diference subordinating from coordinating conjunctions and apply the appropiate rule
(11) prepositions (with, to, for, at...) activated at (9) except rule (2), (3), (4), (5). This means that other rules are mandatory. 
(12) articles (first or final word override this rule). "a", "an", "the" are articles but "some" is an article if the next word is a NOUN
(13) "to" on the infinitve cases. If the previous word is "to" and the next word is a verb, then this to is lowercase. If we found "to" we can use a FLAG
(14) if we found a specific names defined at this rule we will not touch them. This override all rules. We need to generate a list of specific names

I am studying the algorithm... 

PROBLEMS

- What happens if the word is not at the dictionary. Capitalize or no capitalize? We opt to NO capitalize and ignore it EXCEPT if is first or last word
- what happens with plurals? The irregular plurals are included on the dictionary but not the regular. For the regulars we can check if the last letter are "s" or "es" and try to check on the dictionary
- we need to trim special chars as "(" + ")"+ "," + ";" can appear at the end or beginning of the word. We can define more special chars. 
- what we do if find other chars as ":", ".","_", "?", "!"... I think that we need to ignore this chars and continue analyzing or discard it to send it
- at (6) what happens with the words with AMBIGUOUS words? adverbs are AFTER the verb. If we identify 

*/

//Java util Scanner to readline from the input standard 
import java.util.Scanner; 

//classes to connect to the Web Service which offers the dictionary URL
import java.net.URL;
import java.io.*;
import javax.net.ssl.HttpsURLConnection;

//we will use a 

public class StyleRules
{
    //we define the dictionary that we are going to use
    private static final String dictId="gcide";
    //we define the base URL that contains the URL web service without the word that we want to check because we need to parse it after
    private static final String baseURL = "services.aonaware.com/DictService/DictService.asmx/DefineInDict?dictid="
                                                        +dictId+"&word=";
    //main function
    public static void main(String[] args)
    {
        try
        {
            Scanner teclado=new Scanner(System.in);
            String finalResult, sentence="";
            String [] splittedSentence=null;
            System.out.println("C002 - Headline headache (Code)");
            System.out.println("*******************\n");
            System.out.println("Give me a sentence to apply the required style: ");
            if(teclado.hasNextLine()) sentence=teclado.nextLine();

            if(sentence!=null)
            {
                //this will be the word analyzer
                //...WE NEED TO APPLY A GRAMMAR DIC BETTER THAN NORMAL DICTIONARY TO OBTAIN GRAMMAR TYPE WORDS ASSIGNED. We could use "org.languagetool". 
                //exactly we need to use the ChunkTag method [1] to obtain the tag of the analyzed word to know if is a verb, pronoum, noum, adjective, adverb...
                //[1] https://www.languagetool.org/development/api/ 
                splittedSentence=sentence.split("\\s");
                int size=splittedSentence.length;
                for(String token : splittedSentence)
                {
                    //we need to apply the STYLE RULES
                    //splitted sentence to test split works and MAIN LOOP of the application
                    System.out.println(token);
                }
            }
            /** ...BRAIN STORMING PROCESS... TO COMPLETE... */
            //NOTE: we suppose that person writes words separated with a blank space. Do not contemplate concatenated words without space as: 
            //"wecangotothebeach". Anyway we could do it parsing this word char by char with a function and querying the dictionary except 
            //separated letters as "a,b,c" that we know that has a definition on the dictionary. If we find a word of two letters or more EXCEPT 
            //"a" that can be a preoposition, then split the word "we" and continue analysing the rest "cangotothebeach" with the same logic 
            //till to the end of the token
            //(0) we need to tokenize the sentence in words (spaces)
            //(00) we can start to check tokens sequentially from first token to last and using FLAGS to emulate a syntax analizer
            //(000) we need to identify the words and quit "(" and ")" before query the dictionary
            //(0000) we need to create connections to web service dictionary as well as tokens we find to identify the kind of word and dump it in an array
            /** ...BRAIN STORMING PROCESS... TO COMPLETE... */
        }
        catch(Exception e)
        {
            System.out.println("Something wrong happened: "+e);
        }
    }
}
