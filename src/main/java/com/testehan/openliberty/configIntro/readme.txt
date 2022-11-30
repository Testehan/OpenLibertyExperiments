It seems like there is either some weird bug in my code, or in the way various used OpenLiberty
features work. This is because as the current setup is right now, the servlet from InventoryServlet
does not work, because of the use of @ApplicationPath. If I comment out all these annotations from all
the packages, then the servlet works (however for the servlet website to work as expected, i need one
of these @ApplicationPath cause the js part of the site makes a call to one of them to obtain data..)

So I should either try to use the features from the tutorial...or idk