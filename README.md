BestGameEver2
=============

In the beginning, two boys had a dream. After playing games their entire lives, they decided that they would create the best game ever. They recruited someone, and they teamed up to create the BEST game the world has ever known. 


    4/9/13: Blade

        added another instance of the addAttack (Character) class with only one parameter

            automatically adds move to lowest available slot

        Set the Character (Character) class to use methods (mostly just a syntax change)

        Added a catch for the attackOther (Character) class if the user types in an unknown move

            throws catch message, loops back to beginning of method

        Made it so the game can tell who wins (Arena)

    4/13/13: Andrew

        changed algorithm for damage (includes attack and defense stats) and added chance for critical hits - NEEDS MAJOR BALANCING

        added option to replay battle

        HIGHLIGHT: added level up/exp points system w/ moves that can be learned as character levels up

            does NOT give player a choice to remove a move if number of moves exceeds 4 yet

        fixed addAttack(input), made no changes to addAttack(input,slot)

        NOTE: Suggest playing game with slightly enlarged console to see most improvements

    4/13/13 Blade

        Added a working removeAttack function

        gave a player the option to remove a move if the number of moves exceeds 4

    4/22/13 Andrew

        CARL JOINS

            gonna focus on combat system

    4/23/13 Blade

        I/O for attackList is essentially done, but has not been implemented or added to google doc (waiting for GUI to be finished first)

        JOptionPane for character creation begun, I/O not finished, not added to google doc yet

    4/26/13 Andrew

        Finally updated code with GUI

        Numerous problems when repeating battles - working on fixes now

        Also working on converting from typed inputs to buttons

        Added barebones Item class - needs severe expanding and discussion on what exactly it’s going to do

        NOTE: I HAVE SAVED PREVIOUS CODE JUST IN CASE WE WANT TO REVERT BACK

        awaiting Blade’s I/O integration this afternoon.................

    4/26/13 Blade

        Added my independent code for reference

        Working on integration. Expected to be done today or tomorrow

        NOTE; Have to add the classes in Environment Stuff folder to get the code to display properly, I just added them to the folder because they’re otherwise very unnecessary

    4/28/13 Blade

        Integration is near completion, although I’m having a tough time sorting through all the code

            I’ve basically copied in my code and gotten it in a working state. My code isn’t being used yet, so the program is essentially in the state where Andrew left off. I’ll need Andrew’s help determining where attacks and character attributes are being called. Also, I need to finish off the I/O before we can move on

            The current version is something we can all copy and work off of, so its a start :/

        Note: I/O is incomplete

            need to add function to allow program to edit character stats

                changing statistics

                add attack list

                changing attacks

    5/2 Blade-Andrew

        Completing Integration

        UPDATE: Integration still needs work, especially on I/O

        Game can now restart without any problems

        Working on Grid system

    5/3 Blade

        Integration as of now is complete

        I/O still needs work, particularly on the character class

        Grid system in progress

        Action point class needs creating and item class needs working - Carl?

        GIT IS WORKING YEEEEEEEEEEEEAAA :D :D :D :D

            Latest code has been pushed to git

    5/6/2013 Blade

        Made some major I/O overhauls. Still in the process of fleshing out I/O, but I think I’ve reached the point where I can start copy pasting a lot of stuff

        Pushed to github; Make sure to pull before editing project to avoid mergers

        Note: everyone should start using the issues feature in github, as it makes it really easy to see what’s yet to be done

    5-6-2013 Carl

        Added Notes to readme.md in git
    
    5-21-2013 Blade
    	
    	Added IO compenent to Items
    	
    	Added addItem and useItem to Character class
    
    6-3-2013 Blade and Andrew
    	
    	Fixed IO elements for Items
    	
    	Plenty of GUI updates, turn system still needs work
    	
    6-5-2013 Blade
    
    	IO redundency fixes
    	
    	IO Item usage fixes
    	
    	New Character constructor that takes line number	
    		

