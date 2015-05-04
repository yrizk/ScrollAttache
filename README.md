# ScrollAttache
A simple demonstration on how to use what I call a "StickyView". The bodes well for projects that span a wide range of apis since the core of it is stuff you get from eclair. 

[![Build screen](https://github.com/yrizk/ScrollAttache/blob/master/demo.gif)]


What you'll need
1. Specific Layout Heiarchy [see res/layouts/activity_main.xml]  \n 
2. An extension of ScrollView, you'll need to add code to onScroll \n 
3. a interface that connects the ScrollView to the class controlling the parent (for me this is Main, but one can easily change that to better have mvc or whatever)  \n 
4. When the time comes, you'll pop the view you wish to persist on the screen from its parent (which will be the scrollview), and add it at index 0 of the top root of the layout. since it is now simply a sibling of the ScrollView, it sticks on the screen. in a similar fashion, you can do the reverse operation to put the view back where you found it. 

Potential areas for improvement 
1. making this a custom for people can use to hook into a scrollview and does all the calculations/view switching automatically 
2. is the LinearLayout really neceassary as  a parent? if so, it might perform slightly better if the LinearLayout was attached/removed from the view tree dynamically as needed. since this specific view tree was very small, this did not present itself as a performance pain . 
3. perhaps a neat little animation to move between the two states. 

That's it! Look at the code for more specific comments. Hope this was helpful. 
