As the code is right now, the servlet works and loads data, but only one ApplicationPath annotation is present in the
entire project.

http://localhost:9080/OpenLibertyExperiments/car-types
should load a servlet and then display 2 cars

Also the tutorial is very vague about how to do the whole port switching (for example they have tons of code in the
server.xml). I meaan sure in com.testehan.openliberty.configIntro.rest.Producer the configured port is injected and then used
to make the call to ObtainCarTypes, but then what is the point of the whole server.xml code ?

<classloader>
    <commonLibrary>
        <folder dir="${wlp.install.dir}/../../../../obtainCarTypes/db/prototype" />
    </commonLibrary>
</classloader>
(this code is I think responsible for the loading of the correct cars file. But keep in mind that in ObtainCarTypes.java I
switched the code, because what they had in the tutorial was not working..)

And how is the switching done ?