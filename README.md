# ScrollAttache
A simple demonstration on how to use what I call "StickyViews"

Demo: 
[![Build screen](https://github.com/yrizk/ScrollAttache/blob/master/demo.gif)]
https://github.com/yrizk/ScrollAttache/blob/master/demo.gif

What you'll need
1. Specific Layout Heiarchy [see res/layouts/activity_main.xml]  \n 
2. An extension of ScrollView, you'll need to add code to onScroll \n 
3. a interface that connects the ScrollView to the class controlling the parent (for me this is Main, but one can easily change that to better have mvc or whatever)  \n 
4. When the time comes, you'll pop the view you wish to persist on the screen from its parent (which will be the scrollview), and add it at index 0 of the top root of the layout. since it is now simply a sibling of the ScrollView, it sticks on the screen. in a similar fashion, you can do the reverse operation to put the view back where you found it. 


That's it! Look at the code for more specific comments. Hope this was helpful. 
